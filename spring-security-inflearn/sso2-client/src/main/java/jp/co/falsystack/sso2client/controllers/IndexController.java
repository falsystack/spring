package jp.co.falsystack.sso2client.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class IndexController {

    private final ClientRegistrationRepository clientRegistrationRepository;

    //    @GetMapping("/")
    public String index() {
        return "index";
    }

    //    @GetMapping("/")
    public String getClientRegistrations() {
//        var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
//
//        clientRegistration.getClientId();
//        return clientRegistration.getRegistrationId();
        return "";
    }

    @GetMapping("/user")
    public OAuth2User user(String accessToken) {
        var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
        var oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.now().plusSeconds(60 * 30));

        var userService = new DefaultOAuth2UserService();
        var oAuth2User = userService.loadUser(new OAuth2UserRequest(clientRegistration, oAuth2AccessToken));

        return oAuth2User;
    }

    @GetMapping("/oidc")
    public OAuth2User oidc(String accessToken, String idToken) {
        var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
        var oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, accessToken, Instant.now(), Instant.now().plusSeconds(60 * 30));
        var oidcIdToken = new OidcIdToken(idToken, Instant.now(), Instant.now().plusSeconds(60 * 30), Map.of(
                IdTokenClaimNames.ISS, "http://localhost:8080/realms/oauth2",
                IdTokenClaimNames.SUB, "OIDC0",
                "preferred_username", "user"
        ));

        var oidcUserService = new OidcUserService();
        var oidcUser = oidcUserService.loadUser(new OidcUserRequest(clientRegistration, oAuth2AccessToken, oidcIdToken));

        return oidcUser;
    }
}
