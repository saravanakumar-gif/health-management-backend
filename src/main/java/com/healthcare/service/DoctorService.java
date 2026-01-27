package com.healthcare.service;

import java.util.List;

import com.healthcare.model.Doctor;

public interface DoctorService {
	Doctor createDoctor(Doctor doctor);
	
	void deleteDoctor(Long id);
	
	Doctor getDoctorById(Long id);
	
	List<Doctor> getAllDoctors();
	
	List<Doctor>getDoctorsBySpecialization(String specialization);

	Doctor updateDoctor(Long id, Doctor doctorDetails);

}
