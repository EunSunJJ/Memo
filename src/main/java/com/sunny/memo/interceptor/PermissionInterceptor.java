package com.sunny.memo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		// 1. 로그인 안된 상태에서는 메모 게시글 관련 페이지 접근을 막고 로그인 페이지로 이동
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		// ex. /user/login-view
		String uri= request.getRequestURI();
		
		// 로그인이 안된 경우 , /post로 시작하는 주소로 접근하는 경우 페이지 이동을 막고 로그인 페이지로 이동한다.
		if(userId == null) {
			if(uri.startsWith("/post")) {

				response.sendRedirect("/user/login-view");
				
				return false; // controller로 가는 요청을 중단시켜
				
			}
		} else {  // 로그인이 되어있는 경우 , /user로 시작하는 주소로 접근하는 경우 페이지 이동을 막고 리스트 페이지로 이동한다.
			if(uri.startsWith("/user")) {
				
				response.sendRedirect("/post/list-view");
				
				return false;
			}
		}
		
		return true;
	}
}
