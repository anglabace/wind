package com.wind.auth.core.rest;

import com.wind.auth.core.Result;
import com.wind.auth.core.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

public class BaseController<T> {

    @Autowired
    private BaseDao<T> dao;

    @GetMapping("/all")
    public Result all() {
        return Result.success(dao.findAll());
    }

    @GetMapping
    public Result select(@RequestParam int _p, @RequestParam int _size, @RequestParam String _sort) {
        return Result.success(dao.find(new Query().with(PageRequest.of(_p, _size, Sort.by(Sort.Direction.DESC, _sort)))));
    }

    @PostMapping
    public Result insert(@RequestBody T t) {
        return Result.success(dao.insert(t));
    }

    @PutMapping
    public Result update(@RequestBody T t) {
        return Result.success(dao.save(t));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        return Result.success(dao.remove(id));
    }
}
