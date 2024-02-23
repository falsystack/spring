package jp.falsystack.userservice.controller;

import jp.falsystack.userservice.service.UserService;
import jp.falsystack.userservice.vo.RequestUser;
import jp.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${greeting.message}")
    private String message;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service";
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
}
