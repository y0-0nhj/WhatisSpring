package com.springbook.biz.member;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MemberServiceClient {
	public static void main(String[] args) {
		// 1. 스프일 컨테이너 기동
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. MemberServiceImpl 객체 LookUp
		MemberService memberService = (MemberService)container.getBean("memberService");
		
		// 3. 회원 등록 기능 테스트
		MemberVO member = new MemberVO();
		member.setId("bbb2020");
		member.setPwd("1234");
		member.setName("홍길동");
		member.setRole("우수회원");
		memberService.insertMember(member);
		
		// 4. 회원 목록 확인 기능 테스트
		List<MemberVO> memberList = memberService.getMemberList(member);
		for(MemberVO m : memberList) {
			System.out.println(m);
		}
		
		// 5. 스프링 컨테이너 닫기
		container.close();
	}

}
