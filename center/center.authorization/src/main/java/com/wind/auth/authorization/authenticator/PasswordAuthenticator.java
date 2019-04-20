package com.wind.auth.authorization.authenticator;

import com.wind.auth.authorization.authentication.AbstractIntegrationAuthenticator;
import com.wind.auth.authorization.authentication.IntegrationAuthenticationEntity;
import com.wind.auth.authorization.dao.UserDao;
import com.wind.auth.core.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PasswordAuthenticator extends AbstractIntegrationAuthenticator {

    @Autowired
    private UserDao userDao;

    @Override
    public User authenticate(IntegrationAuthenticationEntity entity) {
        String username = entity.getAuthParameter("username");
        String password = entity.getAuthParameter("password");
        if (username == null || password == null) {
            throw new OAuth2Exception("用户名或密码不能为空");
        }
        return userDao.findOne(new Query(new Criteria("username").is(username)));
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return StringUtils.isEmpty(entity.getAuthType());
    }
}