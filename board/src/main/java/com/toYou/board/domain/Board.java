package com.toYou.board.domain;

import java.util.*;
import jakarta.persistence.*;
import lombok.*;

/*
 * 
1. 엔티티 매핑
JPA는 "객체와 데이터베이스 테이블 간의 매핑"을 통해 
관계형 데이터베이스에 저장된 "데이터를 객체 형태로 쉽게 관리"할 수 있게 해줌.
각 JPA 엔티티는 데이터베이스의 한 테이블에 매핑되며, 엔티티의 필드는 테이블의 열(column)에 매핑됨.

2. 인스턴스 생성
JPA가 데이터베이스에서 엔티티를 *조회*할 때, 해당 "엔티티의 인스턴스를 생성해야 함."
이 때 JPA는 리플렉션(reflection)을 사용하여 기본 생성자를 호출합니다.
기본 생성자가 없으면 JPA는 인스턴스를 생성할 수 없기 때문에 **InstantiationException**이 발생합니다.

3. 필드 초기화
기본 생성자로 객체가 생성된 후, JPA는 매핑된 필드에 대해 데이터베이스에서 조회한 값을 설정합니다.
이 과정은 보통 JPA가 내부적으로 관리합니다. 예를 들어, 데이터베이스에서 Board 테이블의 데이터가
SELECT 쿼리를 통해 조회되면, (1) JPA는 기본 생성자를 호출하여 Board 객체를 생성한 뒤, (NoArgsConstructor)
결과 집합에서 가져온 값(레코드?)으로 해당 객체의 필드를 채웁니다.

4. 리플렉션의 사용
기본 생성자를 사용하여 객체를 인스턴스화한 후, 필드에 직접 접근하여 값을 설정할 수도 있습니다.
이 경우, 필드는 접근 제어자가 private인 경우에도 설정할 수 있습니다. 
	
	4-1. 기본 생성자 호출 : JPA는 엔티티 클래스의 기본 생성자를 호출해서 객체를 생성함. (이래서 기본생성자가 필요함)
	4-2. 리플렉션을 통한 필드 접근 (Not Setter!!!) : JPA는 기본 생성자를 통해 생성한 객체의 필드 값을 
	     설정하기 위해 리플렉션을 사용하여 private 필드에 접근할 수 있음.
	4-3. 값 설정 : 결과 집합에서 가져온 값 으로 필드를 설정함.

 */

@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected Board() { }
@Getter
@Entity 
public class Board extends Time {

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// AUTHOR_ID
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Member author;
	
	// SUBJECT
	@Column(length = 60, nullable = false)
	private String subject;
	
	// CONTENT
	@Lob
	@Column(nullable = false)
	private String content;
	
	// VIEW_COUNT
	@Column
	private Integer viewCount = 0;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Answer> answerList = new ArrayList<>();
	
	// BOARD_LIKES = [MEMBER_ID|BOARD_ID]
	// 두 엔티티 사이에 다대다 관계를 표현, 중간 테이블(조인 테이블)을 통해서 이 관계를 매핑함.
	@ManyToMany
	@JoinTable(
		name = "board_likes", 
		joinColumns = @JoinColumn(name = "BOARD_ID"),
		inverseJoinColumns = @JoinColumn(name = "MEMBER_ID")
	)
	private Set<Member> likedUsers = new HashSet<>();

	
	// 사용자로부터 받은 DTO에 담긴 정보를 토대로 찐 엔티티를 만들어서 디비에 넘기기 위함.
	@Builder
	public Board(Member author, String subject, String content) {
		
//		사용자가 HTML에 어느 내용을 쳐넣었겠지? 
//		근데 그 내용이 DTO 객체의 setter를 통해 필드값이 채워지겠지?
//		그 값을 파라미터로 DTO 객체에서 toEntity()를 호출해서 찐 Entity를 생성
//	
//		<<public class BoardDTO >> 
//		public Board toEntity() {
//			return Board.builder()
//					.author(this.author)
//					.subject(this.subject)
//					.content(this.content)
//					.build();
//		}

		this.author = author;
		this.subject = subject;
		this.content = content;
	}
	
}