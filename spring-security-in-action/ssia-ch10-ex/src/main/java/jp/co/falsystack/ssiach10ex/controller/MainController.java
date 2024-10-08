package jp.co.falsystack.ssiach10ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
public class MainController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

//    @CrossOrigin
    @PostMapping("/test")
    @ResponseBody
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }
}
