package jp.co.falsystack.sso2client.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

//@Controller
//@RequiredArgsConstructor
//public class ClientController {
//
//    private final OAuth2AuthorizedClientRepository authorizedClientRepository;
//    private final OAuth2AuthorizedClientService authorizedClientService;
//
//    @GetMapping("/client")
//    public String client(HttpServletRequest request, Model model) {
//
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        var clientRegistrationId = "keycloak";
//
//        var authorizedClient1 = authorizedClientRepository.loadAuthorizedClient(clientRegistrationId, authentication, request);
//        var authorizedClient2 = authorizedClientService.loadAuthorizedClient(clientRegistrationId, authentication.getName());
//
//        var accessToken = authorizedClient1.getAccessToken();
//
//        var oAuth2UserService = new DefaultOAuth2UserService();
//        var oAuth2User = oAuth2UserService.loadUser(new OAuth2UserRequest(authorizedClient1.getClientRegistration(), accessToken));
//
//        OAuth2AuthenticationToken authenticationToken = new OAuth2AuthenticationToken
//                (oAuth2User, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), clientRegistrationId);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//        model.addAttribute("accessToken", authorizedClient1.getAccessToken().getTokenValue());
//        model.addAttribute("refreshToken", authorizedClient1.getRefreshToken().getTokenValue());
//        model.addAttribute("principalName", oAuth2User.getName());
//        model.addAttribute("clientName", authorizedClient1.getClientRegistration().getClientName());
//
//        return "client";
//    }
//}
