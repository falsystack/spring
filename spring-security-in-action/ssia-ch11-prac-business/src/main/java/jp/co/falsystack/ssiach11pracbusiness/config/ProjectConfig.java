package jp.co.falsystack.ssiach11pracbusiness.config;

import jp.co.falsystack.ssiach11pracbusiness.authentication.filters.InitialAuthenticationFilter;
import jp.co.falsystack.ssiach11pracbusiness.authentication.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class ProjectConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    protected InitialAuthenticationFilter initialAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return new InitialAuthenticationFilter(authenticationManager(authenticationConfiguration));
//    }
//
//    @Bean
//    protected AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
}
