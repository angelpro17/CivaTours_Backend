package com.civa.challenge.civatours.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI civaToursPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();

        openApi
                .info(new Info()
                        .title("CivaTours Platform API")
                        .description("CivaTours Platform application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("CivaTours Platform wiki Documentation")
                        .url("https://civatours-platform.wiki.github.io/docs"));

        final String securityScheme = "bearerAuth";

               openApi.components(new Components()
                               .addSecuritySchemes(securityScheme,
                                       new SecurityScheme()
                                               .name(securityScheme)
                                               .type(SecurityScheme.Type.HTTP)
                                               .scheme("bearer")
                                               .bearerFormat("JWT")))
                       .addSecurityItem(new SecurityRequirement().addList(securityScheme));
               return openApi;
    }
}