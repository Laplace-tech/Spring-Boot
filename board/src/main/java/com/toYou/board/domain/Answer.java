package com.toYou.board.domain;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Answer {

	// ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// CONTENT
	@Lob
	@Column(nullable = false)
	private String content;
	
	// AUTHOR_ID
	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Member author;
	
	// BOARD_ID
	@ManyToOne
	@JoinColumn(name = "BOARD_ID")
	private Board board;
	
	// CREATE_TIME
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	// UPDATE_TIME
	@Column
	private LocalDateTime updateTime;
	
	
	// ANSWER_LIKES
	// [MEMBER_ID|ANSWER_ID]
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
		name = "answer_likes",
		joinColumns = @JoinColumn(name = "ANSWER_ID"),
		inverseJoinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private Set<Member> likedUsers;
	
}
