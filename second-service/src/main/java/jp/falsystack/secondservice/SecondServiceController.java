package jp.falsystack.secondservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/second-service")
public class SecondServiceController {

    private final Environment env;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Second service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String secondRequest) {
        log.info("secondRequest = {}", secondRequest);

        return "Hello World in Second Service";
    }

    @GetMapping("/check")
    public String check() {
        return String.format("Hi, there. This is a message from Second Service on PORT %s", env.getProperty("local.server.port"));
    }
}

