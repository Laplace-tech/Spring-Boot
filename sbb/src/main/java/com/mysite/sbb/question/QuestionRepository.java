package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.user.SiteUser;


//QuestionRepository : Question 엔티티에 대한 데이터 엑세스 인터페이스
//CRUD(생성, 읽기, 업데이트, 삭제) 작업을 수행할 수 있는 메서드를 자동으로 제공
//C : create
//R : read
//U : update
//D : delete

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	// Spring Data JPA에서 메서드 이름을 정의할 때, 특정 규칙에 따라 이름을 짓는 것이 중요
	// 1. 속성 이름 일치: 엔티티의 속성 이름과 동일해야 함
	// 2. 메서드 이름 규칙: 메서드 이름의 시작 부분에 findBy를 붙여야 JPA가 검색 작업임을 인식
	// 3. 특수한 쿼리 메서드: Like 키워드를 사용하여 부분 일치를 찾는 메서드임
	
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
	//Pageable을 
	Page<Question> findAll(Pageable pageable);

	// 단일 조건 검색 (ex : 글쓴이 검색)
	List<Question> findByAuthor(SiteUser author);
	
	// 복합 조건 검색 (ex : 글쓴이 + 내용)
	List<Question> findBySubjectAndAuthor(String subject, SiteUser author);
	List<Question> findBySubjectOrAuthor(String subject, SiteUser author);
	
	/* 부분 일치 검색 */
	
	// 주어진 문자열과 부분적으로 일치하는 엔티티
	// List<Question> findBySubjectLike(String subject);
	
	// 주어진 문자열로 시작하는 엔티티
	List<Question> findBySubjectStartingWith(String prefix);
	
	// 주어진 문자열로 끝나는 엔티티
	List<Question> findBySubjectEndingWith(String suffix);
	
	// 정렬
	// 특정 글쓴이의 게시글을 최근 순서대로 검색
	List<Question> findByAuthorOrderByCreateDateDesc(SiteUser author);
	
	// 페이지 처리
	// 특정 글쓴이의 게시글을 페이지네이션으로 조회
	Page<Question> findByAuthor(SiteUser author, Pageable pageable);
	
	// 수치적 조건
	List<Question> findByIdGreaterThan(Integer id);
	List<Question> findByIdLessThan(Integer id);
	
	// Null 체크
	List<Question> findByAuthorIsNull();
	List<Question> findByAuthorIsNotNull();
	
//	@Getter
//	@Setter
//	@Entity
//	public class Question {
//
//		// ID (Question 객체의 기본키)
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private Integer id;
//
//		// SUBJECT (질문 제목)
//		@Column(length = 200)
//		private String subject;
//
//		// CONTENT (질문 내용)
//		@Column(columnDefinition = "TEXT")
//		private String content;
//
//		// CREATE_DATE (게시 날짜)
//		@Column
//		private LocalDateTime createDate;
//
//		// LAST_MODIFIED (마지막 수정날짜)
//		@Column
//		private LocalDateTime lastModified;
//
//		// 해당 게시글에 달린 답변 리스트
//		// 이 필드는 데이터베이스에 직접 컬럼으로 생성되지 않습니다.
//		// 대신, Answer 테이블에서 왜래 키(question_id)를 통해 Question 엔티티와 연결됩니다.
//		@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//		private List<Answer> answerList;
//
//		// AUTHOR_ID (게시자 : 한 유저는 여러 질문글을 작성할 수 있다)
//		@ManyToOne
//		private SiteUser author;
//
//	}


}
