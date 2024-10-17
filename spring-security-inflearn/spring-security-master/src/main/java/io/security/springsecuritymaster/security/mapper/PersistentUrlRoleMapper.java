package io.security.springsecuritymaster.security.mapper;

import io.security.springsecuritymaster.admin.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersistentUrlRoleMapper implements UrlRoleMapper{

    private final LinkedHashMap<String, String> urlRoleMappings = new LinkedHashMap<>();
    private final ResourcesRepository resourcesRepository;

    @Override
    public Map<String, String> getUrlRoleMappings() {
        urlRoleMappings.clear();
        var resources = resourcesRepository.findAllResources();
        resources.forEach(re -> {
            re.getRoleSet().forEach(role -> {
                urlRoleMappings.put(re.getResourceName(), role.getRoleName());
            });
        });

        return urlRoleMappings;
    }
}
