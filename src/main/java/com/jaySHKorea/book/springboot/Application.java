package com.jaySHKorea.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA AUditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, Bean 읽기와 생성이 모두 자동
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); // 내장 WAS 사용, 톰캣 필요 X, jar파일로 실행
    }
}
