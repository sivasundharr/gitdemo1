package com.lti.entities;

public abstract class Account {
	
	private int accountNumber;
	
	private double accountBalance;
	
	private String accountPassword;
	
	private static String bankName;
	
	public Account() {
		
	}
	
	public Account(int accountNumber, double accountBalance, String accountPassword) {
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountPassword = accountPassword;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	

	public static String getBankName() {
		return bankName;
	}

	public static void setBankName(String bankName) {
		Account.bankName = bankName;
	}
	
	public abstract double  withdraw(double amount);
	
	public String displayAccount() {
		return "accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", accountPassword="
				+ accountPassword + ", bankName="+ bankName;
	}
	
	

}
