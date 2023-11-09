package hello.itemservice.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

@SpringBootTest
public class MessageSourceTest {

  @Autowired
  private MessageSource ms;

  @Test
  void helloMessage() {
    var message = ms.getMessage("hello", null, null);
    assertThat(message).isEqualTo("안녕");
  }

  @Test
  void notFoundMessageCode() {
    assertThatThrownBy(() -> ms.getMessage("djsadhwuqhdska", null, null))
        .isInstanceOf(NoSuchMessageException.class);
  }

  @Test
  void notFoundMessageCodeDefaultMessage() {
    var message = ms.getMessage("djsadhwuqhdska", null, "기본메시지", null);
    assertThat(message).isEqualTo("기본메시지");
  }

  @Test
  void argumentMessage() {
    var message = ms.getMessage("hello.name", new Object[]{"Spring"}, "default", null);
    assertThat(message).isEqualTo("안녕 Spring");
  }

  @Test
  void defaultLang() {
    var messageKo = ms.getMessage("hello", null, "default", Locale.KOREA);
    assertThat(messageKo).isEqualTo("안녕"); // ko프로퍼티스 파일이 없으니까 기본이 나옴

    var messageDefault = ms.getMessage("hello", null, "default", null);
    assertThat(messageDefault).isEqualTo("안녕"); // 기본

    var messageJa = ms.getMessage("hello", null, "default", Locale.JAPANESE);
    assertThat(messageJa).isEqualTo("おはよう"); // 일본어
  }

}
