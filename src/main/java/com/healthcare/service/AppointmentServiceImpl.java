package com.healthcare.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.model.Appointment;
import com.healthcare.model.Doctor;
import com.healthcare.model.Patient;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.PatientRepository;


@Service
public class AppointmentServiceImpl implements AppointmentService {
	

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	 @Override
	    public Appointment createAppointment(Appointment appointment) {
	        // Validation
	        if(appointment.getPatient()==null || appointment.getPatient().getId()==null) {
	            throw new IllegalArgumentException("Patient is required!");
	        }
	        if(appointment.getDoctor()==null || appointment.getDoctor().getId()==null) {
	            throw new IllegalArgumentException("Doctor is required!");
	        }
	        if(appointment.getAppointmentDate()==null) {
	            throw new IllegalArgumentException("Appointment date is required!");
	        }
	        if(appointment.getAppointmentTime()==null) {
	            throw new IllegalArgumentException("Appointment time is required!");
	        }
	        
	        // Fetch patient
	        Patient patient = patientRepository.findById(appointment.getPatient().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + appointment.getPatient().getId()));
	        
	        // Fetch doctor
	        Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + appointment.getDoctor().getId()));
	        
	        // Set the full objects
	        appointment.setPatient(patient);
	        appointment.setDoctor(doctor);
	        
	        // Set default status
	        if(appointment.getStatus()==null || appointment.getStatus().isEmpty()) {
	            appointment.setStatus("SCHEDULED");
	        }
	        
	        // Save and return
	        return appointmentRepository.save(appointment);
	    }
	    
	    @Override
	    public Appointment updateAppointment(Long id, Appointment appointmentDetails) {
	        Appointment appointment = appointmentRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));
	        
	        // Update patient if provided
	        if (appointmentDetails.getPatient() != null && appointmentDetails.getPatient().getId() != null) {
	            Patient patient = patientRepository.findById(appointmentDetails.getPatient().getId())
	                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
	            appointment.setPatient(patient);
	        }
	        
	        // Update doctor if provided
	        if (appointmentDetails.getDoctor() != null && appointmentDetails.getDoctor().getId() != null) {
	            Doctor doctor = doctorRepository.findById(appointmentDetails.getDoctor().getId())
	                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
	            appointment.setDoctor(doctor);
	        }
	        
	        if (appointmentDetails.getAppointmentDate() != null) {
	            appointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
	        }
	        
	        if (appointmentDetails.getAppointmentTime() != null) {
	            appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
	        }
	        
	        if (appointmentDetails.getStatus() != null && !appointmentDetails.getStatus().isEmpty()) {
	            appointment.setStatus(appointmentDetails.getStatus());
	        }
	        
	        if (appointmentDetails.getReason() != null) {
	            appointment.setReason(appointmentDetails.getReason());
	        }
	        
	        if (appointmentDetails.getNotes() != null) {
	            appointment.setNotes(appointmentDetails.getNotes());
	        }
	        
	        return appointmentRepository.save(appointment);
	    }
	    
	    @Override
	    public void deleteAppointment(Long id) {
	        if (!appointmentRepository.existsById(id)) {
	            throw new IllegalArgumentException("Appointment not found with id: " + id);
	        }
	        appointmentRepository.deleteById(id);
	    }
	    
	    @Override
	    public Appointment getAppointmentById(Long id) {
	        return appointmentRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));
	    }
	    
	    @Override
	    public List<Appointment> getAllAppointments() {
	        return appointmentRepository.findAll();
	    }
	    
	    @Override
	    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
	        return appointmentRepository.findByPatient_Id(patientId);
	    }
	    
	    @Override
	    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
	        return appointmentRepository.findByDoctor_Id(doctorId);  
	    }
	    
	    @Override
	    public List<Appointment> getAppointmentsByDate(LocalDate date) {
	        return appointmentRepository.findByAppointmentDate(date);
	    }
	    
	    @Override
	    public List<Appointment> getAppointmentsByStatus(String status) {
	        return appointmentRepository.findByStatus(status);
	    }
	    
	    @Override
	    public Appointment updateAppointmentStatus(Long id, String status) {
	        Appointment appointment = appointmentRepository.findById(id)
	            .orElseThrow(() -> new IllegalArgumentException("Appointment not found with id: " + id));
	        
	        // Validate status
	        if (!status.equals("SCHEDULED") && !status.equals("COMPLETED") && 
	            !status.equals("CANCELLED") && !status.equals("NO_SHOW")) {
	            throw new IllegalArgumentException("Invalid status. Must be: SCHEDULED, COMPLETED, CANCELLED, or NO_SHOW");
	        }
	        
	        appointment.setStatus(status);
	        return appointmentRepository.save(appointment);
	    }
	
}
