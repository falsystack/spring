package jp.co.falsystack.sso2client.service;

import jp.co.falsystack.sso2client.model.GoogleUser;
import jp.co.falsystack.sso2client.model.KeycloakUser;
import jp.co.falsystack.sso2client.model.ProviderUser;
import jp.co.falsystack.sso2client.model.User;
import jp.co.falsystack.sso2client.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
public abstract class AbstractOAuth2UserService {

    private final UserService userService;
    private final UserRepository userRepository;

    protected ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        var registrationId = clientRegistration.getRegistrationId();
        if (registrationId.equals("keycloak")) {
            return new KeycloakUser(oAuth2User, clientRegistration);
        }

        if (registrationId.equals("google")) {
            return new GoogleUser(oAuth2User, clientRegistration);
        }

        return null;
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
