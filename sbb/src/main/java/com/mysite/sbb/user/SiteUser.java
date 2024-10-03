package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SiteUser {

	// ID : SiteUser의 기본키
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// USER_NAME : 유저의 이름
	// unique = true : 해당 컬럼의 값이 데이터베이스 테이블 내에서 유일해야 한다는 것을 의미
	// name = "USER_NAME" : 데이터베이스 테이블에서 해당 컬럼의 이름을 지정
	@Column(name = "USER_NAME", unique = true)
	private String userName;

	// PASSWORD : 유저의 비밀번호
	@Column
	private String password;

	// EMAIL : 유저의 이메일주소
	// unique = true : 해당 컬럼의 값이 데이터베이스 테이블 내에서 유일해야 한다는 것을 의미
	@Column(unique = true)
	private String email;
}
