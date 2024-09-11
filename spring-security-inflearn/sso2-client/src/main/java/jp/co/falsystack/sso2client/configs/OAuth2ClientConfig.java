package jp.co.falsystack.sso2client.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**", "/static/scss/**"));
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(httpReq ->
                httpReq.requestMatchers("/").permitAll()
                        .anyRequest().authenticated());

        http.oauth2Login(Customizer.withDefaults());
        http.logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/login"));
        return http.build();
    }
}
