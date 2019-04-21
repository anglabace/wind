package com.wind.gateway.entry.config;

import com.wind.gateway.entry.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.stream.Stream;

@Slf4j
@Component
public class AccessGatewayFilter implements GlobalFilter, WebFilter {

    private final static String X_CLIENT_TOKEN = "x-client-token";

    @Autowired
    private AuthService authService;

    /**
     * IP地址白名单
     */
    @Value("${gate.whitelist.ip}")
    private String whitelist = "";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
        if (Stream.of(this.whitelist.split(",")).anyMatch(value -> ip.startsWith(StringUtils.trim(value)))) {
            return chain.filter(exchange);
        }
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        if (authService.hasPermission(authentication, url, method)) {
            ServerHttpRequest.Builder builder = request.mutate();
            builder.header(X_CLIENT_TOKEN, authService.getJwt(authentication).getClaims());
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }
        return unauthorized(exchange);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String ip = request.getRemoteAddress().getAddress().getHostAddress();
        boolean pass = Stream.of(this.whitelist.split(",")).anyMatch(value -> ip.startsWith(StringUtils.trim(value)));
        if (pass) {
            return chain.filter(exchange);
        }
        String url = request.getPath().value();
        String method = request.getMethodValue();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        if (authService.ignoreAuthentication(url)) {
            return chain.filter(exchange);
        }
        if (authService.hasPermission(authentication, url, method)) {
            return chain.filter(exchange);
        }
        return unauthorized(exchange);
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = exchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }
}
