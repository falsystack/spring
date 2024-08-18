package jp.co.falsystack.springsecurityinaction.ssia_ch2_ex5.config;

import jp.co.falsystack.springsecurityinaction.ssia_ch2_ex5.security.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeRequests().anyRequest().authenticated();
        http.authenticationProvider(authenticationProvider);
        return http.build();
    }
}