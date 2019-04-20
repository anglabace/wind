package com.wind.gateway.entry.rest;

import com.wind.auth.core.rest.BaseController;
import com.wind.gateway.entry.entity.Auth;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth")
public class RestAuth extends BaseController<Auth> {
}
