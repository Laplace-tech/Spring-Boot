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

	//ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//CONTENT
	@Column(columnDefinition = "TEXT")
	private String content;
	
	//CREATE_DATE
	@Column
	private LocalDateTime createDate;
	
	//LAST_MODIFIED
	@Column
	private LocalDateTime lastModified;
	
	//QUESION_ID
	@ManyToOne
	private Question question;
	
	//AUTHOR_ID
	@ManyToOne
	private SiteUser author;
}
