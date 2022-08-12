package com.internProject.SJ.api.entity;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import lombok.Builder;
import lombok.Getter;

@Component
public class Response {
	@Getter
    @Builder
    private static class Body {

        private int state;
        private String result;
        private String massage;
        private Object data;
        private Object error;
    }
	public ResponseEntity<?> success(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("success")
                .massage(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }
	public ResponseEntity<?> success(String msg) {
        return success(Collections.emptyList(), msg, HttpStatus.OK);
    }
	public ResponseEntity<?> success(Object data) {
	        return success(data, null, HttpStatus.OK);
	}
	public ResponseEntity<?> success() {
        return success(Collections.emptyList(), null, HttpStatus.OK);
    }
	public ResponseEntity<?> fail(Object data, String msg, HttpStatus status) {
        Body body = Body.builder()
                .state(status.value())
                .data(data)
                .result("fail")
                .massage(msg)
                .error(Collections.emptyList())
                .build();
        return ResponseEntity.ok(body);
    }
	public ResponseEntity<?> fail(String msg, HttpStatus status) {
        return fail(Collections.emptyList(), msg, status);
    }

    public ResponseEntity<?> invalidFields(LinkedList<LinkedHashMap<String, String>> errors) {
        Body body = Body.builder()
                .state(HttpStatus.BAD_REQUEST.value())
                .data(Collections.emptyList())
                .result("fail")
                .massage("")
                .error(errors)
                .build();
        return ResponseEntity.ok(body);
    }
    
    public LinkedList<LinkedHashMap<String, String>> createErrorList(Errors errors){ 
		 LinkedList<LinkedHashMap<String, String>> errorList = new LinkedList<LinkedHashMap<String, String>>();
	 errors.getFieldErrors().forEach(e-> {
	     LinkedHashMap<String, String> error = new LinkedHashMap<>();
	     error.put("field", e.getField());
	     error.put("message", e.getDefaultMessage());
	     errorList.push(error);
	 	});
	return errorList;
    }
}
