
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //테스트 메서드가 실행되기 전에 호출
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository 생성
        memberService = new MemberService(memberRepository); //MemberService 객체를 생성 MemberService 객체의 생성자를 통해 MemoryMemberRepository 객체 주입
    }


    @AfterEach
    public void afterEach(){ //각 테스트 메서드가 실행된 후에 호출
        memberRepository.clearStore();
    }

    @Test
    void join() throws Exception{
        //given
        Member member = new Member(); //회원 생성
        member.setName("hello"); // 회원 이름을 hello 지정

        //when
        Long saveId = memberService.join(member); //회원 가입

        //then
        Member findMember = memberService.findOne(saveId).get(); //저장된 회원을 조회하고 가져옴
        assertEquals(member.getName(), findMember.getName()); //실제로 저장된 회원의 이름과 예상한 이름이 같은지 검증
        
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1); //member1 가입
        // 중복되는 회원인 member2를 가입하려고 시도함. 이때 예외가 발생해야함. assertThrows() 메서드를 사용하여 예외가 발생하는지를 검증함
        // 예외가 발생하지 않을 경우에는 테스트 실패
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //발생한 예외 메시지를 검증 이때 외 메시지가 일치하지 않을 경우에는 테스트 실패

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}