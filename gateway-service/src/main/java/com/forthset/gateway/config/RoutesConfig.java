package com.forthset.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RoutesConfig {

    @Bean
    @Profile("!stage")
    public RouteLocator devRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // auth
                // missions
                .route("get_missions", r -> r.path("/missions")
                        .uri("http://mission:8080/missions"))
                // search
                .route("search", r -> r.path("/search")
                        .uri("http://search:8080/search"))
                .build();
    }

    @Bean
    @Profile("stage")
    public RouteLocator stageRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // auth
                // search
                .route("search", r -> r.path("/search")
                        .uri("http://search:8080/search"))
                .build();
    }
}
