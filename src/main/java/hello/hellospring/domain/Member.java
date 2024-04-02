package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name="username")
    private String name;

    public Long getId(){ //id 변수의 값을 반환하는 public 메서드인 getId()를 정의
        return id; // 메서드를 통해 외부에서 id 변수의 값 읽음
    }

    public void setId(Long id){ //id 변수의 값을 설정하는 public 메서드인 setId()를 정의
        this.id = id; // 현재 인스턴스의 id 변수를 가리킴
    }

    public String getName(){ //name 변수의 값을 반환하는 public 메서드인 getName()을 정의
        return name; //외부에서 name 변수의 값을 읽을 수 있음
    }

    public void setName(String name) { //name 변수의 값을 설정하는 public 메서드인 setName()을 정의
        this.name = name; //현재 인스턴스의 name 변수를 가리킴
    }

}
