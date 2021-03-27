package com.pox.two.rest;

import com.google.common.collect.ImmutableMap;
import com.pox.two.config.discovery.DiscoveryGroupSubscriber;
import com.pox.two.rest.model.PongDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api("Search")
@RequestMapping("v0.1/ping")
public class PingResource {

    private final DiscoveryGroupSubscriber discovery;

    @Autowired
    public PingResource(DiscoveryGroupSubscriber discovery) {
        this.discovery = discovery;
    }

    @GetMapping
    public ResponseEntity<PongDto> ping() {
        return ResponseEntity.ok(
                PongDto.builder()
                        .pong("one pong")
                        .build()
        );
    }

    /**
     * Endpoint will return pong only if all discovered services pinged return 200
     * */
    @GetMapping(path = "all")
    public ResponseEntity<PongDto> pingAllDiscovered() {
        // there should be a separate service responsible for managing the service name/host state store
        // shouldn't be exposed by the subscriber itself
        final ImmutableMap<String, String> discoveredServices = discovery.getDiscoveredServices();

        log.info("Discovered services: " + Arrays.toString(discoveredServices.entrySet().toArray()));

        final Set<Boolean> pingResults = discoveredServices.keySet().stream()
                .map(serviceName -> {

                    log.info("Pinging {}", serviceName);

                    final String serviceHost = discoveredServices.get(serviceName);
                    final WebClient httpClient = WebClient.create(serviceHost);

                    final RequestHeadersUriSpec<?> request = httpClient.get(); // kind of disappointed there is not get with custom URI ...
                    request.uri("/v0.1/ping");

                    final Mono<String> pingResponse = request.exchangeToMono(response -> {

                        if (response.statusCode().equals(HttpStatus.OK)) {
                            return response.bodyToMono(String.class);
                        }
                        return Mono.just("Error");
                    });

                    final String rawResponse = pingResponse.single().block();
                    log.info("{} responded with: {}", serviceHost, rawResponse);

                    return !"Error".equals(rawResponse);
                })
                .collect(Collectors.toSet());

        final boolean allPingsOk = !(pingResults.isEmpty() || pingResults.contains(false));
        if (allPingsOk) {
            return ResponseEntity.ok(
                    PongDto.builder()
                            .pong("pong")
                            .build()
            );
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
