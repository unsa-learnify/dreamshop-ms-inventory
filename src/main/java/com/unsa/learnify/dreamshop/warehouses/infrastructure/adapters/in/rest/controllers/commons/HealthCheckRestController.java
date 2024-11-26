package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.in.rest.controllers.commons;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheckRestController {
    @GetMapping
    public String healthCheck() {
        return "OK";
    }
}
