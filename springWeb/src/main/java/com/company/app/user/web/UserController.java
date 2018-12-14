package com.company.app.user.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.app.user.UserService;
import com.company.app.user.UserVO;

@Controller
public class UserController {
	
	@Autowired 	UserService userservice;
	
	// 로그인 폼
	@RequestMapping("loginForm")
	public String loginForm() {
		return "user/login";
	}
	

	// 로그인 처리
	@RequestMapping("login")	//model.addAttribute("user", vo// 속성명 정하기 제일 앞 한글 자를 소문자로 변경)
	public String login(@ModelAttribute("user") UserVO vo, HttpSession session) {
		// id 단건 조회
		UserVO uservo = userservice.getuser(vo);
		// 조회된 id가 있으면 패스워드 비교
		if (uservo == null) { // id가 없는 경우
			return "user/login";
		} else if (!vo.getPassword().equals(uservo.getPassword())) {	//패스워드 오류
			return "user/login";
		} else {
			session.setAttribute("login", uservo);
			return "redirect:getBoardList";
		}

	}
	//전체 회원 조회
	@RequestMapping("/getUserList")
	public String getUserList(Model model, UserVO vo) {
		model.addAttribute("userList", userservice.getuserList(vo));
		return "user/getUserList";
	}
	
	// 단건 조회
	@RequestMapping("/getuser")
	public String getUser(Model model, UserVO vo) {
		model.addAttribute("user", userservice.getuser(vo));
		return "user/getuser";
	}
	//로그아웃
	//로그아웃 처리

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();//세션 무효화
		return "user/login";
	}
}
