package com.pox.two.config;

import com.google.common.collect.ImmutableMap;
import com.pox.two.config.discovery.DiscoveryGroupSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.concurrent.NotThreadSafe;

@Slf4j
@Configuration
@NotThreadSafe
public class DiscoveryConfig {

    // should wrap the raw map in something more civilized

    // ideally an internal service class should be able to
    // declare an external service dependency just like an internal one
    // e.x. @Autowired should be able to inject wrappers for rest calls for external services
    // (basically injecting generated feign clients)
    /**
     * Name -> hostname dyanmic map of service dependencies
     */
    @Bean
    public ImmutableMap<String, String> discoveredServices(@Autowired DiscoveryGroupSubscriber discoveryGroupSubscriber) {
        return discoveryGroupSubscriber.getDiscoveredServices();
    }
}
