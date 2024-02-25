package jp.falsystack.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jp.falsystack.userservice.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    private List<ResponseOrder> orders;

    @Builder
    public ResponseUser(String email, String name, String userId, List<ResponseOrder> orders) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.orders = orders == null ? new ArrayList<>() : orders;
    }

    public static ResponseUser fromUser(User user) {
        return ResponseUser.builder()
                .email(user.getEmail())
                .name(user.getName())
                .userId(user.getUserId())
                .orders(new ArrayList<>())
                .build();
    }
}
