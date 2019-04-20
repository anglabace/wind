package com.wind.gateway.entry.service;

import com.wind.gateway.entry.dao.WayDao;
import com.wind.gateway.entry.entity.Way;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

@Service
@Slf4j
public class RouteService implements RouteDefinitionRepository {

    @Autowired
    private WayDao wayDao;

    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();

    private void loadRouteDefinition() {
        List<Way> gatewayWays = wayDao.findAll();
        gatewayWays.forEach(value -> {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(value.getId().toString());
            routeDefinition.setUri(URI.create(value.getUri()));
            if (value.getPredicates() != null) {
                value.getPredicates().forEach(item ->
                        routeDefinition.getPredicates().add(new PredicateDefinition(item))
                );
            }
            if (value.getFilters() != null) {
                value.getFilters().forEach(item ->
                        routeDefinition.getFilters().add(new FilterDefinition(item))
                );
            }
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
        });
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        loadRouteDefinition();
        return Flux.fromIterable(routeDefinitionMaps.values());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeDefinitionMaps.remove(id);
            return Mono.empty();
        });
    }
}
