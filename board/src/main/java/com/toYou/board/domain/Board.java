package com.toYou.board.domain;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity 
public class Board extends Time {

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// AUTHOR_ID
	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private Member author;
	
	// SUBJECT
	@Column(length = 150, nullable = false)
	private String subject;
	
	// CONTENT
	@Lob
	@Column(nullable = false)
	private String content;
	
	// VIEW_COUNT
	@Column
	private Integer viewCount;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Answer> answerList = new ArrayList<>();
	
	// BOARD_LIKES
	// [MEMBER_ID|BOARD_ID]
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
		name = "board_likes",
		joinColumns = @JoinColumn(name = "BOARD_ID"),
		inverseJoinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private Set<Member> likedUsers = new HashSet<>();
	
	
	@lombok.Builder
	public Board(Member author, String subject, String content) {
		this.author = author;
		this.subject = subject;
		this.content = content;
		this.viewCount = 0;
	}
	
}
