package jp.co.falsystack.ss6inflearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling(exception -> {
            exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                System.out.println("exception = " + accessDeniedException.getMessage());
                response.sendRedirect("/login");
            });
            exception.authenticationEntryPoint((request, response, authException) -> {
                System.out.println("exception = " + authException.getMessage());
                response.sendRedirect("/login");
            });
        });

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        var user = User.withUsername("kakao")
                .password("{noop}kakao")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
