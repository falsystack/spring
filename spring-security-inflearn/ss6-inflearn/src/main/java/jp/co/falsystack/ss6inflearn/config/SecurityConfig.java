package jp.co.falsystack.ss6inflearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("seoul");

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/anonymous").hasRole("GUEST")
                        .requestMatchers("/logoutSuccess", "/anonymousContext", "/authentication").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> {
                    form.successHandler((request, response, authentication) -> {
                        // 인증전의 저장된 요청을 가져올 수 있다.
                        var savedRequest = requestCache.getRequest(request, response);
                        response.sendRedirect(savedRequest.getRedirectUrl());
                    });
                })
                .requestCache(cache -> cache
                        .requestCache(requestCache));

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
