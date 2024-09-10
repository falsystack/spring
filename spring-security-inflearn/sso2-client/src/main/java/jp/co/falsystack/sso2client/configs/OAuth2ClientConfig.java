package jp.co.falsystack.sso2client.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class OAuth2ClientConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(httpRequest -> httpRequest.anyRequest().permitAll());
//                .requestMatchers("/login","/user").permitAll()
//                .requestMatchers(HttpMethod.POST,"/user").permitAll()
//                .anyRequest().authenticated());
//        http.oauth2Login(oauth -> oauth.loginPage("/login"));

        return http.build();
    }
}
