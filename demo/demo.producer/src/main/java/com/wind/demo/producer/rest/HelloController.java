package com.wind.demo.producer.rest;

import com.wind.auth.core.Result;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return randomNumeric(2) + name;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public Result world(@RequestParam String name) {
        return Result.success(name + "success");
    }
}