package jp.falsystack.userservice.controller;

import jp.falsystack.userservice.service.UserService;
import jp.falsystack.userservice.vo.Greeting;
import jp.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final Environment env;
    private final Greeting greeting;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        String uuid = UUID.randomUUID().toString();
        userService.createUser(user.toUserDto());

        ResponseUser responseUser = ResponseUser.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(uuid)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
