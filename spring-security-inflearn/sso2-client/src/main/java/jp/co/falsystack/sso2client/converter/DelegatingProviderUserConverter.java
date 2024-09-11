package jp.co.falsystack.sso2client.converter;

import jp.co.falsystack.sso2client.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    private final List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        this.converters = List.of(new OAuth2GoogleProviderUserConverter(), new OAuth2KeycloakProviderUserConverter());
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        Assert.notNull(providerUserRequest, "providerUserRequest must not be null");
        for (var converter : converters) {
            return converter.converter(providerUserRequest);
        }

        return null;
    }

}
