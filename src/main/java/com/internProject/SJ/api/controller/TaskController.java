package com.internProject.SJ.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internProject.SJ.api.entity.Response;
import com.internProject.SJ.api.entity.User;
import com.internProject.SJ.api.service.TaskServiceImpl;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/api/task")
@RestController
public class TaskController {
	private final TaskServiceImpl taskServiceImpl;
	private final Response response;
	
	@PostMapping("insertTask")
	public ResponseEntity<?> insertTask(){
		 return taskServiceImpl.insertTask();
	}
	@GetMapping("selectTask")
	public ResponseEntity<?> selectTask(){
		 return taskServiceImpl.selectTask();
	}
	@PutMapping("updateTask")
	public ResponseEntity<?> updateTask(){
		 return taskServiceImpl.updateTask();
	}
	@DeleteMapping("deleteTask")
	public ResponseEntity<?> deleteTask(){
		 return taskServiceImpl.deleteTask();
	}
}
