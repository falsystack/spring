package jp.co.falsystack.ssiach10ex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(cors -> cors.configurationSource(configurationSource()));
        http.authorizeHttpRequests(custom -> custom.anyRequest().permitAll());
        return http.build();
    }

    CorsConfigurationSource configurationSource() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:8080"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

//    @Bean
//    public CsrfTokenRepository customCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
//        return new CustomCsrfTokenRepository(jpaTokenRepository);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, JpaTokenRepository jpaTokenRepository) throws Exception {
//        http.csrf(csrf -> csrf.csrfTokenRepository(customCsrfTokenRepository(jpaTokenRepository)).ignoringRequestMatchers("/ciao"));
//        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
//
//        return http.build();
//    }
}
