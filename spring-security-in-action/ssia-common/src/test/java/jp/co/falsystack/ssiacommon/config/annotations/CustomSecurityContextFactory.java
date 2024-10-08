package jp.co.falsystack.ssiacommon.config.annotations;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;


public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser withCustomUser) {

        var context = SecurityContextHolder.createEmptyContext();

        var authenticationToken = new UsernamePasswordAuthenticationToken(withCustomUser.username(), null, null);
        context.setAuthentication(authenticationToken);

        return context;
    }
}
