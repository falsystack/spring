package jp.co.falsystack.ss6inflearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(form ->
                        form
//                                .loginPage("/login")
                                .loginProcessingUrl("/loginProc")
                                .defaultSuccessUrl("/", true) // true 이면 무조건 지정한 url 로 리디렉트 되지만 false 일 경우 이전 요청 url 로 리디렉트 될 수 있다
                                .failureUrl("/failed")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                // defaultSuccessUrl 의 설정을 override 한다.
                                .successHandler((request, response, authentication) -> {
                                    System.out.println("authentication = " + authentication.getPrincipal().toString());
                                    response.sendRedirect("/home");
                                })
                                // failureUrl 의 설정을 override 한다.
                                .failureHandler((request, response, exception) -> {
                                    System.out.println("exception = " + exception.getMessage());
                                    response.sendRedirect("/login");
                                })
                                .permitAll()
                );

        return http.build();
    }
}
