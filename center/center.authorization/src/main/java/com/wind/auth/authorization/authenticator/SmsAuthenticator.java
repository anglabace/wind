package com.wind.auth.authorization.authenticator;

import com.wind.auth.authorization.authentication.AbstractIntegrationAuthenticator;
import com.wind.auth.authorization.authentication.IntegrationAuthenticationEntity;
import com.wind.auth.core.base.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SmsAuthenticator extends AbstractIntegrationAuthenticator {

    private final static String AUTH_TYPE = "sms";

    @Override
    public User authenticate(IntegrationAuthenticationEntity entity) {
        String mobile = entity.getAuthParameter("mobile");
        if (StringUtils.isEmpty(mobile)) {
            throw new OAuth2Exception("手机号不能为空");
        }
        String code = entity.getAuthParameter("code");
        if (!"1234".equals(code)) {
            throw new OAuth2Exception("验证码错误或已过期");
        }
        return null;
    }

    @Override
    public boolean support(IntegrationAuthenticationEntity entity) {
        return AUTH_TYPE.equals(entity.getAuthType());
    }
}