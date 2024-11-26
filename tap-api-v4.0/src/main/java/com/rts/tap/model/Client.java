package com.rts.tap.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "client_tbl")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	@Column
	private String clientName;

	@Column
	private String clientPosition;

	@Column
	private String clientMobile;

	@Column
	private String clientEmail;

	@Column
	private String password;

	@Column
	private String clientStatus;

	@Column
	private Date createdAt;

	@Column
	private String otp;

	@Column
	private LocalDateTime expirationTime;

	@ManyToOne
	@JoinColumn(name = "clientPartnerId")
	private Employee clientPartner;

	@OneToOne
	private ClientOrganization clientOrganization;

	@OneToOne
	private ThirdPartyCredentitals thirdPartyCredentitals;

	public Client() {
		super();
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPosition() {
		return clientPosition;
	}

	public void setClientPosition(String clientPosition) {
		this.clientPosition = clientPosition;
	}

	public String getClientMobile() {
		return clientMobile;
	}

	public void setClientMobile(String clientMobile) {
		this.clientMobile = clientMobile;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getClientStatus() {
		return clientStatus;
	}

	public void setClientStatus(String clientStatus) {
		this.clientStatus = clientStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Employee getClientPartner() {
		return clientPartner;
	}

	public void setClientPartner(Employee clientPartner) {
		this.clientPartner = clientPartner;
	}

	public ClientOrganization getClientOrganization() {
		return clientOrganization;
	}

	public void setClientOrganization(ClientOrganization clientOrganization) {
		this.clientOrganization = clientOrganization;
	}

	public ThirdPartyCredentitals getThirdPartyCredentitals() {
		return thirdPartyCredentitals;
	}

	public void setThirdPartyCredentitals(ThirdPartyCredentitals thirdPartyCredentitals) {
		this.thirdPartyCredentitals = thirdPartyCredentitals;
	}

}
