package com.hm.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class CustomGlobalFilter {

    @Bean
    @Order(-1)
    public GlobalFilter logFilter(){
        return ((exchange, chain) -> {
            log.info("REQUEST_URI: [{}]",exchange.getRequest().getURI());
           return chain.filter(exchange).then(Mono.fromRunnable(() -> {
               log.info("STATUS: [{}]",exchange.getResponse().getStatusCode());
            }));
        });
    }


}
