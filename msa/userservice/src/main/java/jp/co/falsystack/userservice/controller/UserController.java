package jp.co.falsystack.userservice.controller;

import jp.co.falsystack.userservice.dto.UserDto;
import jp.co.falsystack.userservice.service.UserService;
import jp.co.falsystack.userservice.vo.RequestUser;
import jp.co.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody RequestUser user) {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        var userDto = mapper.map(user, UserDto.class);
        var responseUser = mapper.map(userService.createUser(userDto), ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
