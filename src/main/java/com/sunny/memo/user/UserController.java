package com.sunny.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//View 페이지를 위한 Controller
@Controller
@RequestMapping("/user")
public class UserController {

	// 회원가입 화면
	@GetMapping("/join-view")
	public String joinInput() {
		return "user/join";
	}
	
	// 로그인 화면
	@GetMapping("/login-view")
	public String loginInput() {
		return "user/login";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/login-view";
		
	}
}
