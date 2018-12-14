package com.company.app.common;

import org.springframework.stereotype.Service;

@Service("after")
public class AfterAdvice {
	private void finallyLog() {
		System.out.println("[사후 처리] 비즈니스 로직 수행 후 무조건 실행");

	}
}
