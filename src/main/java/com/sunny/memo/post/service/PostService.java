package com.sunny.memo.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sunny.memo.common.FileManager;
import com.sunny.memo.post.domain.Post;
import com.sunny.memo.post.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	// 메모 상세 페이지
	public Post getPost(int id) {
		// 한 행은 Entity class 여러행은 List
		
		return postRepository.selectPost(id);
	}
	
	
	// 메모 리스트
	public List<Post> getPostList(int userId) {
		
		return postRepository.selectPostList(userId);
		 
	}
	
	// 메모 글 쓰기
	public int addPost(int userId, String title, String content, MultipartFile file) {
		
		// file을 특정 디렉토리(폴더)에 저장하고, 
		// 저장된 파일을 접근할 수 있는 url 경로를 만들고 table 저장
		
		String imagePath = FileManager.saveFile(userId, file);
		
		return postRepository.insertPost(userId, title, content, imagePath);
		
	}
	
}
