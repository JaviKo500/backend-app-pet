package com.back.app.pet.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseRequest {
	
	public ResponseEntity<?> error(Boolean ok, String message, Object error, HttpStatus status){
		Map<String, Object> response = new HashMap<>();
		response.put("ok", ok);
		response.put("message", message);
		response.put("error", error);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	public ResponseEntity<?> success(Boolean ok, String message, Object data, HttpStatus status ){
		Map<String, Object> response = new HashMap<>();
		response.put("ok", ok);
		response.put("message", message);
		response.put("data", data);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	

}
