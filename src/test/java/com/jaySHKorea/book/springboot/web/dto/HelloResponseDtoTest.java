package com.jaySHKorea.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        /* assertThat
        - assertj라는 테스트 검증 라이브러리 검증 메서드
        - 검증하고 싶은 대상을 메소드 인자로 받음
        - 메소드 체이닝이 지원됨

        isEqualTo
        - assertj의 동등비교 메소드
        */
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
