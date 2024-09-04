package jp.co.falsystack.ssiacommon.config.annotations;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@WithSecurityContext(factory = CustomSecurityContextFactory.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithCustomUser {

    String username();
}
