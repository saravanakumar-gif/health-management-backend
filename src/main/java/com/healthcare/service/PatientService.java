package com.healthcare.service;

import java.util.List;

import com.healthcare.model.Patient;

public interface PatientService {

List<Patient>getAllPatients();

Patient getPatientById(Long id);

Patient createPatient(Patient patient);

Patient updatePatient(Long id,Patient patient);

void deletePatient(Long id);



}
