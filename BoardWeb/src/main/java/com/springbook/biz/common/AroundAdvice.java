package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

// ProceedingJoinPoint 클래스 - 비즈니스 메소드를 정보를 갖게됨. Around Advice에서 사용

//@Service @Aspect
public class AroundAdvice {
		
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		//System.out.println("[before] 비즈니스 메소드 실행 전 동작.");
		
		String method = pjp.getSignature().getName();
		
		StopWatch sw = new StopWatch();
		sw.start();
		Object returnObj = pjp.proceed(); // 비즈니스 메소드의 실행
		sw.stop();
		long time = sw.getTotalTimeMillis();
		System.out.println("[around advice 활용] " + method + "() 수행 시간 : " + time + "ms");
		
		//System.out.println("[ after] 비즈니스 메소드 실행 후 동작.");
		
		return returnObj;
	}

}
