package com.sunny.memo.post.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sunny.memo.post.domain.Post;

@Repository
public interface PostRepository {
	
	
	// 메모 리스트
	public List<Post> selectPostList(int userId);
	
	
	// 메모 글쓰기
	public int insertPost(
			@Param("userId") int userId
			, @Param("title") String title
			, @Param("content") String content);
}
