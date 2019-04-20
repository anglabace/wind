package com.wind.gateway.entry.entity;

import com.wind.auth.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auth extends BaseEntity {
    String url;
    String method;
    String role;
}
