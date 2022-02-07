package com.roytuts.spring.boot.auth.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.roytuts.spring.boot.auth.service.entity.User;

public interface UserDao extends JpaRepository<User,String> {
	
	@Query("select u from User u where u.userName=:userName")
	public User getUserName(@Param("userName") String email);
}
