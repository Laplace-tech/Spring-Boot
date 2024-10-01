package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Question {

	//ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//SUBJECT
	@Column(length = 200)
	private String subject;
	
	//CONTENT
	@Column(columnDefinition = "TEXT")
	private String content;
	
	//CREATE_DATE
	@Column
	private LocalDateTime createDate;
	
	//LAST_MODIFIED
	@Column
	private LocalDateTime lastModified;
	
//	이 필드는 데이터베이스에 직접 컬럼으로 생성되지 않습니다.
//	대신, Answer 테이블에서 외래 키(question_id)를 통해 Question 엔티티와 연결됩니다.
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	//AUTHOR_ID
	@ManyToOne
	private SiteUser author;
}
