package jp.falsystack.userservice.controller;

import jp.falsystack.userservice.service.UserService;
import jp.falsystack.userservice.vo.RequestUser;
import jp.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service on %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return message;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public ResponseUser createUser(@RequestBody RequestUser user) {
        return userService.createUser(user.toUserDto());
    }

    @GetMapping("/users")
    public List<ResponseUser> getUsers() {
        return userService.getUserByAll();
    }

    @GetMapping("/users/{userId}")
    public ResponseUser getUser(@PathVariable String userId) {
        return userService.getUserByUserId(userId);
    }
}
