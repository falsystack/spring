package jp.co.falsystack.userservice.service;

import jp.co.falsystack.userservice.dto.UserDto;
import jp.co.falsystack.userservice.jpa.UserEntity;
import jp.co.falsystack.userservice.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        var userEntity = mapper.map(userDto, UserEntity.class);
        var encodedPassword = passwordEncoder.encode(userDto.getPwd());
        userEntity.setEncryptedPassword(encodedPassword);

        var savedUser = userRepository.save(userEntity);
        return mapper.map(savedUser, UserDto.class);
    }
}
