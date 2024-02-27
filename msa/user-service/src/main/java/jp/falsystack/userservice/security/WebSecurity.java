package jp.falsystack.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.falsystack.userservice.entity.User;
import jp.falsystack.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class WebSecurity {

    private final ObjectMapper objectMapper;

    @Bean
    MvcRequestMatcher.Builder mvc(
            HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(
            MvcRequestMatcher.Builder mvc) {
        return web -> web.ignoring()
                .requestMatchers(
                        mvc.pattern("/favicon.ico"),
                        mvc.pattern("/error")
                )
                .requestMatchers(toH2Console());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        return http.authorizeHttpRequests(
                        authorizeHttpRequest -> {
                            authorizeHttpRequest.requestMatchers(
                                            mvc.pattern("/user-service/**")
                                    ).permitAll()
                                    .anyRequest().authenticated();
                        }
                ).addFilter(getAuthenticationFilter(authenticationManager, objectMapper)).csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByEmail(username).orElseThrow(() ->
                    // 스프링이 기본제공하는 예외 사용
                    new UsernameNotFoundException(username + "을 찾을 수 없습니다."));
            return new UserPrincipal(user);
        };
    }

    private AuthenticationFilter getAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(objectMapper);
        authenticationFilter.setAuthenticationManager(authenticationManager);
        return authenticationFilter;
    }

}
