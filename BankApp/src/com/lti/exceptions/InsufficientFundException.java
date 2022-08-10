package com.lti.exceptions;

public class InsufficientFundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		
		return "Your account have no sufficient fund";
		
	}

}
