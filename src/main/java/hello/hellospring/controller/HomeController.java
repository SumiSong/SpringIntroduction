package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // '/' 경로로 들어오는 GET 요청을 처리, 즉 웹 브라우저에서 애플리케이션의 루트 URL에 접속했을 때 실행
    public String home(){
        return "Home";
    }
}
