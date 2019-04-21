package com.wind.gateway.entry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wind.gateway.entry.dao.AuthDao;
import com.wind.auth.core.entity.Token;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthDao authDao;

    /**
     * Authorization认证开头是"bearer "
     */
    private static final int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    /**
     * 不需要网关签权的url
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";

    /**
     * jwt验签
     */
    private MacSigner verifier;

    public boolean ignoreAuthentication(String url){
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    public boolean hasPermission(String authentication, String url, String method) {
        Token token = getJwtAccessToken(authentication);
        if (token != null) {
            Criteria criteria= new Criteria();
            long count = authDao.count(new Query(criteria.andOperator(
                    Criteria.where("url").is(url),
                    Criteria.where("method").is(method.toLowerCase()),
                    Criteria.where("role").in(token.getAuthorities())
            )));
            return count > 0;
        }
        return false;
    }

    public Token getJwtAccessToken(String authentication) {
        try {
            verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
            Jwt jwt = getJwt(authentication);
            jwt.verifySignature(verifier);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jwt.getClaims(), Token.class);
        } catch (Exception e) {
            log.warn("user token has expired or signature error ");
        }
        return null;
    }

    public Jwt getJwt(String authentication) {
        return JwtHelper.decode(StringUtils.substring(authentication, BEARER_BEGIN_INDEX));
    }
}
