package com.wind.gateway.entry.rest;

import com.wind.auth.core.rest.BaseController;
import com.wind.gateway.entry.entity.Way;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/way")
public class RestWay extends BaseController<Way> {
}
