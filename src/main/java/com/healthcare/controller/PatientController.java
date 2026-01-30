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

import com.healthcare.model.Patient;
import com.healthcare.service.PatientService;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	//get all patients
	
	@GetMapping
	public ResponseEntity< List<Patient>>getAllPatients(){
		List<Patient>patients=patientService.getAllPatients();
		return ResponseEntity.ok(patients);
		
	}
	
	
	//get patient by id
	 @GetMapping("/{id}")
	    public ResponseEntity<?> getPatientById(@PathVariable("id") Long id) {
	        try {
	            Patient patient = patientService.getPatientById(id);
	            return ResponseEntity.ok(patient);
	        }
	        catch (IllegalArgumentException e) {
	            Map<String, String> error = new HashMap<>();
	            error.put("error", e.getMessage());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	        }
	    }
	
	//create new patient
	@PostMapping
	public ResponseEntity<?>createPatient(@RequestBody Patient patient) {
		try {
			Patient savedPatient=patientService.createPatient(patient);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
		}
		catch(IllegalArgumentException e) {
			Map<String, String> error=new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
	
		
	}
	
	//update by id
	@PutMapping("/{id}")
	
	public ResponseEntity<?>updatePatient(@PathVariable("id")Long id, @RequestBody Patient patient){
		try {
			Patient updatedPatient=patientService.updatePatient(id, patient);
			return ResponseEntity.ok(updatedPatient);
		}
		 catch(IllegalArgumentException e){
			Map<String,String>error=new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
			
		}
		
		
	}
	
	//delete by id
	
	@DeleteMapping("/{id}")
	
	 public ResponseEntity<?> deletePatient(@PathVariable("id") Long id){
		try {
			patientService.deletePatient(id);
			Map<String,String>response=new HashMap<>();
			response.put("message", "patient delected successfully");
			return ResponseEntity.ok(response);
		}
		catch(IllegalArgumentException e) {
			Map<String, String> error=new HashMap<>();
			error.put("error",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			
		}
	}
	
	

}
