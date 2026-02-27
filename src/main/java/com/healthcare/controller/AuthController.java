package com.healthcare.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.AuthResponse;
import com.healthcare.dto.LoginRequest;
import com.healthcare.dto.RegisterRequest;
import com.healthcare.model.User;
import com.healthcare.repository.UserRepository;




@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")

public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody RegisterRequest request){
		
		try {
			if(userRepository.existsByPhone(request.getPhone())) {
				Map<String, String>error=new HashMap<>();
				error.put("error", "Phone number already registered");
				return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
			}
			
			User user =new User();
			user.setName(request.getName());
			user.setPhone(request.getPhone());
			user.setPassword(request.getPassword());
			user.setAge(request.getAge());
			user.setGender(request.getGender());
			user.setAddress(request.getAddress());
			user.setCreatedAt(LocalDateTime.now().toString());
			
			User savedUser=userRepository.save(user);
			
			savedUser.setPassword(null);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse("Resgistration successfull", savedUser));
			
		}catch (Exception e) {
			Map<String,String> error=new HashMap<>();
			error.put("error", "Registration failed:" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
						
	}
	
	
	
	
	
	@PostMapping ("/login")
	
	public ResponseEntity<?> login(@RequestBody LoginRequest request){
		
		try {
			Optional<User>userOptional=userRepository.findByPhone(request.getphone());
			if(userOptional.isEmpty()) {
				Map<String,String> error=new HashMap<>();
				error.put("error", "Invalid phone number or password");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			}
			
			User user=userOptional.get();
			if(!user.getPassword().equals(request.getPassword())) {
				Map<String,String> error=new HashMap<>();
				error.put("error", "Invalid phone number or password");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
			}
			
			user.setPassword(null);
			return ResponseEntity.ok(new AuthResponse("Login successfull", user));
		}catch (Exception e) {
			Map<String,String>error=new HashMap<>();
			error.put("error", "Login failed:" +e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
			
		}
	
	
	@GetMapping("/me")
	public ResponseEntity<?>getCurrentUser(@RequestParam String phone){
		
		try {
			Optional<User>userOptional=userRepository.findByPhone(phone);
			
			if(userOptional.isEmpty()) {
				Map<String,String>error=new HashMap<>();
				error.put("error", "User not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			}
			
			User user=userOptional.get();
			user.setPassword(null);
			return ResponseEntity.ok(user);
			
		}
		catch (Exception e) {
			Map<String,String>error=new HashMap<>();
			error.put("error","Failed to get user:" +e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
		
		
		
		
	}
	
	

	
	
	
	
	
	
	
}
