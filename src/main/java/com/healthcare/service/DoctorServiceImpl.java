package com.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Doctor createDoctor(Doctor doctor) {
		if(doctor.getName()==null || doctor.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Doctor name is required!");
		}
		
		if(doctor.getSpecialization()==null || doctor.getSpecialization().trim().isEmpty()) {
			throw new IllegalArgumentException("Specialization is required!");
		}
		
		if(doctor.getPhone()==null||doctor.getPhone().trim().isEmpty()) {
			throw new IllegalArgumentException("Phone is required!");
		}
		
		 // Check duplicate phone
        Doctor existingDoctor = doctorRepository.findByPhone(doctor.getPhone());
        if (existingDoctor != null) {
            throw new IllegalArgumentException("Phone number already exists!");
        }
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Long id, Doctor doctorDetails) {
	    // Fetch fresh data from database
	    Doctor doctor = doctorRepository.findById(id)
	        .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + id));
	    
	    // Update only changed fields
	    if (doctorDetails.getName() != null && !doctorDetails.getName().trim().isEmpty()) {
	        doctor.setName(doctorDetails.getName());
	    }
	    if (doctorDetails.getSpecialization() != null && !doctorDetails.getSpecialization().trim().isEmpty()) {
	        doctor.setSpecialization(doctorDetails.getSpecialization());
	    }
	    if (doctorDetails.getQualification() != null) {
	        doctor.setQualification(doctorDetails.getQualification());
	    }
	    if (doctorDetails.getExperience() != null) {
	        doctor.setExperience(doctorDetails.getExperience());
	    }
	    if (doctorDetails.getPhone() != null && !doctorDetails.getPhone().trim().isEmpty()) {
	        Doctor existingDoctor = doctorRepository.findByPhone(doctorDetails.getPhone());
	        if (existingDoctor != null && !existingDoctor.getId().equals(id)) {
	            throw new IllegalArgumentException("Phone number already exists!");
	        }
	        doctor.setPhone(doctorDetails.getPhone());
	    }
	    if (doctorDetails.getEmail() != null) {
	        doctor.setEmail(doctorDetails.getEmail());
	    }
	    if (doctorDetails.getConsultationFee() != null) {
	        doctor.setConsultationFee(doctorDetails.getConsultationFee());
	    }
	    return doctorRepository.save(doctor);
	}
	
	@Override
	public void deleteDoctor(Long id) {
		if(!doctorRepository.existsById(id)) {
			throw new IllegalArgumentException("Doctor not found with id:" +id);
		}
		doctorRepository.deleteById(id);
	}

	@Override
	public Doctor getDoctorById(Long id) {
	    return doctorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Doctor not found with id:" +id));
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Override
	public List<Doctor> getDoctorsBySpecialization(String specialization) {
		return doctorRepository.findBySpecialization(specialization);
	}
	
	
}
