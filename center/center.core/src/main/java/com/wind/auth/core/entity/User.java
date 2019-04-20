package com.wind.auth.core.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private List<String> roles;
}
