package com.lti.entities;

public class CurrentAccount extends Account{
	
	private double overdraftLimitAmount;
	
	public CurrentAccount(int accountNumber, double accountBalance, String accountPassword,double overdraftLimitAmount) {
		
		super(accountNumber,accountBalance,accountPassword);
		
		this.overdraftLimitAmount = overdraftLimitAmount;
		
	}

	public double getOverdraftLimitAmount() {
		return overdraftLimitAmount;
	}

	public void setOverdraftLimitAmount(int overdraftLimitAmount) {
		this.overdraftLimitAmount = overdraftLimitAmount;
	}

	@Override
	public String displayAccount() {
		
		return super.displayAccount()+", overdraftLimitAmount = "+overdraftLimitAmount;
		
	}

	@Override
	public double withdraw(double amount) {
		this.setAccountBalance(this.getAccountBalance() - amount);
		return this.getAccountBalance();
	}
	
	

}
