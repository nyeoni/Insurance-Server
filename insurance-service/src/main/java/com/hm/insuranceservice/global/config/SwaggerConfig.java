package com.hm.insuranceservice.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
@EnableSwagger2
public class SwaggerConfig {


    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Insurance-Service")
                .description("Insurance Service Specification")
                .version("1.0")
                .build();
    }

    public Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        return consumes;
    }

    public Set<String> getProduceContentTypes(){
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }

    @Bean
    public Docket commonApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/insurance-api/**"))
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(){
        return route(GET("/insurance-api/swagger"), request ->
            ServerResponse.temporaryRedirect(URI.create("/swagger-ui.html")).build());
    }


}
