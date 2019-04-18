package com.wind.gateway.entry.entity;

import lombok.Data;

import java.util.List;

@Data
public class Token {
    private String user_name;
    private List<String> scope;
    private String organization;
    private long exp;
    private List<String> authorities;
    private String jti;
    private String client_id;
}
