package jp.falsystack.userservice.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    @Builder
    public ResponseUser(String email, String name, String userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }
}
