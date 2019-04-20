package com.wind.gateway.entry.dao;

import com.wind.auth.core.base.BaseDao;
import com.wind.gateway.entry.entity.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Document(collection="auth")
public class AuthDao extends BaseDao<Auth> {
}
