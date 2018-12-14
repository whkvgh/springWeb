package com.company.app.common;


import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

@Service("afterThrowing")
public class AfterThrowingAdvice {
	private void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();
		
		System.out.println("[예외처리] 비즈니스 로직수행 중 예외발생");
		System.out.println(exceptObj.getMessage());
	}

}
