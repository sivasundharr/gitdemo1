package com.lti.dao;

import java.util.List;

import com.lti.entities.Account;

public interface AccountDao {
	
	void addAnAccount(Account account);
	
	double withdraw(int accountNumber,double amount);
	
	double checkBalance(int accountNumber);
	
	void changePassword(int accountNumber,String oldPassword,String newPassword);
	
	List<Account> viewAllAccounts();
	
	Account getAccountDetails(int accountNumber);
}
