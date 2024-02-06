package jp.falsystack.userservice.controller;

import jp.falsystack.userservice.dto.UserDto;
import lombok.Data;

@Data
public class RequestUser {

//    @NotNull(message = "Email cannot be null")
//    @Size(min = 2, message = "Email not be less than")
    private String email;
    private String pwd;
    private String name;

    public UserDto toUserDto() {
        return UserDto.builder()
                .email(this.email)
                .pwd(this.pwd)
                .name(this.name)
                .build();
    }
}
