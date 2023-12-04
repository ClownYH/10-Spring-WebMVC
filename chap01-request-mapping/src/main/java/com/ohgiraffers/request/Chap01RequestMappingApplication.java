package com.ohgiraffers.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication /* 이 어노테이션 하위의 bean을 탐색하여 스프링 풀에 올려 관리(내부에 component scan이 있음) */
public class Chap01RequestMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap01RequestMappingApplication.class, args);
	}

}
