package com.wind.demo.consumer.service;

import com.wind.auth.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Primary
@FeignClient(name = "producer", fallback = BackServiceFallback.class)
public interface BackService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    Result hello(@RequestHeader HttpHeaders headers, @RequestParam("name") String name);

    @RequestMapping(value = "/hello/", method = RequestMethod.POST)
    Result hello(@RequestHeader HttpHeaders headers, @RequestBody Map map);
}
