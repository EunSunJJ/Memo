package com.sunny.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunny.memo.post.domain.Post;
import com.sunny.memo.post.service.PostService;

// View 화면구성

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// 메모 리스트
	@GetMapping("/list-view")
	public String postList(
			Model model
			, HttpSession session) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<Post> postList = postService.getPostList(userId);
		model.addAttribute("postList", postList);
				
		return "post/list";
	}
	
	// 메모 글쓰기
	@GetMapping("/create-view")
	public String postInput() {
		return "/post/input";
	}

}
