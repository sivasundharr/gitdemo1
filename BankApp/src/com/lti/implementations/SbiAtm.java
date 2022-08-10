package com.lti.implementations;

import com.lti.dao.AccountDao;
import com.lti.dao.InMemoryAccountDaoImpl;

public class SbiAtm implements ATM {
	
	private AccountDao accountDao;
	
	SbiAtm(AccountDao accountDao){
		this.accountDao = new InMemoryAccountDaoImpl() ;
	}

	@Override
	public double withdraw(int accountNumber, double amount) {
		
		return accountDao.withdraw(accountNumber, amount);
	}

	@Override
	public void changePassword(int accountNumber, String oldPassword, String newPassword) {
		
		accountDao.changePassword(accountNumber, oldPassword, newPassword);

	}

	@Override
	public double checkBalance(int accountNumber) {
		
		return accountDao.checkBalance(accountNumber);
	}

}
