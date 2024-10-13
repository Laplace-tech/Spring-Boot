package com.toYou.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.toYou.board.domain.Answer;
import com.toYou.board.domain.Board;
import com.toYou.board.domain.Member;
import com.toYou.board.dto.BoardDto;
import com.toYou.board.repository.BoardRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	private static final int PAGE_POST_COUNT = 5;

	public Page<BoardDto> getBoardPage(int pageNum) {

		Page<Board> entityPage = this.boardRepository.findAll(
				PageRequest.of(pageNum, PAGE_POST_COUNT, Sort.by(Sort.Order.desc("createdDate"))));

		return entityPage.map(this::convertEntityToDto);
	}
	
	public BoardDto convertEntityToDto(Board boardEntity) {
		return BoardDto.builder()
				.id(boardEntity.getId())
				.author(boardEntity.getAuthor())
				.subject(boardEntity.getSubject())
				.content(boardEntity.getContent())
				.createdDate(boardEntity.getCreatedDate())
				.lastModifiedDate(boardEntity.getLastModifiedDate())
				.viewCount(boardEntity.getViewCount())
				.answerList(boardEntity.getAnswerList())
				.likedUsers(boardEntity.getLikedUsers())
				.build();
	}

	
}
