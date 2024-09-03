package jp.co.falsystack.ssiach16ex.controllers;

import jp.co.falsystack.ssiach16ex.services.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final NameService nameService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello " + nameService.getName();
    }

    @GetMapping("/secret/names/{name}")
    public List<String> names(@PathVariable String name) {
        return nameService.getSecretNames(name);
    }
}
