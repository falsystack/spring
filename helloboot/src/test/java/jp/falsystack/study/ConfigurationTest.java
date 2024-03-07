package jp.falsystack.study;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.registerBean(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        assertThat(bean1.common).isSameAs(bean2.common);

        MyConfig config1 = ac.getBean(MyConfig.class);
        MyConfig config2 = ac.getBean(MyConfig.class);

        assertThat(config1).isSameAs(config2);

        Bean1 bean1_1 = ac.getBean(Bean1.class);
        Bean1 bean1_2 = ac.getBean(Bean1.class);
        assertThat(bean1_1).isSameAs(bean1_2);
    }

    @Configuration(proxyBeanMethods = false)
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1(Common common) {
            return new Bean1(common);
//            return new Bean1(common());
        }

        @Bean
        Bean2 bean2(Common common) {
            return new Bean2(common);
//            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }
}
