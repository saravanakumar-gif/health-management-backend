package com.healthcare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.Doctor;
import com.healthcare.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		List<Doctor> doctors=doctorService.getAllDoctors();
		return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getDoctorById(@PathVariable("id") Long id ){
		try {
			Doctor doctor=doctorService.getDoctorById(id);
			return ResponseEntity.ok(doctor);
		}
		catch(IllegalArgumentException e) {
			Map<String, String> error = new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			
		}		
	}
	
	
	
	@GetMapping("/specialization/{specialization}")
	public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String sepcialization){
		List<Doctor> doctors=doctorService.getDoctorsBySpecialization(sepcialization);
		return ResponseEntity.ok(doctors);
	}
	
	
	@PostMapping
	public ResponseEntity<?> createDoctor(@RequestBody Doctor doctor){
		try {
			Doctor savedDoctor=doctorService.createDoctor(doctor);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
		}
		catch(IllegalArgumentException e) {
			Map<String, String> error= new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor){
		try {
			Doctor updatedDoctor=doctorService.updateDoctor(id, doctor);
			return ResponseEntity.ok(updatedDoctor);
		}
		catch(IllegalArgumentException e) {
			Map<String,String>error=new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteDoctor(@PathVariable("id") Long id){
		try {
			doctorService.deleteDoctor(id);
			Map<String, String>response=new HashMap<>();
			response.put("message", "Doctor deleted successfully");
			return ResponseEntity.ok(response);
		
		}
		catch(IllegalArgumentException e) {
			Map<String,String> error = new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}
	}
	
}
