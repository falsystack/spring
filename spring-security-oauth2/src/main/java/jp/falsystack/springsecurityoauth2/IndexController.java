package jp.falsystack.springsecurityoauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public IndexController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/user")
    public OAuth2User user(Authentication authentication) {
        // OAuth2AuthenticationToken 을 가져오는 두 가지 방법
        OAuth2AuthenticationToken authenticationToken1 = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthenticationToken authenticationToken2 = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        OAuth2User oauth2User = authenticationToken1.getPrincipal();
        return oauth2User;
    }

    @GetMapping("/oauth2User")
    public OAuth2User oauth2User(@AuthenticationPrincipal OAuth2User oauth2User) {
        System.out.println("oauth2User = " + oauth2User);
        return oauth2User;
    }

    @GetMapping("/oidcUser")
    public OAuth2User oidcUser(@AuthenticationPrincipal OidcUser oidcUser) {
        System.out.println("oidcUser = " + oidcUser);
        return oidcUser;
    }
}
