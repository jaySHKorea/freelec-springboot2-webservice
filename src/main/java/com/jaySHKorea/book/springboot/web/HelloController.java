package com.jaySHKorea.book.springboot.web;

import com.jaySHKorea.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 응답을 JSON으로 반환하는 컨트롤러
public class HelloController {

    @GetMapping("/hello") //hello 페이지에 대한 요청을 받을때
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    }
}
