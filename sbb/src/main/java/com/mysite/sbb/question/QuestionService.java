package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
// @Controller가 HTTP 요청을 처리하는동안, @Service는 비즈니스 로직을 수행하고 
// 데이터베이스와의 상호작용을 관리함. @Controller는 @Service의 결과를 받아서 적절한 HTTP response를 생성함.
@RequiredArgsConstructor
// 클래스의 모든 final 필드와 @NonNull로 지정된 필드에 대한 생성자를 자동으로 생성함.
// @Service와 @RequiredArgsConstructor를 함께 사용하면 비즈니스 로직을 처리하는 서비스 클래스에서
// 의존성을 간편하게 주입받을 수 있으며, 코드가 간결해지고 명확하게 유지됨.
public class QuestionService {

	private final QuestionRepository questionRepository;

	// 모든 질문 목록을 반환하는 메서드 (구버전)
	@Deprecated
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}


//  호출부 : QuestionController
//
//  @GetMapping("/list") 
//		ㄴ> Page<Question> paging = questionService.getList(pageNumber)
	
	// 페이지네이션된 질문 목록 반환
	public Page<Question> getList(int pageNumber) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate")); // 게시글 작성일을 기준으로 내림차순 = 최신순
		Pageable pageable = PageRequest.of(pageNumber, 12, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	
	
	
	
//	호출부 : QuestionController
//
//  @GetMapping("/detail/{id})
//		ㄴ> Question question = questionService.getQuestion(id);
	
	// 질문 ID로 특정 질문을 찾음
	public Question getQuestion(Integer id) {
		return this.questionRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("질문을 못찾겠어요..!"));
	}

	
	
	
//	호출부 : QuestionController
//
//	@PostMapping("/create") // create URL로 오는 POST 요청을 처리
//		ㄴ> questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser)
	
	// 새로운 질문 생성
	public void create(String subject, String content, SiteUser author) {
		Question newQuestion = new Question();

		newQuestion.setSubject(subject);
		newQuestion.setContent(content);
		newQuestion.setCreateDate(LocalDateTime.now());
		newQuestion.setLastModified(LocalDateTime.now());
		newQuestion.setAuthor(author);

		this.questionRepository.save(newQuestion);

//	   	/* 왜 answerList랑 Id는 question.set()을 안하는가? */
//	
//	     1. answerList 
// 		 
// 	 	 Question 엔티티 내에 answerList는 @OneToMany로 설정되어 있지만,
//	     질문이 생성될 때는 이 리스트가 자동으로 초기화되지 않음.
//	     JPA에서는 기본적으로 관계를 설정한 객체들 간의 연결을 처리하지만,
//	     Question이 생성될 때는 answerList가 빈 리스트로 초기화되지 않고 null 상태로 남아있음.
//		 
//		
//		 Q) 그럼 언제 초기화 됨?
//		
//		 this.questionRepository.save(q);를 호출하면 
//   	 JPA가 자동으로 해당 Question 객체의 answerList를 업데이트함. 
//		 즉, answerList가 null이라면 JPA가 이를 초기화하여 새 ArrayList를 생성하고,
//		 해당 Answer 객체를 추가함.
//
//		 2. Id
//		
//		 Question q = new Question();로 새로운 질문 객체를 생성할 때, id 필드는 역시나 null 상태임.
//		 this.questionRepository.save(q);를 호출하면,
//  	 JPA는 Question 객체를 데이터베이스에 저장하고, 
//		 이때 데이터베이스에서 자동으로 생성된 id 값이 Question 객체의 id 필드에 할당됨. 

	}
	
	
//	호출부 : QuestionController
//		
//	@PostMapping("/modify/{id}")
//		ㄴ> this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());

	public void modify(Question question, String subject, String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setLastModified(LocalDateTime.now());
	
		this.questionRepository.save(question);
	}

//	호출부 : QuestionController
//  	 
//	@GetMapping("/delete/{id}")
//		ㄴ> this.questionService.delete(question);
	
	public void delete(Question question) {
		this.questionRepository.delete(question);
	}

	

}
