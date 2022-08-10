package com.lti.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.lti.entities.Account;
import com.lti.exceptions.InsufficientFundException;
import com.lti.exceptions.InvalidAmountException;

public class InMemoryAccountDaoImpl implements AccountDao {
	
	public static ArrayList<Account> accounts;
	
	static {
		accounts = new ArrayList<>();
	}
	

	@Override
	public void addAnAccount(Account account) {
		
		accounts.add(account);

	}

	@Override
	public double withdraw(int accountNumber, double amount) {
		
		if(amount < 0)
			try {
				throw new InvalidAmountException();
			} catch (InvalidAmountException e1) {
				System.out.println(e1.getMessage());
			}
		
		Optional<Account> filterAccount = accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findFirst();
		if(filterAccount.isPresent()) {
			
			Account a = filterAccount.get();
			
			if(a.getAccountBalance() < amount)
				try {
					throw new InsufficientFundException();
				} catch (InsufficientFundException e) {
					System.out.println(e.getMessage());
				}
			
			a.setAccountBalance(a.getAccountBalance() - amount);
			
			return a.getAccountBalance();
		}
		
		return 0.0;
		
	}

	@Override
	public double checkBalance(int accountNumber) {
		Account filterAccount = accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findFirst().get();
		
		return filterAccount.getAccountBalance();
	}

	@Override
	public void changePassword(int accountNumber, String oldPassword, String newPassword) {
		
		Optional<Account> filterAccount = accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findFirst();
		
		if(filterAccount.isPresent()) {
			
			Account oldAccount =  filterAccount.get();
			
			if(oldAccount.getAccountPassword() == oldPassword) {
				oldAccount.setAccountPassword(newPassword);
			}
			
		}
		

	}

	@Override
	public List<Account> viewAllAccounts() {
		
		return accounts;
	}

	@Override
	public Account getAccountDetails(int accountNumber) {
		return accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findFirst().get();
	}

}
