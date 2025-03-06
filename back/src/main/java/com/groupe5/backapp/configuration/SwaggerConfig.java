package com.groupe5.backapp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("API GHIBLI")
                        .version("1.0")
                        .description("Documentation de l'API GHIBLI - Groupe 5")))
                .build();
    }

    /*@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API GHIBLI")
                        .version("1.0")
                        .description("Documentation de l'API GHIBLI - Groupe 5"));
    }*/
}