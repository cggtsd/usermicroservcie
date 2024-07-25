package cgg.microservice.user.usermicroservcie.config.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;

@Configuration
@Component
@AllArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate template) {
        String token = manager.authorize(
                OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
                .getAccessToken().getTokenValue();

        template.header("Authorization", "Bearer " + token);
    }

}
