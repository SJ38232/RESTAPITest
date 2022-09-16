package com.internProject.SJ.api.service;

import org.springframework.http.ResponseEntity;

public interface TaskService {
	public ResponseEntity<?> insertTask();
	public ResponseEntity<?> selectTask();
	public ResponseEntity<?> updateTask();
	public ResponseEntity<?> deleteTask();
}
