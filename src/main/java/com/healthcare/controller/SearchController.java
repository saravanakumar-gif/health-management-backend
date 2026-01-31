package com.healthcare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.Doctor;
import com.healthcare.model.Patient;
import com.healthcare.service.AppointmentService;
import com.healthcare.service.DoctorService;
import com.healthcare.service.PatientService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private  DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	@GetMapping
	public ResponseEntity<Map<String,Object>> globalSearch(@RequestParam("keyword") String keyword){
		
		Map<String,Object>results=new HashMap<>();
		
		//search patients
		
		List<Patient> patients= patientService.getAllPatients().stream().filter(p-> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
				 (p.getPhone() !=null && p.getPhone().contains(keyword))).collect(Collectors.toList());
		
		
		List<Doctor>doctors=doctorService.getAllDoctors().stream().filter(d->d.getName().toLowerCase().contains(keyword.toLowerCase()) ||d.getSpecialization().toLowerCase().contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
		
		
		results.put("patients", patients);
		results.put("doctors", doctors);
		results.put("totalResults", patients.size()+doctors.size());
		
		return ResponseEntity.ok(results);
	
	}

}
