package jp.co.falsystack.sso2client.model.social;

import jp.co.falsystack.sso2client.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class KeycloakUser extends OAuth2ProviderUser {
    public KeycloakUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return getAttributes().get("sub").toString();
    }

    @Override
    public String getUsername() {
        return getAttributes().get("preferred_username").toString();
    }
}
