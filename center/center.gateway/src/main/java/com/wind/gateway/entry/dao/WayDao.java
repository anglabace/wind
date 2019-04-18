package com.wind.gateway.entry.dao;

import com.wind.gateway.entry.entity.Way;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Document(collection="way")
public class WayDao extends BaseDao<Way> {
}
