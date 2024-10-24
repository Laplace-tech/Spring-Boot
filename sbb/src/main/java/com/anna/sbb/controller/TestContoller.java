package com.anna.sbb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anna.sbb.domain.Member;
import com.anna.sbb.service.TestService;

import lombok.RequiredArgsConstructor;

// *** 프레젠테이션 계층 ***
// HTTP 리퀘스트를 받고 이를 비즈니스 계층으로 전송함.

@RestController // @Controller와 @ResponseBody가 결합된 어노테이션, 
@RequiredArgsConstructor
public class TestContoller {

	private final TestService testService;
	
	@GetMapping("/test")
	public List<Member> getAllMembers() {
		List<Member> memberList = this.testService.getAllMembers();
		return memberList;
	}

}
