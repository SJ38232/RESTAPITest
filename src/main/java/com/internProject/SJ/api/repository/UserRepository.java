package com.internProject.SJ.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.internProject.SJ.api.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	public boolean existsByEmail(String email);
	public User findByEmail(String email);
}
