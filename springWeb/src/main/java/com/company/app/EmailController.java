package com.company.app;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.common.EmailVO;
import com.company.app.common.SendEmailService;


@Controller
public class EmailController {

	@Autowired
	SendEmailService emailService;
	//메일보내기폼
	@RequestMapping("mailForm")
	public String mailForm() {
		return "email/mailForm";
	}
	
	//메일발송처리
	@RequestMapping("mailSend")
	public void mailSend(EmailVO vo,
			        HttpServletResponse response) throws IOException {
		vo.setFrom("whkvgh@naver.com");
		vo.setTo("whkvgh@gmail.com");
		vo.setSubject("test	메일입니다");
		vo.setContent("테스트 메일입니다");
		emailService.send(vo);
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('mail send success!!!');");
		out.println("</script>");
	}
}
