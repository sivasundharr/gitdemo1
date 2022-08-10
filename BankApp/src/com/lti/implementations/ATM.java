package com.lti.implementations;

public interface ATM {
	
	double withdraw(int accountNumber,double amount);
	
	void changePassword(int accountNumber,String oldPassword,String newPassword);
	
	double checkBalance(int accountNumber);

}
