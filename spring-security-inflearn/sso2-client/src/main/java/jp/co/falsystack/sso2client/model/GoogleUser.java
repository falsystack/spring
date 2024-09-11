package jp.co.falsystack.sso2client.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends OAuth2ProviderUser {

    private static final String SUB = "sub";

    public GoogleUser(OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        // google の場合は id も username　も sub で取得できる
        return getAttributes().get(SUB).toString();
    }

    @Override
    public String getUsername() {
        // google の場合は id も username　も sub で取得できる
        return getAttributes().get(SUB).toString();
    }
}
