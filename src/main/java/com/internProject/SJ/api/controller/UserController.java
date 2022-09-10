package com.internProject.SJ.api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internProject.SJ.api.model.Response;
import com.internProject.SJ.api.model.User;
import com.internProject.SJ.api.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
	private final UserServiceImpl userServiceImpl;
	private final Response response;
	
	 @PostMapping("/regist")
	 public ResponseEntity<?> regist(@Validated User user, Errors errors){
		 if(errors.hasErrors()) {
			 return response.invalidFields(response.createErrorList(errors));
		 }
		 return userServiceImpl.register(user);
	 }
	 @PostMapping("/login")
	 public ResponseEntity<?> login(@Validated User user, Errors errors){
		 if(errors.hasErrors()) {
			 return response.invalidFields(response.createErrorList(errors));
		 }	
		 return userServiceImpl.login(user);
	 }
	 
}

