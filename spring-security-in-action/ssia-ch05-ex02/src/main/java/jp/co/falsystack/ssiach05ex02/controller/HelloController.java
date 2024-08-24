package jp.co.falsystack.ssiach05ex02.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // @GetMapping("/hello")
    public String hello1(){
        var auth = SecurityContextHolder.getContext().getAuthentication();

        return "Hello " + auth.getName();
    }

    @GetMapping("/hello")
    public String hello2(Authentication auth){

        return "Hello " + auth.getName();
    }
}
