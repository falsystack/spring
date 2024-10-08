package jp.co.falsystack.ss6inflearn.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        var securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        var authentication = securityContext.getAuthentication();
        System.out.println("authentication = " + authentication);
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    //    @GetMapping("/login")
    public String login() {
        return "login!";
    }

    @GetMapping("/anonymous")
    public String anonymous() {
        return "anonymous";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        } else {
            return "null";
        }
    }

    @GetMapping("/anonymousContext")
    public String authentication(@CurrentSecurityContext SecurityContext context) {
        return context.getAuthentication().getName();
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        System.out.println("IndexController.logoutSuccess");
        return "logoutSuccess";
    }

    @GetMapping("/invalidSessionUrl")
    public String invalidSessionUrl() {

    }
}
