package jp.co.falsystack.ssiach17ex.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ProjectConfig {

    @Bean
    protected UserDetailsService userDetailsService() {
        var user1 = User.withUsername("nikolai")
                .password("12345")
                .authorities("read")
                .build();
        var user2 = User.withUsername("julien")
                .password("12345")
                .authorities("write")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
