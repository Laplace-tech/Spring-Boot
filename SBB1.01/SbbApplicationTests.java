package com.mysite.sbb;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	void testJpa() {
	}

	void insertionAnswerData() {
		generationAnswerData(8, "ㅇㅇ 특히 20비 기무탄이 꿀임");
		generationAnswerData(8, "ㄴ-> 이새낀 진짜 악마네 ㅋㅋㅋㅋ");
		generationAnswerData(8, "그냥 헌급방이나 가라");
		generationAnswerData(8, "나 8비 항전 화력반인데 매일매일 ㅈ뻉이친다");
		generationAnswerData(8, "그냥 닥치고 육군이나 가라 유꾼 아미 타이거! 유꾼유꾼유꾼ㅇ!");
		generationAnswerData(8, "병신들ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
	}
	
	void generationAnswerData(Integer questionID, String content) {
		Question q = this.questionRepository.findById(questionID).get();
		
		Answer a = new Answer();
		a.setContent(content);
		a.setQuestion(q);
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	
	void insertionQuestionData() {
		generationQuestionData("수능등급 65646인데 서성한 ㄱㄴ?", "서강대교 성수대교 한강대교");
		generationQuestionData("2306 30번 문제 해설", "는 구라고 나도 모르겠음 ㅋ");
		generationQuestionData("등급컷 떳냐?", "떴으니깐 올리지 아 ㅋㅋ");
		generationQuestionData("시발 인생", "이게 1컷이 92라고? 씨발ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
		generationQuestionData("컴공 ㅈ같다", "코테 이거 아무리봐도 금머갈 테스트인거 같은데 ㅈ됐다");
		generationQuestionData("짬찌들 지금 이 시점 이짬 주면 갖냐?", "34%");
		generationQuestionData("대855기", "대855기 오늘자 복무율 34퍼 달성 ㅅㅅ");
		generationQuestionData("공군가서 꿀빨려는데 무슨 특기 가야함?", "ㅈㄱㄴ");
	}
	
	void generationQuestionData(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		
		this.questionRepository.save(q);
	}
	
	
}