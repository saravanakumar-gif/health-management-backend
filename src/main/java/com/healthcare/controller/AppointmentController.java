package com.healthcare.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.model.Appointment;
import com.healthcare.service.AppointmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
 
	@Autowired
 private AppointmentService appointmentService;

 @GetMapping
 public ResponseEntity<List<Appointment>> getAllAppointments(){
	
	 List<Appointment>appointments=appointmentService.getAllAppointments();
	 return ResponseEntity.ok(appointments); 
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<?>getAppointmentById(@PathVariable("id") Long id){
	
	 try {
		 Appointment appointment=appointmentService.getAppointmentById(id);
		 return ResponseEntity.ok(appointment);
	 }
	 catch(IllegalArgumentException e) {
		 Map<String, String> error=new HashMap<>();
		 error.put("error", e.getMessage());
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	 }
 }
 
 
 @GetMapping("/patient/{patientId}")
	public ResponseEntity<List<Appointment>>getAppointmentsByPatient(@PathVariable("patientId") Long patientId){
	List<Appointment> appointments=appointmentService.getAppointmentsByPatientId(patientId);
	 return ResponseEntity.ok(appointments);
 }
 
 
 @GetMapping("/doctor/{doctorId}")
 public ResponseEntity<List<Appointment>>getAppointmentsByDoctor(@PathVariable("doctorId") Long doctorId){
	 List<Appointment>appointments=appointmentService.getAppointmentsByDoctorId(doctorId);
	 return ResponseEntity.ok(appointments);
 }
 
 
 
 @GetMapping("/date/{date}")
 public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate date){
      List<Appointment> appointments=appointmentService.getAppointmentsByDate(date); 
	 return ResponseEntity.ok(appointments);	 
 }
 
 @GetMapping("/status/{status}")
 public ResponseEntity<List<Appointment>> getAppointmentsByStatus(@PathVariable("status")   String status ){
	 List<Appointment>appointments=appointmentService.getAppointmentsByStatus(status);
	 return ResponseEntity.ok(appointments);
 }
 
 
 @PostMapping
 public ResponseEntity<?>createAppointments(@RequestBody Appointment appointment){
	try {
		Appointment savedAppointment=appointmentService.createAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
	}
	catch(IllegalArgumentException e) {
		Map<String, String> error=new HashMap<>();
		error.put("error", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
 }
	
 
 
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAppointment(@PathVariable("id") Long id, @RequestBody Appointment appointment){
		
		try {
			Appointment updateAppointment =appointmentService.updateAppointment(id, appointment);
			return ResponseEntity.ok(updateAppointment);
		}
		catch(IllegalArgumentException e) {
			Map<String, String> error= new HashMap<>();
			error.put("error",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}	
	}
	
	 
 @PatchMapping("/{id}/status")
 public ResponseEntity<?>updateAppointmentStatus(@PathVariable("id") Long id, @RequestBody Map<String,String> statusUpdate){
	try {
		String status=statusUpdate.get("status");
		if(status ==null || status.isEmpty()) {
			Map<String, String>error=new HashMap<>();
			error.put("error", "Status is required!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		}
		
		Appointment updateAppointment=appointmentService.updateAppointmentStatus(id, status);
		return ResponseEntity.ok(updateAppointment);
	}
	catch(IllegalArgumentException e) {
		Map<String, String> error=new HashMap<>();
		error.put("error", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}	
 }
 
 
 
 @DeleteMapping("/{id}")
 public ResponseEntity<?>deleteAppointment(@PathVariable("id") Long id){
	 
	 try {
		 appointmentService.deleteAppointment(id);
		 Map<String,String> response=new HashMap<>();
		 response.put("message", "Appointment deleted successfully");
		 return ResponseEntity.ok(response);
	 }
	 catch (IllegalArgumentException e) {
         Map<String, String> error = new HashMap<>();
         error.put("error", e.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
     }
 }
 
 
 
 
 
}
