package com.toYou.board.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
// 클래스 내의 모든 필드에 대해 getter 메서드를 자동으로 생성
@MappedSuperclass
// 이 클래스를 상속받는 엔티티는 createdDate와 lastModifiedDate 필드를 자동으로 디비에 매핑시킴
@EntityListeners(value = {AuditingEntityListener.class})
// 엔티티가 생성될 때, createdDate 필드에 자동으로 값을 설정 
public abstract class Time {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	@Column
	private LocalDateTime lastModifiedDate;
}
