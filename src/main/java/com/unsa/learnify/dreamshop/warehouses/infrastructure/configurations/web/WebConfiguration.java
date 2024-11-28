package com.unsa.learnify.dreamshop.warehouses.infrastructure.configurations.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .maxAge(3600)
            .allowedHeaders("*")
            .allowedMethods("*");
    }
}
