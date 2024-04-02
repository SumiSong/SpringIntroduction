package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() { //이름 반환
        return name;
    }

    public void setName(String name) { // 이름 설정
        this.name = name;
    }
}
