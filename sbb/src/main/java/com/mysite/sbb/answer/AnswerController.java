package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;

//	 현재 로그인한 사용자의 정보를 알려면 스프링 시큐리티가 제공하는 Principal 객체를 사용해야 한다.
//	 principal.getName()을 호출하면 현재 로그인한 사용자의 사용자명(사용자ID)을 알 수 있다.
//	
//	 <form th:action="@{|/answer/create/${question.id}|}" th:object="${answerForm}" method="post" class="my-3">

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
			BindingResult bindingResult, Principal principal) {

		Question question = this.questionService.getQuestion(id); //
		SiteUser siteUser = this.userService.getUser(principal.getName()); 

		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}

		this.answerService.create(
				question, // Answer 객체에 대한 의존관계 : Question(One) <- Answer(Many)
				answerForm.getContent(), // 댓글내용
				siteUser); // 댓글 작성자
		
		return String.format("redirect:/question/detail/%s", id);
	}

//	@PostMapping("/create/{id}")
//	public String createAnswer(Model model, @PathVariable(value = "id") Integer id,
//			@RequestParam(value = "content") String content) {
//
//		Question question = this.questionService.getQuestion(id);
//		answerService.create(question, content);
//
//		return String.format("redirect:/question/detail/%s", id);
//	}

//	@PostMapping("/create/{id}")
//	public String createAnswer(Model model, @PathVariable("id") Integer id, 
//			@Valid AnswerForm answerForm, BindingResult bindingResult) {
//
//		Question question = this.questionService.getQuestion(id);
//
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("question", question);
//			return "question_detail";
//		}
//	
//		this.answerService.create(question, answerForm.getContent());
//		return String.format("redirect:/question/detail/%s", id);
//	}

}
