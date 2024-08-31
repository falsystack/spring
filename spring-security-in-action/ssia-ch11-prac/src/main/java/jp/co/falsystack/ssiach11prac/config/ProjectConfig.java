package jp.co.falsystack.ssiach11prac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity(debug = true)
@Configuration
public class ProjectConfig {

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
        return security -> security.ignoring()
                .requestMatchers(
                        mvc.pattern("/favicon.ico"),
                        mvc.pattern("/error"),
                        mvc.pattern("/h2-console/**")
                )
                .requestMatchers(toH2Console());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(custom -> custom.disable());
        http.authorizeHttpRequests(custom -> custom.requestMatchers("/h2-console/**").permitAll());
        http.authorizeHttpRequests(custom -> custom.anyRequest().permitAll());

        return http.build();
    }
}
