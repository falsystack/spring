package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class JavaSystemProperties {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for (Object k : properties.keySet()) {
            log.info("prop = {} = {}", k, System.getProperty(String.valueOf(k)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        log.info("url = {}", url);
        log.info("username = {}", username);
        log.info("password = {}", password);
    }
}
