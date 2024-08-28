package jp.co.falsystack.ssiach10ex.config;

import jp.co.falsystack.ssiach10ex.repositories.CustomCsrfTokenRepository;
import jp.co.falsystack.ssiach10ex.repositories.JpaTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
public class ProjectConfig {

    @Bean
    public CsrfTokenRepository customCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
        return new CustomCsrfTokenRepository(jpaTokenRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JpaTokenRepository jpaTokenRepository) throws Exception {
        http.csrf(csrf -> csrf.csrfTokenRepository(customCsrfTokenRepository(jpaTokenRepository)).ignoringRequestMatchers("/ciao"));
        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());

        return http.build();
    }
}
