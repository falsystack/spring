package jp.falsystack.userservice.dto;

import jp.falsystack.userservice.jpa.UserEntity;
import jp.falsystack.userservice.vo.ResponseOrder;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDate createdAt;

    private String encryptedPwd;
    private List<ResponseOrder> orders;

    @Builder
    public UserDto(String email, String name, String pwd, String userId, LocalDate createdAt, String encryptedPwd, List<ResponseOrder> orders) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.userId = userId;
        this.createdAt = createdAt;
        this.encryptedPwd = encryptedPwd;
        this.orders = orders;
    }


    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(this.email)
                .name(this.name)
                .userId(this.userId)
                .encryptedPwd(this.encryptedPwd == null ? this.pwd : this.encryptedPwd)
                .build();
    }
}
