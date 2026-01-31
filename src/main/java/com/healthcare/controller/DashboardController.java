package com.healthcare.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.service.AppointmentService;
import com.healthcare.service.DoctorService;
import com.healthcare.service.PatientService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	
	
	@GetMapping("/stats")
	public ResponseEntity<Map<String,Object>> getDashboardStats(){
		Map<String, Object> stats=new HashMap<>();
		
		//get count
		long totalPatients=patientService.getAllPatients().size();
		long totalDoctors=doctorService.getAllDoctors().size();
		long totalAppointments= appointmentService.getAllAppointments().size(); 
		
		
		//get today's appointments
		LocalDate today=LocalDate.now();
		long todayAppointments=appointmentService.getAppointmentsByDate(today).size();
		
		// get appointments by status
		
		long scheduledAppointments=appointmentService.getAppointmentsByStatus("SCHEDULED").size();
		long completedAppointments=appointmentService.getAppointmentsByStatus("COMPLETED").size();
		long cancelledAppointments=appointmentService.getAppointmentsByStatus("CANCELLED").size();
		
		//build response
		stats.put("totalPatients", totalPatients);
		stats.put("totalDoctors", totalDoctors);
		stats.put("totalAppointments", totalAppointments);
		stats.put("todayAppointments", todayAppointments);
		stats.put("scheduledAppointments", scheduledAppointments);
		stats.put("completedAppointments", completedAppointments);
		stats.put("cancelledAppointments", cancelledAppointments);
		stats.put("date", today.toString());
		
		return ResponseEntity.ok(stats);
		
	}
	
	
	@GetMapping("/recent-appointments")
	public ResponseEntity<?>getRecentAppointments(@RequestParam(value="limit", defaultValue = "5") int limit){
		
		
		//get all appointments
		var appointments=appointmentService.getAllAppointments();
		
		
		//return last limit appointments
		int size =appointments.size();
		int fromIndex=Math.max(0, size-limit);
		var recentAppointments=appointments.subList(fromIndex, size);
		
		
		return ResponseEntity.ok(recentAppointments);
		
	}
	
	
	
	
	
}
