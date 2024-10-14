package com.toYou.board.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.toYou.board.domain.Answer;
import com.toYou.board.domain.Board;
import com.toYou.board.domain.Member;

import groovy.transform.ToString;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

	private Integer id;
	private Member author;
	private String subject;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate; // 이 새끼 문제가 많음.
	private Integer viewCount;
	
	private List<Answer> answerList;
	private Set<Member> likedUsers;
	
	// 사용자가 게시글을 DTO를 통해 작성하고 그걸 토대로 찐 엔티티를 생성
	// User-Level  =======>  DataBase 
	public Board toEntity() {
		return Board.builder()
				.author(this.author)
				.subject(this.subject)
				.content(this.content)
				.build();
	}
	
	// 데이터베이스에서 데이터를 가져올때, 엔티티를 전달하는 것이 아닌 그 엔티티의 DTO를 통해 복사본이 전달.
	// User-Level <======== DataBase 
	@Builder
	public BoardDto(Integer id, Member author, String subject, String content, 
			LocalDateTime createdDate, LocalDateTime lastModifiedDate, Integer viewCount,
			List<Answer> answerList, Set<Member> likedUsers) {
		
		this.id = id;
		this.author = author;
		this.subject = subject;
		this.content = content;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.viewCount = viewCount;
		
		this.answerList = answerList;
		this.likedUsers = likedUsers;
	}
	
}
