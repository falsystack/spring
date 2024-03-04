package jp.falsystack.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Spring");

        assertThat(ret).isEqualTo("Spring");
    }

    @Test
    void failHelloController() {
        HelloController helloController = new HelloController(name -> name);

        assertThatThrownBy(() -> helloController.hello(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> helloController.hello(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

}