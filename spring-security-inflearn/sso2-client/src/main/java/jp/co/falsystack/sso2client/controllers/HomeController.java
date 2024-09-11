package jp.co.falsystack.sso2client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home2")
    public String home() {
        return "home2";
    }
}
