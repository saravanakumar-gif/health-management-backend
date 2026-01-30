package com.healthcare.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment>findByPatientId(Long patientId);
	
	List<Appointment> findByDoctor(Long doctor);
	
	List<Appointment>findByAppointmentDate(LocalDate date);
	
	List<Appointment>findByStatus(String status);
	
	List<Appointment>findByPatientAndStatus(Long patientId, String status);
	
	List<Appointment> findByDoctorIdAndStatus(Long doctorId, String status);
	
	List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

}
