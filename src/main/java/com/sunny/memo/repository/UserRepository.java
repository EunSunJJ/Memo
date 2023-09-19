package com.sunny.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunny.memo.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
}
