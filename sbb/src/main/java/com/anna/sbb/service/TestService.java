package com.anna.sbb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anna.sbb.domain.Member;
import com.anna.sbb.repository.TestRepository;

import lombok.RequiredArgsConstructor;

// *** 비즈니스 계층 ***
// 비즈니스 로직을 처리함. 퍼시스턴스 계층에서 제공하는 서비스를 사용할 수도 있고,
// 권한을 부여하거나 유효성 검사를 하기도 함.

@RequiredArgsConstructor
@Service
public class TestService {

	private final TestRepository testRepository;
	
	public List<Member> getAllMembers() {
		return this.testRepository.findAll();
	}
	
}

