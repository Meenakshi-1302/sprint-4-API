package com.rts.tap.dto;

public class UpdatePasswordDto {

	private String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public UpdatePasswordDto(String message) {
		super();
		Message = message;
	}

	public UpdatePasswordDto() {
		super();
	}

	
	
	
}
