package io.security.springsecuritymaster.users.controller;

import io.security.springsecuritymaster.domain.dto.AccountDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception,
                               @AuthenticationPrincipal AccountDto accountDto,
                               Model model) {
        model.addAttribute("username", accountDto.getUsername());
        model.addAttribute("exception", exception);

        return "login/denied";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginParam params) {
        return "login/login";
    }

    @GetMapping("/api/login")
    public String restLogin() {
        return "rest/login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "login/signup";
    }

    // get 방식 비추천
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }


    @Data
    private static class LoginParam {
        private String error;
        private String exception;
    }
}
