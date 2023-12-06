package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/","/main"})
    // "/" : localhost:8080/을 의미한다. 루트 요청이라고도 부른다. Mapping 설정이 template으로 되어있기 때문에
    // static에 main.html이 있어도 실행안됨(톰캣 기본 설정으로 index.html만 가능)
    public String main(){
        return "main";
    }
}
