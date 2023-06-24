package jp.falsystack.helloboot;

import java.util.Objects;

public class HelloController {

    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();
        // null check -> Objects.requireNonNull(obj)
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
