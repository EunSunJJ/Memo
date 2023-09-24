package com.sunny.memo.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.memo.post.domain.Post;
import com.sunny.memo.post.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	// 메모 리스트
	public List<Post> getPostList(int userId) {
		
		return postRepository.selectPostList(userId);
		 
	}
	
	// 메모 글 쓰기
	public int addPost(int userId, String title, String content) {
		
		return postRepository.insertPost(userId, title, content);
		
	}
	
}
