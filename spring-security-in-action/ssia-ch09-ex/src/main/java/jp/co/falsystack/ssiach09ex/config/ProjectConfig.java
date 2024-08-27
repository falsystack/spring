package jp.co.falsystack.ssiach09ex.config;

import jp.co.falsystack.ssiach09ex.filters.AuthenticationLoggingFilter;
import jp.co.falsystack.ssiach09ex.filters.RequestValidationFilter;
import jp.co.falsystack.ssiach09ex.filters.StaticKeyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(req -> req.anyRequest().permitAll());
        return http.build();
    }
}
