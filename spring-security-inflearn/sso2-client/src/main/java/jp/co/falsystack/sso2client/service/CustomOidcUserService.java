package jp.co.falsystack.sso2client.service;

import jp.co.falsystack.sso2client.repository.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    public CustomOidcUserService(UserService userService, UserRepository userRepository) {
        super(userService, userRepository);
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        var clientRegistration = userRequest.getClientRegistration();
        var oidcUserService = new OidcUserService();
        var oidcUser = oidcUserService.loadUser(userRequest);

        var providerUser = super.providerUser(clientRegistration, oidcUser);
        super.register(providerUser, userRequest);

        return oidcUser;
    }
}
