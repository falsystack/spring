package jp.co.falsystack.ssiach18final.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Collections;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerConfig {

    @Value("${claim.aud}")
    private String claimAud;

    @Value("${jwkSetUri}")
    private String urlJwk;

    // 스프링 시큐리티 6.1.3 이상부터 필요하다.
    @Bean
    MvcRequestMatcher.Builder mvc(
            HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    // 스프링 시큐리티를 적용하지 않을 대상들
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(
            MvcRequestMatcher.Builder mvc) {
        return web -> web.ignoring()
                .requestMatchers(
                        mvc.pattern("/favicon.ico"),
                        mvc.pattern("/error"),
                        mvc.pattern("/h2-console/**")
                )
                .requestMatchers(toH2Console());
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(oauth2 -> {
            oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()).jwkSetUri(urlJwk));
        });
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var decoder = NimbusJwtDecoder.withJwkSetUri(urlJwk).build();
        MappedJwtClaimSetConverter converter = MappedJwtClaimSetConverter
                .withDefaults(Collections.singletonMap("aud", custom -> claimAud));
        decoder.setClaimSetConverter(converter);
        return decoder;
    }
}
