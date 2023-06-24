package jp.falsystack.helloboot;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    // 스프링은 container에 등록된 빈들중에 주입되야 할 HelloService가 있는지 찾는다.
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
