package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	//ID : Answer 객체의 고유번호(기본키)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//CONTENT : 답변 내용
	@Column(columnDefinition = "TEXT")
	private String content;
	
	//CREATE_DATE : 만든 날짜
	@Column
	private LocalDateTime createDate;
	
	//LAST_MODIFIED : 마지막 수정 날짜 및 시간
	@Column
	private LocalDateTime lastModified;
	
	//AUTHOR_ID : 작성자 ID (SiteUser 객체의 기본키)
	@ManyToOne
	private SiteUser author;
	
	//QUESTION_ID : 댓글 단 질문글을 참조하는 ID (Question 객체의 기본키)
	@ManyToOne
	private Question question;
	
}
