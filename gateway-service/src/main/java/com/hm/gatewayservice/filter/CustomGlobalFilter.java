package com.hm.gatewayservice.filter;

import com.hm.gatewayservice.exception.InvalidAuthException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Slf4j
@Configuration
public class CustomGlobalFilter {

    @Autowired
    private Environment env;
    private final HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>(){{
           put("/login",true);
           put("/logout",true);
           put("/join",true);
    }};

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

    @Bean
    @Order(0)
    public GlobalFilter AuthFilter(){
        return ((exchange, chain) -> {
            if(!hashMap.containsKey(exchange.getRequest().getURI().getPath())) {
                if (true) {
                    ServerHttpRequest request = exchange.getRequest();
                    ServerHttpResponse response = exchange.getResponse();
                    if (!request.getCookies().containsKey("token")) {
                        response.setStatusCode(HttpStatus.BAD_REQUEST);
                        throw new InvalidAuthException.RequireAuth();
                    }
                    String token = request.getCookies().get("token").get(0).getValue();
                    if (!isJwtValid(token)) {
                        response.setStatusCode(HttpStatus.BAD_REQUEST);
                        throw new InvalidAuthException.InvalidToken();
                    }
                }
            }
            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String token) {
        boolean isValid = true;
        String subject = null;
        String property = env.getProperty("token.secret");
        if (property==null)
            throw new NullPointerException("토큰 설정이 존재하지 않습니다.");
        try {
            subject = Jwts.parser()
                    .setSigningKey(property)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e){
            isValid = false;
        }
        if(subject==null || subject.isEmpty())
            isValid = false;
        return isValid;
    }



}
