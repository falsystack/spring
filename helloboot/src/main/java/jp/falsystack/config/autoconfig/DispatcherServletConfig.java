package jp.falsystack.config.autoconfig;

import jp.falsystack.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

// このConfigurationを @MyAutoConfiguration アノテーションで使っていると言う意味で付けておく
// 慣例（convention）である
@MyAutoConfiguration
public class DispatcherServletConfig {

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

}
