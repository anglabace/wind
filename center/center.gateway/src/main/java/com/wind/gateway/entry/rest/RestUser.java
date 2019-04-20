package com.wind.gateway.entry.rest;

import com.wind.auth.core.entity.User;
import com.wind.auth.core.rest.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class RestUser extends BaseController<User> {
}
