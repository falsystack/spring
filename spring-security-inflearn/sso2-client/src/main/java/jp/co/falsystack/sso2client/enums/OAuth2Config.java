package jp.co.falsystack.sso2client.enums;

import lombok.Getter;

public class OAuth2Config {

    @Getter
    public enum SocialType {
        GOOGLE("google"),
        KEYCLOAK("keycloak");

        SocialType(String name) {
        }
    }

}
