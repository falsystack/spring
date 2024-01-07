package jp.falsystack.datajpa.repository;

import lombok.Getter;

@Getter
public class UsernameOnlyDto {

    private final String username;

    // parameter 이름명으로 매칭
    public UsernameOnlyDto(String username) {
        this.username = username;
    }
}
