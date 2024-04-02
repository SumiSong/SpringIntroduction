package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    //회원 리포지토리 생성
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { //외부에서 MemoryMemberRepository 객체를 주입받음
        this.memberRepository = memberRepository; //받은 회원 저장소 객체를 MemberService 클래스의 멤버 변수인 memberRepository에 할당
    }

    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member); //회원 저장
        return member.getId(); //저장된 회원 ID 반환
    }

    //중복 회원 검증
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()) // 동일한 이름을 가진 회원을 조회
                .ifPresent(m -> { // 만약 동일한 이름을 가진 회원이 존재한다면
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 예외 발생
                });
    }

    //전체 회원 조회
//    public List<Member> findMembers(){
//        return  memberRepository.findAll(); // 저장소에 저장된 모든 회원을 리스트로 반환
//    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId); //주어진 회원 ID에 해당하는 회원을 조회 후 반환
    }

}
