package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()

            // USER SERVICE ROUTES
            .route("user-service", r -> r.path("/users/**", "/user/**", "/playlists/**")
                .uri("lb://USER-SERVICE"))

            // ADMIN SERVICE ROUTES
            .route("admin-service", r -> r.path("/admin/**", "/songs/**")
                .uri("lb://ADMIN-SERVICE"))

            .build();
    }
}
