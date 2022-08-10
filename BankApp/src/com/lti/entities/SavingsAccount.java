package com.lti.entities;

public class SavingsAccount extends Account {
	
	private double minimumBalance;

	public SavingsAccount(int accountNumber, double accountBalance, String accountPassword,double minimumBalance) {
		super(accountNumber,accountBalance,accountPassword);
		this.minimumBalance = minimumBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public String displayAccount() {
		
		return super.displayAccount() + ", minimumBalance = "+this.minimumBalance;
	}

	@Override
	public double withdraw(double amount) {
		
		this.setAccountBalance(this.getAccountBalance() - amount);
		
		return this.getAccountBalance();
	}
	
	
	

}
