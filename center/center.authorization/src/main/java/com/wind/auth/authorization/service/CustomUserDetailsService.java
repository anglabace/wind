package com.wind.auth.authorization.service;

import com.wind.auth.authorization.authentication.IntegrationAuthenticationContext;
import com.wind.auth.authorization.authentication.IntegrationAuthenticationEntity;
import com.wind.auth.authorization.authentication.IntegrationAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.wind.auth.core.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Service
@Slf4j
@Primary
public class CustomUserDetailsService implements UserDetailsService, ApplicationContextAware {

    private Collection<IntegrationAuthenticator> authenticators;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (this.authenticators == null) {
            Map<String, IntegrationAuthenticator> map = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
            this.authenticators = map.values();
        }
        IntegrationAuthenticationEntity entity = IntegrationAuthenticationContext.get();
        User user = this.authenticate(entity);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(value -> authorities.add(new SimpleGrantedAuthority(value)));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    private User authenticate(IntegrationAuthenticationEntity entity) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(entity)) {
                    return authenticator.authenticate(entity);
                }
            }
        }
        return null;
    }
}
