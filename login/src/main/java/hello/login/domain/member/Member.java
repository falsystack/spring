package hello.login.domain.member;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Member {

  private Long id;

  @NotEmpty
  private String loginId;

  @NotEmpty
  private String name;

  @NotEmpty
  private String password;

  public Member(String loginId, String name, String password) {
    this.loginId = loginId;
    this.name = name;
    this.password = password;
  }
}
