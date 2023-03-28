package jp.falsystack.servlet.web.springmvc.v3;

import java.util.List;
import jp.falsystack.servlet.domain.member.Member;
import jp.falsystack.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  //  @RequestMapping(value = "/new-form", method = GET)
  @GetMapping("/new-form")
  public String newFormV1() {
    return "new-form";
  }

//  @GetMapping("/new-form")
//  public String newFormV2() {
//    return "new-form";
//  }

  //  @RequestMapping("/save")
  @PostMapping("/save")
  public String save(@RequestParam String username, @RequestParam int age, Model model) {

    Member member = new Member(username, age);
    memberRepository.save(member);

    model.addAttribute("member", member);

    return "save-result";
  }

  //  @RequestMapping
  @GetMapping
  public String members(Model model) {

    List<Member> members = memberRepository.findAll();

    model.addAttribute("members", members);

    return "members";
  }

}
