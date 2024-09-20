package jp.co.falsystack.tacocloud.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/design", "/orders").hasRole("USER")
                            .requestMatchers("/", "/**").permitAll();
                })
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
        return userDetailsManager;

//        InMemory Case
//        var user1 = User.withUsername("user1")
//                .password("{noop}password1")
//                .authorities("ROLE_USER")
//                .build();
//        var user2 = User.withUsername("user2")
//                .password("{noop}password2")
//                .authorities("ROLE_USER")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new NoEncodingPasswordEncoder();
    }
}
