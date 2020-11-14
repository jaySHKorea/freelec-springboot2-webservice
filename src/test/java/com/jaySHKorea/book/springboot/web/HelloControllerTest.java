package com.jaySHKorea.book.springboot.web;

import com.jaySHKorea.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할때 JUnit 내장 실행자 외에 다른 실행자를 실행시킴, 실행자는 SpringRunner
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@RunWith(SpringRunner.class)
// 스프링 어노테이션 중 Web에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 사용 가능
// 단 @Service, @Component, @Repository 등은 사용불가
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

        @Autowired // 빈 주입
        private MockMvc mvc; // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작
                            // 점. 이 클래스로 HTTP GET/POST에 대한 API 테스트 가능

        @Test
        @WithMockUser(roles="USER")
        public void hello가_리턴된다() throws Exception {
            String hello = "hello";

            // 체이닝 지원됨
            mvc.perform(get("/hello")) // MockMvc로 해당 주소로 get 요청
                    .andExpect(status().isOk()) // 결과를 검증(HTTP Header의 Status 검증)-OK :200
                    .andExpect(content().string(hello)); // 결과를 검증(응답 본문의 내용) - "hello"

        }

        @Test
        @WithMockUser(roles="USER")
        public void helloDto가_리턴된다() throws Exception {
            String name = "hello";
            int amount = 1000;

            mvc.perform(
                    get("/hello/dto")
                        .param("name",name) // 요청 파라미터 설정, string만 허용, 숫자/날짜도 무조건 문자열로
                        .param("amount",String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name",is(name))) // JSON 응답값을 필드별로 검정 가능
                    .andExpect(jsonPath("$.amount",is(amount))); // $를 기준으로 필드명 명시
        }
}
