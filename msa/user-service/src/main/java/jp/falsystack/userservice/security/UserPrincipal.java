package jp.falsystack.userservice.security;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class UserPrincipal extends User {

    public UserPrincipal(jp.falsystack.userservice.entity.User user) {
        super(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}
