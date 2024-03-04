package jp.falsystack.helloboot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final HelloService helloService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
