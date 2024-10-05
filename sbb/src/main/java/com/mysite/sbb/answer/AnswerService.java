package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerRepository answerRepository;

	public void create(Question question, String content, SiteUser author) {
		Answer answer = new Answer();

		answer.setAuthor(author);
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);

		// 이 부분에서 Question의 answerList에 자동으로 추가됨
		this.answerRepository.save(answer); // 답변을 저장
		
//		새로운 Answer를 추가할 때, Question의 answerList에 자동으로 추가할 수 있음.
//		AnswerService에서 새로운 Answer를 만들 때, 
//		answer.setQuestion(question);으로 Answer가 속한 Question을 매핑.
//      => Answer객체의 question 참조변수에 Answer 객체가 속한 Question 객체가 매핑됨.
//
//		이때, Question 측의 answerList가 아직 null이면, 
//		JPA는 자동으로 new ArrayList<>()를 통해 객체를 생성하고 해당 Answer 객체를 추가함.
		
	}

}
