package jp.falsystack.userservice.service;

import jp.falsystack.userservice.dto.UserDto;
import jp.falsystack.userservice.vo.ResponseUser;

public interface UserService {
    ResponseUser createUser(UserDto userDto);
}
