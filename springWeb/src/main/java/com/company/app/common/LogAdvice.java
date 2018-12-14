package com.company.app.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service("log")
@Aspect
public class LogAdvice {

	@Pointcut("execution(* com.company.app..*Impl.*(..))")
	//public void allpointcut() {	} 사전 
	public void getpointcut() {}
	
	//@Before("allpointcut()") 사전 
	@AfterReturning("getpointcut()")
	public void pringLog(JoinPoint jp) {
		//서비스 메소드	
		String methodname = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		System.out.println("[공통로그] "+ methodname + 
			((args != null && args.length>0 ) ? args[0] : "" ));
	}
}
