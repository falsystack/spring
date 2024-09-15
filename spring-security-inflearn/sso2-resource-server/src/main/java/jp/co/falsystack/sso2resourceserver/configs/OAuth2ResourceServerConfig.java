package jp.co.falsystack.sso2resourceserver.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPairGenerator;

@RequiredArgsConstructor
@Configuration
public class OAuth2ResourceServerConfig {

    private final OAuth2ResourceServerProperties properties;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        http.oauth2ResourceServer(config -> config.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    protected JwtDecoder jwtDecoder() {
        // return JwtDecoders.fromIssuerLocation(properties.getJwt().getIssuerUri());
        return JwtDecoders.fromOidcIssuerLocation(properties.getJwt().getIssuerUri());
    }

}
