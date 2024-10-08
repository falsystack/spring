package jp.co.falsystack.sso2client.service;

import jp.co.falsystack.sso2client.converter.ProviderUserRequest;
import jp.co.falsystack.sso2client.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    public CustomOAuth2UserService(UserService userService, UserRepository userRepository) {
        super(userService, userRepository);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        var clientRegistration = userRequest.getClientRegistration();
        var oAuth2UserService = new DefaultOAuth2UserService();
        var oAuth2User = oAuth2UserService.loadUser(userRequest);

        var providerUserRequest = new ProviderUserRequest(clientRegistration, oAuth2User);
        var providerUser = super.providerUser(providerUserRequest);

        // 회원가입
        super.register(providerUser, userRequest);

        return oAuth2User;
    }
}
