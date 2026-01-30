package com.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import com.healthcare.model.Appointment;

public interface AppointmentService {
	
	Appointment createAppointment(Appointment appointment);
	
	Appointment updateAppointment(Long id, Appointment appointment);
	
	void deleteAppointment(Long id);
	
	Appointment getAppointmentById(Long id);
	
	List<Appointment> getAllAppointments();
	
	List<Appointment>getAppointmentsByPatientId(Long patientId);
	
	List<Appointment> getAppointmentsByDoctorId(Long doctorId);
	
	List<Appointment> getAppointmentsByDate(LocalDate date);
	
	List<Appointment> getAppointmentsByStatus(String status);
	
	Appointment updateAppointmentStatus(Long id, String status);

	
	
}
