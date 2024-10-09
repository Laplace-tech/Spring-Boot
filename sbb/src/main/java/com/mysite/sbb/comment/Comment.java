package com.mysite.sbb.comment;

import java.time.LocalDateTime;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private SiteUser author;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column
	private LocalDateTime createDate;
	
	@Column
	private LocalDateTime lastModified;
	
	@ManyToOne
	private Question question;
	
	@ManyToOne
	private Answer answer;
	
}
