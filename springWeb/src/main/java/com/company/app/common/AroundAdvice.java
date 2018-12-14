package com.company.app.common;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service("around")
public class AroundAdvice {	//실행시간을 확인 할 수 있다. 통계정보 쌓일 수있다..
	

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		//System.out.println("[around before] 비즈니스 메서드 수행 전");
		Object returnObj = pjp.proceed();	//서비스 메소드 호출
		stopWatch.stop();
		String methodName = pjp.getSignature().getName();
		System.out.println(methodName + " : 수행시간 " + stopWatch.getTotalTimeMillis());
		//System.out.println("[around after] 비즈니스 메서드 수행후 ");
		return returnObj;
	}
}
