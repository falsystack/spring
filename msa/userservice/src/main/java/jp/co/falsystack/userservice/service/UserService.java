package jp.co.falsystack.userservice.service;

import jp.co.falsystack.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
}
