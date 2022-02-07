package com.roytuts.spring.boot.auth.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.boot.auth.service.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {
	
	
}
