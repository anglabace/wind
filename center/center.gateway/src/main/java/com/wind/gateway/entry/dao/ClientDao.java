package com.wind.gateway.entry.dao;

import com.wind.auth.core.dao.BaseDao;
import com.wind.auth.core.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Document(collection="client")
public class ClientDao extends BaseDao<Client> {
}
