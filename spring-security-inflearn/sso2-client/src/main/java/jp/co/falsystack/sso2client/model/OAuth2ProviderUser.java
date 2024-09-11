package jp.co.falsystack.sso2client.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class OAuth2ProviderUser implements ProviderUser {

    private final Map<String, Object> attributes;
    private final OAuth2User oAuth2User;
    private final ClientRegistration clientRegistration;

    public OAuth2ProviderUser(Map<String, Object> attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        this.attributes = attributes;
        this.oAuth2User = oAuth2User;
        this.clientRegistration = clientRegistration;
    }

    @Override
    public String getPassword() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getProvider() {
        return this.clientRegistration.getRegistrationId();
    }

    @Override
    public String getEmail() {
        return this.oAuth2User.getAttribute("email");
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return this.oAuth2User.getAuthorities().stream().toList();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}
