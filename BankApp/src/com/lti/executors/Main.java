package com.lti.executors;

import com.lti.dao.AccountDao;
import com.lti.dao.InMemoryAccountDaoImpl;
import com.lti.entities.Account;
import com.lti.entities.CurrentAccount;
import com.lti.entities.SavingsAccount;

public class Main {

	public static void main(String[] args) {
		
		
		
		Account.setBankName("some bank");
		
		AccountDao accountDao = new InMemoryAccountDaoImpl();
		
		accountDao.addAnAccount(new SavingsAccount(233765,7800,"xyz",5000));
		accountDao.addAnAccount(new SavingsAccount(233789,7800,"xyz",5000));
		
		accountDao.addAnAccount(new CurrentAccount(4567785,42000,"xyz",15000));
		
		System.out.println(accountDao.withdraw(233789, 2000));
		
		System.out.println(accountDao.checkBalance(233789));
		
		accountDao.viewAllAccounts().stream().forEach(a -> System.out.println(a.displayAccount()));
		
		

	}

}
