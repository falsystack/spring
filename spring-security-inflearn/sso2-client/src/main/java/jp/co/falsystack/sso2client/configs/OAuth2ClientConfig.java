package jp.co.falsystack.sso2client.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    protected ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
    }

    private ClientRegistration keycloakClientRegistration() {
        return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/oauth2")
                .registrationId("keycloak")
                .clientId("oauth2-client-app")
                .clientSecret("eoOgG57DSNmd1fD8q7vhCz94Ocv6DbxW")
                .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
                .scope("openid", "profile", "email") // keycloak 버전이 업데이트 되어서 scope, 특히 openid 가 없으면 403 에러가 난다
                .build();
    }
}
