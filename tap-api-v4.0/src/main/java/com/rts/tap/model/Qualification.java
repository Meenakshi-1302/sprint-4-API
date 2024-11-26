package com.rts.tap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qualifications")
public class Qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qualification_id")
	private Long qualificationId;

	@Enumerated(EnumType.STRING)
	@Column(name = "typeOfEducation")
	private TypeOfEducation typeOfEducation;

	@Column(name = "institution")
	private String institutionName;

	@Column(name = "year_of_completion")
	private Integer yearOfCompletion;

	public Long getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}

	public TypeOfEducation getTypeOfEducation() {
		return typeOfEducation;
	}

	public void setTypeOfEducation(TypeOfEducation typeOfEducation) {
		this.typeOfEducation = typeOfEducation;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Integer getYearOfCompletion() {
		return yearOfCompletion;
	}

	public void setYearOfCompletion(Integer yearOfCompletion) {
		this.yearOfCompletion = yearOfCompletion;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public byte[] getCertificates() {
		return certificates;
	}

	public void setCertificates(byte[] certificates) {
		this.certificates = certificates;
	}

	public Qualification(Long qualificationId, TypeOfEducation typeOfEducation, String institutionName,
			Integer yearOfCompletion, String grade, LocalDateTime createdDate, LocalDateTime updatedDate,
			byte[] certificates) {
		super();
		this.qualificationId = qualificationId;
		this.typeOfEducation = typeOfEducation;
		this.institutionName = institutionName;
		this.yearOfCompletion = yearOfCompletion;
		this.grade = grade;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.certificates = certificates;
	}

	@Column(name = "grade")
	private String grade;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Lob
	@Column(nullable = false, length = 1000000)
	private byte[] certificates;

	public enum TypeOfEducation {
		SSLC, HSC, DIPLOMA, PG, UG
	}
	@PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now(); // Only update updatedDate on update
    }
}
