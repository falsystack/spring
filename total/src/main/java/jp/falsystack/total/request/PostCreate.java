package jp.falsystack.total.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCreate {

  @NotBlank(message = "타이틀은 필수 입니다.")
  private final String title;
  @NotBlank(message = "내용은 필수 입니다.")
  private final String content;

  @Builder
  public PostCreate(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
