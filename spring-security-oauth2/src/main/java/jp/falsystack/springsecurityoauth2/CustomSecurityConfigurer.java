package jp.falsystack.springsecurityoauth2;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomSecurityConfigurer extends
    AbstractHttpConfigurer<CustomSecurityConfigurer, HttpSecurity> {

  private boolean isSecure;

  @Override
  public void init(HttpSecurity builder) throws Exception {
    super.init(builder);
    System.out.println("CustomSecurityConfigurer.init");
  }

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    super.configure(builder);
    System.out.println("CustomSecurityConfigurer.configure");
    if (isSecure) {
      System.out.println("https is required");
    } else {
      System.out.println("https is optional");
    }
  }

  public CustomSecurityConfigurer isSecure(boolean isSecure) {
    this.isSecure = isSecure;
    return this;
  }
}