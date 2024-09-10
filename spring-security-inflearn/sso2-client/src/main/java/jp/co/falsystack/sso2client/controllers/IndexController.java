package jp.co.falsystack.sso2client.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {

//    private final ClientRegistrationRepository clientRegistrationRepository;

//    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/")
    public String getClientRegistrations() {
//        var clientRegistration = clientRegistrationRepository.findByRegistrationId("keycloak");
//
//        clientRegistration.getClientId();
//        return clientRegistration.getRegistrationId();
        return "";
    }
}
