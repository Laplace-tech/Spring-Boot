package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

	//ID (Question 객체의 기본키)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//SUBJECT (질문 제목)
	@Column(length=200)
	private String subject;
	
	//CONTENT (질문 내용)
	@Column(columnDefinition = "TEXT")
	private String content;
	
	//CREATE_DATE (게시 날짜)
	@Column
	private LocalDateTime createDate;

	//LAST_MODIFIED (마지막 수정날짜)
	@Column
	private LocalDateTime lastModified;
	
	/* 해당 게시글에 달린 답변 리스트
	 * 
	 * 이 필드는 데이터베이스에 직접 컬럼으로 생성되지 않습니다.
	 * 대신, Answer 테이블에서 왜래 키(question_id)를 통해 Question 엔티티와 연결됩니다.
	 * 
	 * 새로운 Answer를 추가할 때, Question의 answerList에 자동으로 추가할 수 있음.
	 * AnswerService에서 새로운 Answer를 만들 때, answer.setQuestion으로 Answer가 속한 Question을 매핑.
	 * 이때, question의 answerList가 null이면, JPA는 자동으로 new ArrayList<>()를 통해 객체를 생성하고 
	 * 해당 Answer 객체를 추가함.
	 */
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	//AUTHOR_ID (게시자 : 한 유저는 여러 질문글을 작성할 수 있다)
	@ManyToOne
	private SiteUser author;
}
