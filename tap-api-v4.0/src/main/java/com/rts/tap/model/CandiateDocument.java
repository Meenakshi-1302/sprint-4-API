package com.rts.tap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CandiateDocuments")
public class CandiateDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long documentId;

	@Column
	private String payslip;

	@Column
	private String experienceLetter;

	@Column
	private String degereeCertificate;

	@Column
	private String aadhar;

	@Column
	private String panCard;

	@Column
	private String candidateProfilePicture;

	@Column
	private String passport;

	@Column
	private String relievingLetter;
	
	private String statusDescription;
	private String Status;	

}
