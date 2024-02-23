package jp.falsystack.userservice.service;

import jp.falsystack.userservice.dto.UserDto;
import jp.falsystack.userservice.repository.UserRepository;
import jp.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword("encrypted_password");

        userRepository.save(userDto.toEntity());
        return ResponseUser.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
}
