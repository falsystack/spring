package jp.co.falsystack.ssiach09ex.config;

import jp.co.falsystack.ssiach09ex.filters.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(req -> req.anyRequest().permitAll());
        return http.build();
    }
}
