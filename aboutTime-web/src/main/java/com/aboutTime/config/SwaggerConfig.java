package com.aboutTime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(title = "AboutTime API",
                description = "AboutTime API 명세서",
                version = "1.0"))
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("AboutTime API ver.1.0")
                .pathsToMatch(paths)
                .build();
    }
}
