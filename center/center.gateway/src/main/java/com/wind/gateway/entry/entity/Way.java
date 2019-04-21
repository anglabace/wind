package com.wind.gateway.entry.entity;

import com.wind.auth.core.entity.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
public class Way extends BaseEntity {
    private List<String> predicates;
    private List<String> filters;
    private String uri;
    @Builder.Default
    private Integer order = 0;
}
