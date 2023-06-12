package springbootjpaprac.springbootjpaprac.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springbootjpaprac.springbootjpaprac.domain.Address;
import springbootjpaprac.springbootjpaprac.domain.Member;
import springbootjpaprac.springbootjpaprac.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    //Member 엔티티를 안넣고 MemberForm 클래스를 새로 만들어서 하는 이유 : 엔티티에는 id도 있고, 여러 정보들이 더 많다. 따라서, 화면에서 딱 값을 받아오는
    //name, address만(필요한 데이터만) 가져오는게 좋다.
    // From 객체를 사용하냐, 엔티티를 직접 쓰냐.. 이 문제는 계속 생각하면서 코딩하기. 중요한 것은, 엔티티를 최대한 순수하게 만든느 것이(핵심 비지니스 로직만. 화면 기능에 맞추는 로직 ㄴㄴ) 좋음.
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){

        if(result.hasErrors()){         //에러가 있다면. NotEmpty
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

}
