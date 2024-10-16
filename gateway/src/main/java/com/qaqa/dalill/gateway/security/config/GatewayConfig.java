package com.qaqa.dalill.gateway.security.config;

import com.qaqa.dalill.gateway.security.filter.TokenAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
public class GatewayConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public GatewayConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_route", r -> r.path("/user/**")
                        .filters(f -> f.filter(tokenAuthenticationFilter.apply(new TokenAuthenticationFilter.Config())))
                        .uri("http://localhost:8081"))
                .build();
    }
}

