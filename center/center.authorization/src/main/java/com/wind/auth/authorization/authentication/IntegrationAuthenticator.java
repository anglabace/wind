package com.wind.auth.authorization.authentication;

import com.wind.auth.core.base.User;

public interface IntegrationAuthenticator {

    /**
     * 处理集成认证
     *
     * @param entity 集成认证实体
     * @return 用户对象
     */
    User authenticate(IntegrationAuthenticationEntity entity);

    /**
     * 预处理
     *
     * @param entity 集成认证实体
     */
    void prepare(IntegrationAuthenticationEntity entity);

    /**
     * 判断是否支持集成认证类型
     *
     * @param entity 集成认证实体
     */
    boolean support(IntegrationAuthenticationEntity entity);

    /**
     * 认证结束后执行
     *
     * @param entity 集成认证实体
     */
    void complete(IntegrationAuthenticationEntity entity);
}