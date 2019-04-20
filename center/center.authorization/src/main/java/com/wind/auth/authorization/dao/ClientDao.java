package com.wind.auth.authorization.dao;

import com.wind.auth.authorization.entity.Client;
import com.wind.auth.core.base.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Document(collection="client")
public class ClientDao extends BaseDao<Client> {
}
