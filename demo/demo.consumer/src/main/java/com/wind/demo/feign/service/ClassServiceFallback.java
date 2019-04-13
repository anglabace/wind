package com.wind.demo.feign.service;

import com.wind.auth.core.Result;
import com.wind.auth.core.exception.ErrorType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClassServiceFallback implements ClassService {
    @Override
    public Result users(String name) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }

    @Override
    public Result users(Map map) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }
}
