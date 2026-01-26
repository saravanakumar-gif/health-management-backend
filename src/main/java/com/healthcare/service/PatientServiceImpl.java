package com.healthcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.healthcare.controller.PatientController;
import com.healthcare.model.Patient;
import com.healthcare.repository.PatientRepository;

@Service
public  class PatientServiceImpl implements PatientService{

   
@Autowired
private PatientRepository patientRepository;


   @Override 
public Patient createPatient(Patient patient) {
	if(patient.getName()==null ||patient.getName().trim().isEmpty()) {
		throw new IllegalArgumentException("Name is requried");
	}
	
	if(patient.getPhone()==null || patient.getPhone().trim().isEmpty()) {
		throw new IllegalArgumentException("Phone is requried");
	}
	 return patientRepository.save(patient);
}


@Override
public Patient updatePatient(Long id, Patient patientDetails) {
	Patient patient=patientRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Patient not found with id:" +id));
	if (patientDetails.getName()!=null) {
		patient.setName(patientDetails.getName());
	}
	
	if(patientDetails.getAge()!=null) {
		patient.setAge(patientDetails.getAge());
	}
	
	if(patientDetails.getGender()!=null) {
		patient.setGender(patientDetails.getGender());
	}
	
	if(patientDetails.getPhone()!=null) {
		patient.setPhone(patientDetails.getPhone());
	}
	
	if(patientDetails.getAddress()!=null) {
		patient.setAddress(patientDetails.getAddress());
	}
	return patientRepository.save(patient);
}



@Override
public List<Patient> getAllPatients() {
	return patientRepository.findAll();
}



@Override
public Patient getPatientById(Long id) {
    return patientRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + id));
}



@Override
public void deletePatient(Long id) {
	if (!patientRepository.existsById(id)) {
		throw new IllegalArgumentException("Patient not found with id:" +id);
		}
	patientRepository.deleteById(id);
}



}
