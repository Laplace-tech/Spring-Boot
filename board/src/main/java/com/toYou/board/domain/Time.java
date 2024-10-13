package com.toYou.board.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class Time {

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column
	private LocalDateTime lastModifiedDate;
	
}
