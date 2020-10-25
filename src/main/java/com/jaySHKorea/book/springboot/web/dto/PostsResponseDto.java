package com.jaySHKorea.book.springboot.web.dto;

import com.jaySHKorea.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){ // 엔티티 필드 중 일부만 사용하므로 생성자로 엔티티 값을 받아 필드에 넣음
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
