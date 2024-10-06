package jp.co.falsystack.userservice.controller;

import jp.co.falsystack.userservice.dto.UserDto;
import jp.co.falsystack.userservice.jpa.UserEntity;
import jp.co.falsystack.userservice.service.UserService;
import jp.co.falsystack.userservice.vo.RequestUser;
import jp.co.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {

    private final Environment env;
    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User service on PORT %s", env.getProperty("local.server.port"));
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

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        var userList = userService.getUserByAll();
        var result = new ArrayList<ResponseUser>();
        for (var user : userList) {
            result.add(new ModelMapper().map(user, ResponseUser.class));
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId) {
        var userDto = userService.getUserById(userId);

        var responseUser = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }
}
