package jp.falsystack.springsecurityoauth2;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  private final ClientRegistrationRepository clientRegistrationRepository;

  public IndexController(ClientRegistrationRepository clientRegistrationRepository) {
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  @GetMapping("/")
  public String index() {
    var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");

    System.out.println("clientRegistration.getClientId() = " + clientRegistration.getClientId());

    return "Hello World!";
  }
}
