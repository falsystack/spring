package jp.falsystack.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@ComponentScan
@Retention(RetentionPolicy.RUNTIME) // 원래 기본값은 CLASS
@Target(ElementType.TYPE)
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}