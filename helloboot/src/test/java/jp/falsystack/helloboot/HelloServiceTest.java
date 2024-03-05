package jp.falsystack.helloboot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

@Test
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface UniTest {
}

class HelloServiceTest {

    @UniTest
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Spring");

        assertThat(ret).isEqualTo("Hello Spring");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test");

        assertThat(ret).isEqualTo("*Test*");
    }

}