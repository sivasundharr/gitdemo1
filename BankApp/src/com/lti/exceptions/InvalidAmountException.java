package com.lti.exceptions;

public class InvalidAmountException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Negative Amount values are not allowed";
		
	}

}
