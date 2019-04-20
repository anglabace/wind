package com.wind.demo.producer.rest;

import com.wind.auth.core.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.POST, value = "/hello")
    public Result hello(@RequestHeader HttpHeaders headers, @RequestBody Map map) {
        return Result.success(randomNumeric(2) + map.toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public Result world(@RequestHeader HttpHeaders headers, @RequestParam String name) {
        return Result.success(name + "success");
    }
}