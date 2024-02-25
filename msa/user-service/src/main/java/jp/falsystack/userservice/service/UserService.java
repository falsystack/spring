package jp.falsystack.userservice.service;

import jp.falsystack.userservice.dto.UserDto;
import jp.falsystack.userservice.vo.ResponseUser;

import java.util.List;

public interface UserService {
    ResponseUser createUser(UserDto userDto);
    ResponseUser getUserByUserId(String userId);

    List<ResponseUser> getUserByAll();
}
