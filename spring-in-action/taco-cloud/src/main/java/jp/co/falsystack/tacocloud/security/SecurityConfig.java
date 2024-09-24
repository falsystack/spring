package jp.co.falsystack.tacocloud.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/design", "/orders").hasRole("USER")
                            .requestMatchers("/", "/**").permitAll();
                })
                .formLogin(form -> form.loginPage("/login"))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
                .userDetailsService(userDetailsService)
                .httpBasic(Customizer.withDefaults());
        http.csrf(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }

//    @Bean
//    protected UserDetailsService userDetailsService() {
//        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
//        return userDetailsManager;
//    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
