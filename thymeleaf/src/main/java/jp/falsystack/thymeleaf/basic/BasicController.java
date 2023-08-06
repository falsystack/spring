package jp.falsystack.thymeleaf.basic;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring MVC2");
        return "/basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "/basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = User.builder()
                .username("userA")
                .age(20)
                .build();

        User userB = User.builder()
                .username("userB")
                .age(30)
                .build();

        List<User> list = List.of(userA, userB);
        Map<String, User> map = Map.of("userA", userA, "userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "/basic/variable";
    }

    @Data
    static class User {
        private String username;
        private int age;

        @Builder
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
