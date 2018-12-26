package com.company.app.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {

	@Scheduled(cron = "0/10 31,32,33,34 * * * *")
			
	public void job1() {
		System.out.println("tab1 실행");
		
	}
	//@Scheduled(fixedDelay = 5000)
	public void job2() {
		System.out.println("tab2 실행");
	}
	
}
