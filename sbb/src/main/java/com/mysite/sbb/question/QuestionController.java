package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
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
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;
	private final UserService userService;

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
		Page<Question> paging = this.questionService.getList(pageNumber);
		model.addAttribute("paging", paging);
		return "question_list";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);

		return "question_detail";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {

		if (bindingResult.hasErrors()) {
			return "question_form";
		} else {
			String subject = questionForm.getSubject();
			String content = questionForm.getContent();
			SiteUser siteUser = this.userService.getUser(principal.getName());

			this.questionService.create(subject, content, siteUser);
			return "redirect:/question/list";
		}
	}

//  <a th:href="@{|/question/modify/${question.id}|}" 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id,
			Principal principal) {
		
		Question question = this.questionService.getQuestion(id);
		
		//  if( 글쓴이 != 로그인한 유저 닉네임 ) 
		if(!question.getAuthor().getUserName().contentEquals(principal.getName())) {
			String message = "수정권한이 없음.";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
		} else {
			questionForm.setSubject(question.getSubject());
			questionForm.setContent(question.getContent());
			return "question_form";
		}
		
	}

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
