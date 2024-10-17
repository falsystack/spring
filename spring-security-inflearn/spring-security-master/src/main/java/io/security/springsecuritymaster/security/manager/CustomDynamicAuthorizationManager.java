package io.security.springsecuritymaster.security.manager;

import io.security.springsecuritymaster.admin.repository.ResourcesRepository;
import io.security.springsecuritymaster.security.mapper.MapBasedUrlRoleMapper;
import io.security.springsecuritymaster.security.mapper.PersistentUrlRoleMapper;
import io.security.springsecuritymaster.security.service.DynamicAuthorizationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CustomDynamicAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappings;
    //    private static final AuthorizationDecision DENY = new AuthorizationDecision(false);
    private static final AuthorizationDecision ACCESS = new AuthorizationDecision(true);
    private final HandlerMappingIntrospector handlerMappingIntrospector;
    private final ResourcesRepository resourcesRepository;
    private DynamicAuthorizationService dynamicAuthorizationService;

    @PostConstruct
    public void mapping() {
//        var dynamicAuthorizationService = new DynamicAuthorizationService(new MapBasedUrlRoleMapper());
        dynamicAuthorizationService = new DynamicAuthorizationService(new PersistentUrlRoleMapper(resourcesRepository));
        setMapping();
    }

    private void setMapping() {
        mappings = dynamicAuthorizationService.getUrlRoleMappings()
                .entrySet().stream()
                .map(entry -> new RequestMatcherEntry<>(
                        new MvcRequestMatcher(handlerMappingIntrospector, entry.getKey()),
                        customAuthorizationManager(entry.getValue())
                )).toList();
    }

    private AuthorizationManager<RequestAuthorizationContext> customAuthorizationManager(String role) {
        if (role != null) {
            if (role.startsWith("ROLE_")) {
                return AuthorityAuthorizationManager.hasAuthority(role);
            }
            // 표현식
            return new WebExpressionAuthorizationManager(role);
        }
        return null;
    }


    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        // RequestMatcherDelegatingAuthorizationManager.check 참고
        for (RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> mapping : this.mappings) {

            RequestMatcher matcher = mapping.getRequestMatcher();
            RequestMatcher.MatchResult matchResult = matcher.matcher(context.getRequest());
            if (matchResult.isMatch()) {
                AuthorizationManager<RequestAuthorizationContext> manager = mapping.getEntry();

                return manager.check(authentication,
                        new RequestAuthorizationContext(context.getRequest(), matchResult.getVariables()));
            }
        }
        return ACCESS;
    }

    public synchronized void reload() {
        mappings.clear();
        setMapping();
    }
}
