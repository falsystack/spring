package jp.co.falsystack.sso2client.service;

import jp.co.falsystack.sso2client.converter.ProviderUserConverter;
import jp.co.falsystack.sso2client.converter.ProviderUserRequest;
import jp.co.falsystack.sso2client.model.ProviderUser;
import jp.co.falsystack.sso2client.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public abstract class AbstractOAuth2UserService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {

        return providerUserConverter.converter(providerUserRequest);
    }

    protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
        var foundUser = userRepository.findByUsername(providerUser.getUsername());
        if (foundUser.isEmpty()) {
            var registrationId = userRequest.getClientRegistration().getClientId();
            userService.register(registrationId, providerUser);
            return;
        }

        System.out.println("foundUser = " + foundUser.get());
    }
}
