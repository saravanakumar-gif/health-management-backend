package com.healthcare.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="appointments")
public class Appointment {
	
	

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="patient_id",nullable = false)
	private Patient patient;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="doctor_id",nullable = false)
	private Doctor doctor;
	
	@Column(nullable = false)
	private LocalDate appointmentDate;
	
	@Column(nullable = false)
	private LocalTime appointmentTime;
	
	@Column(nullable = false)
	private String status;
	
	@Column(columnDefinition = "TEXT")
	private String reason;
	
	@Column(columnDefinition = "TEXT")
	private String notes;
	

	public Appointment() {
		
	}

	public Appointment(Patient patient, Doctor doctor, LocalDate appointmentDate, LocalTime appointmentTime,
			String status, String reason, String notes) {
		super();
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.status = status;
		this.reason = reason;
		this.notes = notes;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public  Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Appointment [patient=" + patient + ", doctor=" + doctor + ", appointmentDate=" + appointmentDate
				+ ", appointmentTime=" + appointmentTime + ", status=" + status + ", reason=" + reason + ", notes="
				+ notes + "]";
	}
	
	
	

}
