package jp.falsystack.config;

import jp.falsystack.config.autoconfig.DIspatcherServletConfig;
import jp.falsystack.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 원래 기본값은 CLASS
@Target(ElementType.TYPE)
@Import({DIspatcherServletConfig.class, TomcatWebServerConfig.class})
public @interface EnableMyAutoConfiguration {
}
