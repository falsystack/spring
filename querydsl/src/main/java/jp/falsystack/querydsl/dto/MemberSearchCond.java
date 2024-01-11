package jp.falsystack.querydsl.dto;

import lombok.Data;

@Data
public class MemberSearchCond {

    private String username;
    private String teamName;
    private Integer ageLoe;
    private Integer ageGoe;
}
