package cgg.microservice.user.usermicroservcie.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration(proxyBeanMethods = false)
@OpenAPIDefinition(info = @Info(title = "User api", description = "A simple User API."))
public class SwaggerConfiguration {
}