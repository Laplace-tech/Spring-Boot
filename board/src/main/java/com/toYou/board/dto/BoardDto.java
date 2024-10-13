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
	private LocalDateTime lastModifiedDate;
	private Integer viewCount;
	
	private List<Answer> answerList;
	private Set<Member> likedUsers;
	
	public Board toEntity() {
		return Board.builder()
				.author(this.author)
				.subject(this.subject)
				.content(this.content)
				.build();
	}
	
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
