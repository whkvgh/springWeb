package com.company.app;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailTest {
	public static void main(String args[]) {
		SimpleEmail email = new SimpleEmail();
// 메일서버 설정
		email.setCharset("euc-kr"); // 한글 인코딩
		email.setHostName("smtp.gmail.com"); // 보내는 메일(SMTP) 서버(고정)
		email.setSmtpPort(465); // 포트(고정)
		email.setAuthenticator(new DefaultAuthenticator("whkvgh@gmail.com", "nmwnbwgbqectayek")); // 인증
		email.setSSLOnConnect(true); // SSL 필요
		try {
			email.addTo("whkvgh@naver.com", "조정화"); // 수신자 추가
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.setFrom("whkvgh@naver.com", "Me"); // 보내는 사람
		} catch (EmailException e) {
			e.printStackTrace();
		}
		email.setSubject("메일전송테스트"); // 메일 제목
		email.setContent("simple 메일 Test입니다", "text/plain; charset=euc-kr"); // 메일 내용
		System.out.println("메일 발송 완료");
		try {
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}