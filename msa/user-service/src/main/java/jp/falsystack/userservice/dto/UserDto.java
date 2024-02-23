package jp.falsystack.userservice.dto;

import jp.falsystack.userservice.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDate createdAt;

    private String encryptedPassword;

    @Builder
    public UserDto(String email, String name, String password, String userId, LocalDate createdAt, String encryptedPassword) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.createdAt = createdAt;
        this.encryptedPassword = encryptedPassword;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .encryptedPassword(encryptedPassword)
                .userId(userId)
                .build();
    }
}
