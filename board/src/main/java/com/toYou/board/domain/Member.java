package com.toYou.board.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Member {

	// ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// MEMBER_NAME
	@Column(nullable = false, unique = true)
	private String memberName;
	
	// PASSWORD
	@Column(nullable = false)
	private String password;
	
	// EMAIL
	@Column(nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	private List<Board> boardList;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	// BOARD_LIKES
	// [MEMBER_ID|BOARD_ID]
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
	    name = "board_likes",
	    joinColumns = @JoinColumn(name = "MEMBER_ID"),
	    inverseJoinColumns = @JoinColumn(name = "BOARD_ID")
	)
	private Set<Board> likedBoards;

	// ANSWER_LIKES
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
	    name = "answer_likes",
	    joinColumns = @JoinColumn(name = "MEMBER_ID"),
	    inverseJoinColumns = @JoinColumn(name = "ANSWER_ID")
	)
	private Set<Answer> likedAnswers;


}
