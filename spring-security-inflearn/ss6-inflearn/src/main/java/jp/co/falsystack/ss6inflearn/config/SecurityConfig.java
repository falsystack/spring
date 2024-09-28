package jp.co.falsystack.ss6inflearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/anonymous").hasRole("GUEST")
                        .requestMatchers("/logoutSuccess", "/anonymousContext", "/authentication").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
//                .csrf(csrf -> csrf.disable())
                .logout(logout -> logout
//                        .logoutUrl("/logoutProc")
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc", "POST"))
                        .logoutSuccessUrl("/logoutSuccess")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            // 복잡한 설정이 필요할 경우 logoutSuccessHandler 를 사용
                            System.out.println("logoutSuccessHandler");
                            response.sendRedirect("/logoutSuccess");
                        })
                        .deleteCookies("JSESSIONID", "remember-me")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .addLogoutHandler((request, response, authentication) -> {
                            System.out.println("addLogoutHandler");
                            request.getSession().invalidate();
                            SecurityContextHolder.getContext().setAuthentication(null);
                            SecurityContextHolder.getContextHolderStrategy().clearContext();
                        })
                );

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        var user = User.withUsername("kakao")
                .password("{noop}kakao")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
