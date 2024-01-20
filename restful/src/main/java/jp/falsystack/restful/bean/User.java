package jp.falsystack.restful.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(value = {"password", "ssn"})
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;
    @Size(min = 2, message = "Name은 두 글자 이상 입력해 주세요")
    private String name;
    @Past(message = "등록은 미래 날짜를 입력할 수 없습니다")
    private LocalDate joinDate;

    private String password;
    private String ssn;

    public AdminUser toAdminUser() {
        return AdminUser.builder()
                .id(getId())
                .name(getName())
                .joinDate(getJoinDate())
                .password(getPassword())
                .ssn(getSsn())
                .build();
    }

}
