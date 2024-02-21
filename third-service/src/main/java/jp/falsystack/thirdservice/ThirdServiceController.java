package jp.falsystack.thirdservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/third-service")
public class ThirdServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Third service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("third-request") String thirdRequest) {
        log.info("thirdRequest = {}", thirdRequest);

        return "Hello World in Third Service";
    }
}
