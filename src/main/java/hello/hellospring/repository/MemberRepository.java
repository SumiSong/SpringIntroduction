package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 정보 저장
    Optional<Member> findById(Long id); //id를 인자로 받고, 해당하는 회원 정보가 존재하면 그 정보를 Optional 객체로 감싸서 반환
    Optional<Member> findByName(String name); // name을 인자로 받고, 해당하는 회원 정보가 존재하면 그 정보를 Optional 객체로 감싸서 반환
    List<Member> findAll(); // 모든 회원 정보를 조회
}
