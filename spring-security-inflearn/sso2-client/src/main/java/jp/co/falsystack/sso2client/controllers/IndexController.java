package jp.co.falsystack.sso2client.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User){
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken)authentication;
        if(authenticationToken != null){
            Map<String, Object> attributes = oAuth2User.getAttributes();
            String userName = (String)attributes.get("name");

            model.addAttribute("user", userName);
        }
        return "index";
    }

}