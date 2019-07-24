package com.search.exception;

public class CustomException extends Exception {
	private static final long serialVersionUID = 3337180226336768235L;
	
	private String code = null;
	
	public CustomException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
