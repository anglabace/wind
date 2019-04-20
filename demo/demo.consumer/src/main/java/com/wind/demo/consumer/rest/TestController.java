package com.wind.demo.consumer.rest;

import com.wind.auth.core.Result;
import com.wind.demo.consumer.service.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private BackService backService;

    @GetMapping("/test")
    public Result hello(@RequestHeader HttpHeaders headers, @RequestParam String name) {
        return backService.hello(headers, name);
    }

    @PostMapping("/test")
    public Result hello(@RequestHeader HttpHeaders headers, @RequestBody Map map) {
        return backService.hello(headers, map);
    }

}
