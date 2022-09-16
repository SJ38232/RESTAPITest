package com.internProject.SJ.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.internProject.SJ.api.entity.Response;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
	private final Response response;
	private Thread thread = Thread.currentThread();
	@Override
	public ResponseEntity<?> insertTask() {
		try {
			thread.sleep((int)(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.success("삽입에 성공했습니다");
	}

	@Override
	public ResponseEntity<?> selectTask() {
		try {
			thread.sleep((int)(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.success("조회에 성공했습니다");
	}

	@Override
	public ResponseEntity<?> updateTask() {
		try {
			thread.sleep((int)(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.success("수정에 성공했습니다");
	}

	@Override
	public ResponseEntity<?> deleteTask() {
		try {
			thread.sleep((int)(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.success("삭제에 성공했습니다");
	}

}
