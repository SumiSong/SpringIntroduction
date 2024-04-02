package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository(); // 새로운 인스턴스 생성

    @AfterEach
    public void afterEach() {
        repository.clearStore(); //각 테스트 메서드가 독립적으로 실행되며, 이전에 저장된 데이터가 현재 테스트에 영향을 주지 않도록 보장
    }

    @Test
    public void save() { // 회원 저장
        //given
        Member member = new Member(); // 회원 객체 생성
        member.setName("spring"); //이름을 'spring'으로 설정
        //when
        repository.save(member); // 메서드 호출하여 회원 저장
        //then
        Member result = repository.findById(member.getId()).get(); // 메서드 호출하여 저장된 회원 조회. 저장된 회원 객체가 일치하는지 확인하기 위함
        assertThat(result).isEqualTo(member); // result 객체와 member 객체가 일치하는지 검증
    }

    @Test
    public void findByName() { // 회원찾기
        //given
        Member member1 = new Member(); // 회원 객체 생성
        member1.setName("spring1"); //회원1의 이름을 spring1로 설정
        repository.save(member1); // 회원 저장
        Member member2 = new Member(); member2.setName("spring2"); // 회원2를 spring2로 객체 생성
        repository.save(member2); // 회원 저장
        //when
        Member result = repository.findByName("spring1").get(); // 메서드를 호출하여 조회한 회원 정보가 일치하는지 확인하기 위함
        //then
        assertThat(result).isEqualTo(member1); // result 객체와 member 객체가 일치하는지 검증
    }

    @Test
    public void findAll() { // 모든 회원찾기
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
        List<Member> result = repository.findAll(); //조회된 모든 회원을 담을 수 있는 리스트(List)를 선언하고, 조회 결과를 이 리스트에 할당.
        //then
        assertThat(result.size()).isEqualTo(2); //조회된 회원의 개수가 예상한 값인 2와 동일한지를 확인
    }
}

