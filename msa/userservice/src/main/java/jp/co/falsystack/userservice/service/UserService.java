package jp.co.falsystack.userservice.service;

import jp.co.falsystack.userservice.dto.UserDto;
import jp.co.falsystack.userservice.jpa.UserEntity;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(String userId);

    Iterable<UserEntity> getUserByAll();
}
