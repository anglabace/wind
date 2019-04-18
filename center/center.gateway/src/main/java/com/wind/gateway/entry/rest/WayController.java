package com.wind.gateway.entry.rest;

import com.wind.auth.core.Result;
import com.wind.gateway.entry.dao.WayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("way")
public class WayController {

    @Autowired
    private WayDao wayDao;

    @GetMapping("/all")
    public Result all() {
        return Result.success(wayDao.findAll());
    }
}
