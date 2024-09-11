package jp.co.falsystack.sso2client.converter;

import jp.co.falsystack.sso2client.enums.OAuth2Config;
import jp.co.falsystack.sso2client.model.ProviderUser;
import jp.co.falsystack.sso2client.model.social.GoogleUser;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE)) {
            return null;
        }

        return new GoogleUser();
    }
}
