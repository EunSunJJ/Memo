package com.sunny.memo.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunny.memo.post.domain.Post;
import com.sunny.memo.post.service.PostService;

// View 화면구성

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// 메모 상세 페이지
	@GetMapping("/detail-view")
	public String postDetail(
			@RequestParam("id") int id
			, Model model) {
		
		// model 데이터 -> 무슨데이터? 메모내용
		Post post = postService.getPost(id);
		
		// model에 data 담아놨어
		model.addAttribute("post", post);
		
		
		return "post/detail";
	}
	
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
