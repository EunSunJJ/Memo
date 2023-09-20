package com.sunny.memo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.memo.repository.UserRepository;
import com.sunny.memo.user.domain.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		// 비밀번호 암호화
		
		User user = User.builder()
					.loginId(loginId)
					.password(password)
					.name(name)
					.email(email)
					.build();
		
		return userRepository.save(user);
	}
}
