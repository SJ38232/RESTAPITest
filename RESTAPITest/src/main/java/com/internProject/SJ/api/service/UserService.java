package com.internProject.SJ.api.service;

import org.springframework.http.ResponseEntity;

import com.internProject.SJ.api.entity.User;

public interface UserService {
	public ResponseEntity<?> register(User user);
	public ResponseEntity<?> login(User user);
	public ResponseEntity<?> logout(User user);
}