package com.toYou.board.domain;

import java.time.LocalDateTime;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Getter;

@Getter
@Entity
public class Board {

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// SUBJECT
	@Column(length = 150, nullable = false)
	private String subject;
	
	// CONTENT
	@Lob
	@Column(nullable = false)
	private String content;
	
	// AUTHOR_ID
	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Member author;
	
	// CREATE_TIME
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	// UPDATE_TIME
	@Column
	private LocalDateTime updateTime;
	
	// VIEW_COUNT
	@Column
	private Integer viewCount = 0;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	
	// BOARD_LIKES
	// [MEMBER_ID|BOARD_ID]
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
		name = "board_likes",
		joinColumns = @JoinColumn(name = "BOARD_ID"),
		inverseJoinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private Set<Member> likedUsers;
	

	@PrePersist
	public void prePersist() {
		this.createTime = this.updateTime = LocalDateTime.now();
	}
	
}
