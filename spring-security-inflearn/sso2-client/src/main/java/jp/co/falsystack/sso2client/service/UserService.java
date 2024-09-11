package jp.co.falsystack.sso2client.service;

import jp.co.falsystack.sso2client.model.ProviderUser;
import jp.co.falsystack.sso2client.model.User;
import jp.co.falsystack.sso2client.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void register(String registrationId, ProviderUser providerUser) {
        userRepository.register(User.builder()
                .id(providerUser.getId())
                .registrationId(registrationId)
                .username(providerUser.getUsername())
                .provider(providerUser.getProvider())
                .email(providerUser.getEmail())
                .authorities(providerUser.getAuthorities())
                .build());
    }

}
