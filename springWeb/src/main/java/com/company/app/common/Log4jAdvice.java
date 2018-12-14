package com.company.app.common;

import org.springframework.stereotype.Service;

@Service("log4j")
public class Log4jAdvice {
	
	public void printLogging() {
		System.out.println("[공통 로그 -log4] 비즈니스 로직 수행전 실행");
	}
	
}
