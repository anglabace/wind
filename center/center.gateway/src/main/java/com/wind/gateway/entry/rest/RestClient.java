package com.wind.gateway.entry.rest;

import com.wind.auth.core.entity.Client;
import com.wind.auth.core.rest.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/client")
public class RestClient extends BaseController<Client> {
}
