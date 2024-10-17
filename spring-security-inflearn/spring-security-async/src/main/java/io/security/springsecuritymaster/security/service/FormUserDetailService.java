package io.security.springsecuritymaster.security.service;

import io.security.springsecuritymaster.domain.dto.AccountContext;
import io.security.springsecuritymaster.domain.dto.AccountDto;
import io.security.springsecuritymaster.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service("userDetailsService")
@Service
@RequiredArgsConstructor
public class FormUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = userRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        var authority = new SimpleGrantedAuthority(account.getRoles());
        var mapper = new ModelMapper();
        var accountDto = mapper.map(account, AccountDto.class);

        return new AccountContext(accountDto, List.of(authority));
    }
}
