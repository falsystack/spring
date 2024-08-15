package jp.falsystack.springsecurityoauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class OAuth2ClientConfig {

  private final InMemoryClientRegistrationRepository clientRegistrationRepository;

  public OAuth2ClientConfig(InMemoryClientRegistrationRepository clientRegistrationRepository) {
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests(
        authRequest ->
            authRequest
//                .requestMatchers("/loginPage")
//                .permitAll()
                .anyRequest()
                    .permitAll());
//                .authenticated());
//    http.oauth2Login(oauth2 -> oauth2.loginPage("/loginPage"));
    http.oauth2Login(Customizer.withDefaults());
    http.logout(logout ->
            logout.logoutSuccessHandler(oidcLogoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID"));
    return http.build();
  }

  private LogoutSuccessHandler oidcLogoutSuccessHandler() {
    OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
    successHandler.setPostLogoutRedirectUri("http://localhost:8080/login");

    return successHandler;
  }

//  @Bean
//  public ClientRegistrationRepository clientRegistrationRepository() {
//    return new InMemoryClientRegistrationRepository(keycloakClientRegistration());
//  }
//
//  private ClientRegistration keycloakClientRegistration() {
//    return ClientRegistrations.fromIssuerLocation("http://localhost:8080/realms/OAuth2")
//        .registrationId("keycloak")
//        .clientId("oauth2-client-app")
//        .clientSecret("aTTEYbLxElXEkebDcefJ2LYThRHwM5pf")
//        .redirectUri("http://localhost:8081/login/oauth2/code/keycloak")
//        .build();
//  }

}
