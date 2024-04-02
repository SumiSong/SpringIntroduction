package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
     private static Map<Long, Member> store = new HashMap<>(); //store 이름의 HashMap을 사용하여 회원 정보를 메모리에 저장.  Long은 Member 객체를 고유하게 식별하는 데 사용되는 값
     private static long sequence = 0L; // sequence는 회원의 식별자(id)를 생성하기 위한 변수
    @Override
    public Member save(Member member) { //회원 저장
        member.setId(++sequence); //Member 객체의 고유한 식별자(id)를 설정하는 메서드를 호출. (Member 클래스에 정의됨)
        store.put(member.getId(), member); //(회원 객체의 고유한 식별자(id), 회원 객체 자체)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //store.get(id)가 반환한 값(회원 객체 또는 null)으로부터 Optional 객체를 생성.
        // ofNullable() 메서드는 매개변수로 받은 객체가 null이면 빈(empty) Optional 객체를 생성하고, null이 아니면 해당 객체를 가지고 있는 Optional 객체를 생성.
    }

    @Override
    //stream() 메서드를 사용하여 store의 값들을 스트림으로 변환한 후, filter() 메서드로 이름이 일치하는 회원을 찾음
    public Optional<Member> findByName(String name) {
        return store.values().stream() //맵에 저장된 모든 회원 객체들의 Collection을 반환한 후  Collection을 스트림(Stream)으로 변환.
                .filter(member -> member.getName().equals(name)) //스트림의 각 요소를 필터링하여 주어진 이름과 동일한 이름을 가진 회원 찾음. getName() 메서드로 회원의 이름을 가져와서 주어진 이름과 비교
                .findAny(); //찾은 회원 중에 임의의 한 요소를 반환
    }

    @Override
    public List<Member> findAll() { // 현재 저장된 모든 회원 정보 반환
        return new ArrayList<>(store.values()); //store.values()를 이용하여 생성된 Collection을 ArrayList로 복사 -> 새로운 ArrayList 객체 생성
    }

    public void clearStore() { // 저장된 모든 회원 정보 삭제
        store.clear();
    }
}
