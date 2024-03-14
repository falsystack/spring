package jp.falsystack.config.autoconfig;

import jp.falsystack.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {

      // old way
//    @Bean
//    public ServerProperties serverProperties(Environment env) {
//        ServerProperties properties = new ServerProperties();
//
//        properties.setContextPath(env.getProperty("contextPath"));
//        properties.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("port"))));
//
//        return properties;
//      }

    // new way
    @Bean
    public ServerProperties serverProperties(Environment env) {
        return Binder.get(env).bind("", ServerProperties.class).get();
    }
}
