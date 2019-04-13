package com.wind.demo.feign.service;

import com.wind.auth.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Primary
@FeignClient(name = "producer", fallback = ClassServiceFallback.class)
public interface ClassService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    Result users(@RequestParam("name") String name);

    @RequestMapping(value = "/hello/", method = RequestMethod.POST)
    Result users(@RequestBody Map map);
}
