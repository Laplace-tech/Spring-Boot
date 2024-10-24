package com.anna.sbb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anna.sbb.domain.Member;

// *** 퍼시스턴스 계층 *** 
// 퍼시스턴스 계층은 스토리지 관련 로직을 처리함.
// 이 과정에서 데이터베이스에 접근하기 위한 객체인 DAO(Data Access Object)를 사용할 수 있음.

@Repository
public interface TestRepository extends JpaRepository<Member, Long>{

}
