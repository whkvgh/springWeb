package com.company.app.common;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service("afterReturn")
@Aspect
public class AfterReturnningAdvice {
	
	@Pointcut("execution(* com.company.app..*Impl.*(..))")
	public void getpointcut() {}
	
	@AfterReturning(pointcut="getpointcut()", returning="returnObj")	//포인트컷, 리턴닶
	public void afterLog(JoinPoint jp, Object returnObj) {//service 메소드로 리턴값을 가져올 수 있다
		String methodName = jp.getSignature().getName();
		
		System.out.println("[사후처리] " + methodName );
		System.out.println(returnObj);
	}

}
