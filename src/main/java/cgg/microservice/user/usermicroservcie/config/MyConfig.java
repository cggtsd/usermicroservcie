package cgg.microservice.user.usermicroservcie.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced
    @Lazy
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    RestClient getClient() {

        return RestClient.create();
    }

    @Bean
    @LoadBalanced
    @Lazy
    RestClient.Builder getRestClientBuikder() {
        return RestClient.builder();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Hotel App API")
                        .description("This Hotel Rating APplication")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("fatima.d@cgg.gov.in")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

}
