package jp.falsystack.helloboot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
@RequestMapping("/hello")
public class HelloController {

    private final HelloService helloService;

    @ResponseBody
    @GetMapping
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
