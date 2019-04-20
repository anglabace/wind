package com.wind.auth.core.entity;

import com.wind.auth.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Client extends BaseEntity {
    private String name;
    private String secret;
    private List<String> scopes;
    private List<String> types;
    private List<String> uris;
}
