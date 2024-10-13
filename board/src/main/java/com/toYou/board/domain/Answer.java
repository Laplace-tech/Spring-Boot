package com.toYou.board.domain;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장된다.
@Getter
@Entity
public class Answer extends Time{

	// ID
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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
	
	// ANSWER_LIKES
	// [MEMBER_ID|ANSWER_ID]
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
		name = "answer_likes",
		joinColumns = @JoinColumn(name = "ANSWER_ID"),
		inverseJoinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private Set<Member> likedUsers = new HashSet<>();
	
	@lombok.Builder
	public Answer(String content, Member author, Board board) {
		this.content = content;
		this.author = author;
		this.board = board;
	}
	
}
