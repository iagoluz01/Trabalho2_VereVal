package com.letmetrial.api.medico.adapter.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "${api.name}", version = "${api.version}", description = "${api.description}"))
public class OpenApiConfig {

}
