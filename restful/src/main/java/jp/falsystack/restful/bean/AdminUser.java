package jp.falsystack.restful.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonFilter("UserInfo")
public class AdminUser {
    private Long id;
    @Size(min = 2, message = "Name은 두 글자 이상 입력해 주세요")
    private String name;
    @Past(message = "등록은 미래 날짜를 입력할 수 없습니다")
    private LocalDate joinDate;

    private String password;
    private String ssn;

    @Builder
    public AdminUser(Long id, String name, LocalDate joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
