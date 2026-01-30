package com.healthcare.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="doctors")

public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false)
	private String name;
    
    
	 @Column(nullable = false)
	private String specialization;
	
	private String qualification;
	
	private Integer experience;
	
	@Column(nullable = true)
	private String phone;
	
	private String email;
	
	private Double consultationFee;
	

	public Doctor() {
		
	}
	
	

	public Doctor(Long id, String name, String specialization, String qualification, Integer experience, String phone,
			String email, Double consultationFee) {
		super();
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.qualification = qualification;
		this.experience = experience;
		this.phone = phone;
		this.email = email;
		this.consultationFee = consultationFee;
	}
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(Double consultationFee) {
		this.consultationFee = consultationFee;
	}
	
	// toString()
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", qualification='" + qualification + '\'' +
                ", experience=" + experience +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", consultationFee=" + consultationFee +
                '}';
    }
	
	
}
