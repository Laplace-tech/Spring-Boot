package com.anna.sbb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Test
	void contextLoads() {

		// ① given : 데이터를 저장하기 위한 준비 과정

		// ② when : 실제로 데이터를 저장

		// ③ then :	데이터가 잘 추가되었는지 검증
		
		int a = 1;
		int b = 2;
		int sum = 3;
		
		Assertions.assertEquals(sum + 1, a + b);
	}

}
