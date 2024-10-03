package com.mysite.sbb.question;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
//Controller : HTTP 요청을 처리하고 사용자의 요청을 @Service에 전달하는 역할을 함.
// 즉, 사용자의 입력을 받아서 QuestionService의 메서드를 호출하여 비즈니스 로직을 실행함.
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
		
		Page<Question> paging = questionService.getList(pageNumber);
		
//						*** at QuestionService method ***
//
//		public Page<Question> getList(int pageNumber) {
//
//			List<Sort.Order> sorts = new ArrayList<>();
//			sorts.add(Sort.Order.desc("createDate")); // 게시글 작성일을 기준으로 내림차순 = 최신순
//			Pageable pageable = PageRequest.of(pageNumber, 12, Sort.by(sorts));
//
//			return this.questionRepository.findAll(pageable);
//		}
		
		model.addAttribute("paging", paging);

//             			   *** question_list.html file ***  
//
//		<tr th:each="question, loop : ${paging}">
//
//      <a th:href="@{|/question/detail/${question.id}|}" >>>> (Ex : /question/detail/1)
//         th:text="${question.subject}"></a>

		return "question_list";
	}
	
	@GetMapping("/detail/")
	public String detail(Model model, Integer id, AnswerForm answerForm) {
		
	}
	
//	@GetMapping("/")
//	public String root() {
//		return "redirect:/question/list";
//	}

//  <a th:href="@{|/question/modify/${question.id}|}" 
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/modify/{id}")
//	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id,
//			Principal principal) {
//		
//		Question question = this.questionService.getQuestion(id);
//		
//		//  if( 글쓴이 != 로그인한 유저 닉네임 ) 
//		if(!question.getAuthor().getUserName().contentEquals(principal.getName())) {
//			String message = "수정권한이 없음.";
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
//		} else {
//			questionForm.setSubject(question.getSubject());
//			questionForm.setContent(question.getContent());
//			return "question_form";
//		}
//		
//	}

//	@PostMapping("/create")
//	public String questionCreate(@RequestParam(value = "subject") String subject,
//								 @RequestParam(value = "content") String content) {
//		
//		this.questionService.create(subject, content);
//		return "redirect:/question/list";
//	}

//	@GetMapping("/list")
//	public String list(Model model) {
//		List<Question> questionList = this.questionService.getList();
//		model.addAttribute("questionList", questionList);
//
//		return "question_list";
//	}

}
