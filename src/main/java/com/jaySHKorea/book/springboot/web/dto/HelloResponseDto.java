package com.jaySHKorea.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // Getter
@RequiredArgsConstructor // 생성자
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
