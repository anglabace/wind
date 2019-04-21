package com.wind.gateway.entry.entity;

import com.wind.auth.core.entity.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
public class Auth extends BaseEntity {
    String url;
    String method;
    String role;
}
