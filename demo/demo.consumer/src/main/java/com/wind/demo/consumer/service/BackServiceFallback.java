package com.wind.demo.consumer.service;

import com.wind.auth.core.Result;
import com.wind.auth.core.exception.ErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BackServiceFallback implements BackService {
    @Override
    public Result hello(HttpHeaders headers, String name) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }

    @Override
    public Result hello(HttpHeaders headers, Map map) {
        return Result.fail(ErrorType.SYSTEM_BUSY);
    }
}
