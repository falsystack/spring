package jp.co.falsystack.ssiach16ex.controllers;

import jp.co.falsystack.ssiach16ex.services.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + nameService.getName();
    }
}
