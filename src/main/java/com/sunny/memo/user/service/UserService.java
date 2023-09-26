package com.sunny.memo.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunny.memo.common.EncryptUtils;
import com.sunny.memo.user.domain.User;
import com.sunny.memo.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	// 로그인
	public User getUser(String loginId, String password) {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		Optional<User> optionalUser = userRepository.findByLoginIdAndPassword(loginId, encryptPassword);
		User user = optionalUser.orElse(null);
		
		return user;
	}
	
	// 회원가입
	public User addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);
		
		User user = User.builder()
					.loginId(loginId)
					.password(encryptPassword)
					.name(name)
					.email(email)
					.build();
		
		return userRepository.save(user);
	}
}
