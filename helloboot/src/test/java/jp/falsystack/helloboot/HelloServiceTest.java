package jp.falsystack.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Spring");

        assertThat(ret).isEqualTo("Hello Spring");
    }

}