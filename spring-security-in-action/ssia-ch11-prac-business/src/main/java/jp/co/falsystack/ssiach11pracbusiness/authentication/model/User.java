package jp.co.falsystack.ssiach11pracbusiness.authentication.model;

import lombok.Data;

@Data
public class User {

    private String username;
    private String password;
    private String code;

}
