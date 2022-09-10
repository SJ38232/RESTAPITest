package com.internProject.SJ.api.mapper;

import com.internProject.SJ.api.model.User;

public interface UserMapper {
	User findByEmail(String Email);
	boolean existsByEmail(String Email);
	void save(User user);
}
