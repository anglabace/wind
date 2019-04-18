package com.wind.gateway.entry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Way extends BaseEntity {
    private List<String> predicates;
    private List<String> filters;
    private String uri;
    private Integer order;
}
