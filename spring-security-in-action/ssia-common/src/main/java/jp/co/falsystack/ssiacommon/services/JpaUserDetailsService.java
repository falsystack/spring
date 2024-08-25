package jp.co.falsystack.ssiacommon.services;

import jp.co.falsystack.ssiacommon.model.CustomUserDetails;
import jp.co.falsystack.ssiacommon.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Problem during authentication");

        var user = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(user);
    }
}
