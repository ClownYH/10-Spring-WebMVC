package com.ohgiraffers.pos.menu.controller;

import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String NullPointerExceptionHandler(Model model, NullPointerException e){
        System.out.println("Global NullPointerException 발생");
        model.addAttribute("message", "불러오고자 하는 데이터가 존재하지 않습니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public  String IndexOutOfBoundExceptionHandler(Model model, IndexOutOfBoundsException e){
        System.out.println("Global IndexOutOfBoundException 발생");
        model.addAttribute("message", "입력값이 배열의 범위를 벗어났습니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String DataTooLongExceptionHandler(Model model, DataIntegrityViolationException e){
        System.out.println("Global DataIntegrityViolationException 발생");
        model.addAttribute("message1", e.getCause());
        model.addAttribute("message2", "입력하고자 하는 데이터 중 제약 조건을 어긴 값이 있습니다.");
        // 모델에 담은 데이터는 모두 String 값으로 변환되기 때문에 "\n", 즉 한 칸 내려쓰기가 적용되지 않는다.
        // 위와 같은 경우 컬럼의 이름을 노출하게 되어 보안이 취약해진다.

        return "/error/errorPage";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeExceptionHandler(Model model, RuntimeException e){
        System.out.println("Global RuntimeException 발생");
        model.addAttribute("message", "동작을 실행하는 중 문제가 발생했습니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String NoResourceFoundExceptionHandler(Model model, NoResourceFoundException e){
        System.out.println("Global NoResourceFoundException 발생");
        model.addAttribute("message", "요청에 해당하는 뷰를 찾지 못했습니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public String BadSqlGrammarExceptionHandler(Model model, BadSqlGrammarException e){
        System.out.println("Global BadSqlGrammarException 발생");
        model.addAttribute("message", "요청을 보내는 쿼리에 문법적 오류가 존재합니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(BindingException.class)
    public String BindingExceptionHandler(Model model, BindingException e){
        System.out.println("Global BindingException 발생");
        model.addAttribute("message", "정상적인 매핑이 이루어지지 않고 있습니다.");

        return "/error/errorPage";
    }

    @ExceptionHandler(Exception.class)
    public String ExceptionHandler(Model model, Exception e){
        e.getClass();
        model.addAttribute("message1", e.getClass());
        model.addAttribute("message2", "특정한 에러가 발생했습니다.");

        return "/error/errorPage";
    }
}
