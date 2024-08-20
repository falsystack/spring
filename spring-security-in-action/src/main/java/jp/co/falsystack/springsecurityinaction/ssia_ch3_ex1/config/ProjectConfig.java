package jp.co.falsystack.springsecurityinaction.ssia_ch3_ex1.config;

import jp.co.falsystack.springsecurityinaction.ssia_ch3_ex1.model.User;
import jp.co.falsystack.springsecurityinaction.ssia_ch3_ex1.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = new User("kakao", "12345", "read");
        var users = List.of(user);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
