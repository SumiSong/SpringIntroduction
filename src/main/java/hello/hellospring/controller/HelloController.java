package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //HTTP GET 요청이 "/hello" 경로로 들어왔을 때 실행
    public String hello(Model model){  //Model 객체가 포함. 컨트롤러와 뷰 사이의 데이터를 전달하는 데 사용
        model.addAttribute("data", "Hello!!"); // "data"라는 이름으로 "hello!!"라는 값을 모델에 추가. model(data:Hello!!)
        return "hello"; // resources/templates/hello.html로 이동
    }

    @GetMapping("hello-mvc") //HTTP GET 요청이 "/hello-mvc" 경로로 들어왔을 때 실행
    public String helloMvc(@RequestParam("name") String name, Model model){ //클라이언트가 "/hello-mvc?name=값"과 같은 형태로 요청할 때 해당 값을 받아오는 역할
        model.addAttribute("name", name); // 컨트롤러에서 받은 name 값을 모델에 "name"이라는 이름으로 추가 ex)model(name: spring)
        return "hello-template";
    }
    @GetMapping("hello-string") // HTTP GET 요청이 "/hello-string" 경로로 들어왔을 때 이 메서드가 실행
    @ResponseBody //컨트롤러 메서드가 반환하는 데이터가 HTTP 응답 본문에 직접 쓰여야 함을 나타냄. 즉, 메서드가 반환하는 문자열이 HTTP 응답 본문에 포함되어 클라이언트로 전송
    public String helloString(@RequestParam("name") String name){  //쿼리 문자열에서 "name" 파라미터를 추출하여 name 파라미터로 받음. 이는 클라이언트가 "/hello-string?name=값"과 같은 형태로 요청할 때 해당 값을 받아오는 역할
        return "hello" + name;
    }


    // 아래는 Spring MVC에서 RESTful API를 제공하는 컨트롤러 메서드의 예시
    @GetMapping("hello-api") // HTTP GET 요청이 "/hello-api" 경로로 들어왔을 때 이 메서드가 실행
    @ResponseBody //컨트롤러 메서드가 반환하는 데이터가 HTTP 응답 본문에 직접 쓰여야 함을 나타냄. 즉, 메서드가 반환하는 문자열이 HTTP 응답 본문에 포함되어 클라이언트로 전송
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //클래스의 새로운 인스턴스 생성
        hello.setName(name); //받아온 name 값을 Hello 객체의 속성에 설정
        return hello; //설정된 Hello 객체를 반환. Spring MVC는 이 객체를 자동으로 JSON 형식으로 직렬화하여 클라이언트로 전송
    }
    static class Hello{
        private String name; //멤버 변수 선언. 클래스 내부에서만 사용 가능

        public String getName(){ //name 변수의 값을 반환하는 public 메서드인 getName()을 정의
            return name; //이 메서드를 통해 외부에서 name 변수의 값을 읽을 수 있음
        }

        public void setName(String name){ //name 변수의 값을 반환하는 public 메서드인 setName()을 정의
            this.name = name; // 이 메서드를 통해 외부에서 name 변수의 값을 변경. 메서드 안의 this.name은 현재 인스턴스의 name 변수를 가리킴
        }
    }


}
