package jp.co.falsystack.ssiach11pracbusiness.config;

import jp.co.falsystack.ssiach11pracbusiness.authentication.filters.InitialAuthenticationFilter;
import jp.co.falsystack.ssiach11pracbusiness.authentication.filters.JwtAuthenticationFilter;
import jp.co.falsystack.ssiach11pracbusiness.authentication.providers.UsernamePasswordAuthenticationProvider;
import jp.co.falsystack.ssiach11pracbusiness.authentication.proxy.OtpAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final InitialAuthenticationFilter initialAuthenticationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Bean
    protected SecurityFilterChain setAuthenticationProvider(HttpSecurity http) throws Exception {
        return http.authenticationProvider(otpAuthenticationProvider)
                .authenticationProvider(usernamePasswordAuthenticationProvider)
                .build();
    }

    @Bean
    protected SecurityFilterChain configureFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(new InitialAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()), BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().authenticated()).build();
    }


}
