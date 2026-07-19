package com.example.libraryapidevjoint.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .group("Library API")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Library Management REST API")
                                .version("1.0")
                                .description("REST API for Library Management System")
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Local Server")
                ));
    }

}
