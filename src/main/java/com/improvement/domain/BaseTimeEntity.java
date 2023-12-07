package com.improvement.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//이 class 상속할 경우 하위는 entity인식됨
@EntityListeners(AuditingEntityListener.class) //해당 class에 Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate//entity가 생성시
    private LocalDateTime createDate;

    @LastModifiedDate//entity가 변경시
    private LocalDateTime modifiedDate;






}
