package com.wind.gateway.entry.rest;

import com.wind.auth.core.Result;
import com.wind.gateway.entry.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthDao authDao;

    @GetMapping("/all")
    public Result all() {
        return Result.success(authDao.findAll());
    }
}
