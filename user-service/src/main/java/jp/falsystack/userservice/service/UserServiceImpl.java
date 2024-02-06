package jp.falsystack.userservice.service;

import jp.falsystack.userservice.dto.UserDto;
import jp.falsystack.userservice.jpa.UserEntity;
import jp.falsystack.userservice.jpa.UserRepository;
import jp.falsystack.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        String encodedPwd = passwordEncoder.encode(userDto.getPwd());
        userDto.setEncryptedPwd(encodedPwd);

        userRepository.save(userDto.toEntity());

        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        List<ResponseOrder> orders = new ArrayList<>();
        UserDto userDto = UserDto.builder()
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .encryptedPwd(userEntity.getEncryptedPwd())
                .userId(userEntity.getUserId())
                .orders(orders)
                .build();


        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
