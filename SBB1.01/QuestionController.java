package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

//	@GetMapping("/list")
//	public String list(Model model) {
//		List<Question> questionList = this.questionService.getList();
//		model.addAttribute("questionList", questionList);
//
//		return "question_list";
//	}

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int pageNum) {
		Page<Question> paging = this.questionService.getList(pageNum);
		model.addAttribute("paging", paging);
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);

		return "question_detail";
	}

	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}

	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "question_form";
		} else {
			this.questionService.create(questionForm.getSubject(), questionForm.getContent());
			return "redirect:/question/list";
		}
	}

//	// <form th:action = "@{/question/create}" method="post">
//
//	@PostMapping("/create")
//	public String questionCreate(@RequestParam(value = "subject") String subject,
//								 @RequestParam(value = "content") String content) {
//		
//		this.questionService.create(subject, content);
//		return "redirect:/question/list";
//	}

}
