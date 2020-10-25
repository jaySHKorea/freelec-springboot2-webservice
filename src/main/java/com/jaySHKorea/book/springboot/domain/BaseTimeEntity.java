package com.jaySHKorea.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA entity 클래스들이 지금 클래스를 상속할 경우 필드들을 지금 클래스의 칼럼으로 인식하게함
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능 포함
public abstract class BaseTimeEntity {

    @CreatedDate // 생성시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 값 변경시 자동저장
    private LocalDateTime modifiedDate;
}
