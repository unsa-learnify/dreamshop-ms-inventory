package com.unsa.learnify.dreamshop.warehouses.infrastructure.configurations.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "Authorization",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "Bearer"
)
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addTagsItem(new Tag()
                .name("Categories")
                .description("Endpoints for managing categories"))
            .addTagsItem(new Tag()
                .name("Products")
                .description("Endpoints for managing products"))
            .info(new Info()
                .title("Dreamshop - Warehouse Microservice - Restful API")
                .version("1.0")
                .description("Detailed API documentation for the Dreamshop Warehouse Microservice, providing endpoints for CRUD operations on products and categories, with secure JWT authentication and flexible integration for modern e-commerce systems.")
                .contact(new Contact()
                    .name("Angel Hincho")
                    .email("ahincho@unsa.edu.pe")
                    .url("https://github.com/ahincho")
                )
                .termsOfService("https://github.com/unsa-learnify/dreamshop-ms-warehouses")
            );
    }
}
