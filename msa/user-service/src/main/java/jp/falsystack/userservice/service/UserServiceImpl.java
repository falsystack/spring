package jp.falsystack.userservice.service;

import jp.falsystack.userservice.dto.UserDto;
import jp.falsystack.userservice.entity.User;
import jp.falsystack.userservice.repository.UserRepository;
import jp.falsystack.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseUser createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userDto.toEntity());
        return ResponseUser.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .orders(new ArrayList<>())
                .build();
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseUser.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .orders(new ArrayList<>())
                .build();
    }

    @Override
    public List<ResponseUser> getUserByAll() {
        return userRepository.findAll().stream().map(ResponseUser::fromUser).toList();
    }
}
