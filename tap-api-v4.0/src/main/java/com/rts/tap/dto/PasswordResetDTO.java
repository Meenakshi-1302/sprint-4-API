package com.rts.tap.dto;

import java.time.LocalDateTime;

public class PasswordResetDTO {

	private Long id; // will be changed from Long to String once Alphanumeric is set
	private String email;
	private String name;
	private String password;
	private String otp;
	private LocalDateTime expirationTime;

	public PasswordResetDTO() {
		super();
	}

	@Override
	public String toString() {
		return "PasswordResetDTO [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password
				+ ", otp=" + otp + ", expirationTime=" + expirationTime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}
