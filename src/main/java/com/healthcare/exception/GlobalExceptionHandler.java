package com.healthcare.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String,String>> handleIllegalArgument(IllegalArgumentException ex){
		
		Map<String, String>error=new HashMap<>();
		error.put("error",ex.getMessage());
		error.put("status", "400");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);	
	}
	
	
	@ExceptionHandler(Exception .class )
	public ResponseEntity<Map<String, String>> handleGenericException(Exception ex){
		Map<String,String>error =new HashMap<>();
		error.put("error", "An error occurred:" +ex.getMessage());
		error.put("status", "500");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	
	
}
