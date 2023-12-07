package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("controller-null")
    public String nullPointException(){
        String str = null;

        System.out.println(str.charAt(0)); // str이 null이기 때문에 해당 동작을 시행할 수 없어 프로세스가 멈춤

        return "/main";
    }

    @ExceptionHandler(NullPointerException.class) // 지역 단위의 exception 처리, 전역 단위보다 우선
    public String NullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 Exception 처리");
        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userException() throws MemberRegistException{
        boolean check = true;
        if(check){
            throw new MemberRegistException("입사가 불가능합니다.");
        }
        return "/main";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String MemberRegistExceptionHandler(Model model, MemberRegistException e){
        System.out.println("controller 레벨의 Exception 처리");
        model.addAttribute("exception", "MemberRegistException이 발생");
        return "error/memberRegist";
    }
}
