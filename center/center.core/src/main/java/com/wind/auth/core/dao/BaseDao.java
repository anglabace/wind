package com.wind.auth.core.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> {
    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public T insert(T entity) {
        return mongoTemplate.insert(entity);
    }

    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    public DeleteResult remove(String id) {
        return mongoTemplate.remove(new Query(Criteria.where("id").is(id)), getTClass());
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(getTClass());
    }

    public List<T> find(Query query) {
        return mongoTemplate.find(query, getTClass());
    }

    public T findOne(Query query) {
        return mongoTemplate.findOne(query, getTClass());
    }

    public DeleteResult remove(Query query) {
        return mongoTemplate.remove(query, getTClass());
    }

    public UpdateResult updateFirst(Query query, Update update) {
        return mongoTemplate.updateFirst(query, update, getTClass());
    }

    public UpdateResult updateMulti(Query query, Update update) {
        return mongoTemplate.updateMulti(query, update, getTClass());
    }

    public long count(Query query) {
        return mongoTemplate.count(query, getTClass());
    }
}
