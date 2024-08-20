package jp.co.falsystack.springsecurityinaction.ssia_ch2_ex5.controller;

import org.springframework.web.bind.annotation.GetMapping;

//@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}
