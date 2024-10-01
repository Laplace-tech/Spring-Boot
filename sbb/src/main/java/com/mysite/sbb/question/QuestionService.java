package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;
	
//	public List<Question> getList(){
//		return this.questionRepository.findAll();
//	}

	/*public Page<Question> getList(int pageNum){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.asc("createDate"));
		
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}*/
	
	public Page<Question> getList(int pageNumber) {
		 List<Sort.Order> sorts = new ArrayList<>();
	        sorts.add(Sort.Order.desc("createDate"));
	        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by(sorts));
	        return this.questionRepository.findAll(pageable);
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("없는데요. 뚱인데요?");
		}
	}
	
	public void create(String subject, String content, SiteUser siteUser) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(siteUser);
		
		this.questionRepository.save(q);
	}

	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

//	
//	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//	private List<Answer> answerList;
//	
//	@ManyToOne
//	private SiteUser author;
	
	
}
