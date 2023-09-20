package com.sunny.memo.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// View 화면구성

@RequestMapping("/post")
@Controller
public class PostController {

	@GetMapping("/list-view")
	public String postList() {
		return "post/list";
	}
}
