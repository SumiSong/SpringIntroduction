package hello.hellospring.controller;
import org.springframework.ui.Model;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService; //멤버 변수 선언

    @Autowired
    public MemberController(MemberService memberService) { //멤버 컨트롤러 생성자

        this.memberService = memberService; // 멤버서비스를 받아 멤버 변수에 할당
    }

    @GetMapping("/members/new") // HTTP GET 요청에 대한 매핑 지정
    public String createForm(){ //회원 가입 폼을 보여주는 뷰 이름 반환
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //경로로 들어오는 POST 요청을 처리
    public String create(MemberForm form){ //회원 등록
        Member member = new Member(); // 회원 객체 생성
        member.setName(form.getName()); // 회원 객체 이름 설정
        memberService.join(member); // 회원 객체를 서비스에 회원 서비스에 전달하여 회원 등록
        return "redirect:/"; //회원 등록 후에 홈페이지로 이동
    }

    @GetMapping(value = "/members")  //경로로 들어오는 GET 요청을 처리
    public String list(Model model){ //회원 목록 조회, 모델 추가, 뷰 반환 메서드
        List<Member> members = memberService.findMembers(); //회원 목록 조회
        model.addAttribute("members", members); //조회된 회원 목록을 모델에 추가
        return "members/memberList"; // 회원 목록을 보여줄 뷰의 이름 반환
    }
}
