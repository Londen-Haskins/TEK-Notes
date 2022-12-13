package com.LondenHaskins.Capstone.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LondenHaskins.Capstone.Entity.UserRole;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
	
	public List<UserRole> findByUserId(Integer userId);
}