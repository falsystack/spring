package jp.co.falsystack.sso2client.configs;

import jp.co.falsystack.sso2client.service.CustomOAuth2UserService;
import jp.co.falsystack.sso2client.service.CustomOidcUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**"));
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(httpReq ->
                httpReq.requestMatchers("/").permitAll()
                        .requestMatchers("/api/user").access(new WebExpressionAuthorizationManager("hasAnyRole('SCOPE_profile', 'SCOPE_email')"))
                        .requestMatchers("/api/oidc").access(new WebExpressionAuthorizationManager("hasAnyRole('SCOPE_openid')"))
                        .anyRequest().authenticated());

        http.oauth2Login(loginConfig ->
                loginConfig.userInfoEndpoint(customizer ->
                        customizer.userService(customOAuth2UserService)
                                .oidcUserService(customOidcUserService)));
        http.logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/login"));
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper customAuthoritiesMapper() {
        return new CustomAuthorityMapper();
    }
}
