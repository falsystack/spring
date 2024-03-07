package jp.falsystack.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 동적으로 파일을 Import することができる
public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "jp.falsystack.config.autoconfig.DispatcherServletConfig",
                "jp.falsystack.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
