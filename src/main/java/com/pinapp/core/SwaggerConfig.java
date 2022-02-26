package com.pinapp.core;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Api Test",
                description = "Api Test"),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
}