package com.springbook.biz.common;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.springbook.biz.member.MemberVO;

//@Service @Aspect
public class AfterReturningAdvice {
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		// 멤버 role 확인 - 우수회원일때 특별 혜택
		List<MemberVO> memberList = (List<MemberVO>)returnObj;
		for(MemberVO member : memberList) {
			String name = member.getName();
			String role = member.getRole();
			if(role.equals("우수회원")) {
				System.out.println(role + "인 " + name + "님에게는 " + "롯데 호텔 2박 숙식권을 드립니다.");
			}
		}
		
		System.out.println("[사후 처리] " + method + "() 메소드 실행\n리턴값 정보: " + returnObj);
	}

}
