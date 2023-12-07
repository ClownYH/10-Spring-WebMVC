package com.ohgiraffers.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 내부에 @ComponentScan이 포함(소속된 패키지부터 scan한다)
public class Chap04ExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap04ExceptionApplication.class, args);
    }

}
