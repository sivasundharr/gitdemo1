package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.entities.Account;
import com.lti.entities.CurrentAccount;
import com.lti.entities.SavingsAccount;
import com.lti.exceptions.InsufficientFundException;

public class AccountDaoImpl implements AccountDao {
	
	private static final String LIST_OF_ACCOUNTS = 
	"SELECT a.account_number,a.account_balance,a.account_type,s.min_balance,c.draf_limit FROM accounts LEFT JOIN "+
	"savings_account s ON a.account_number == s.account_number LEFT JOIN current_account c"+
	"ON a.account_number == c.account_number order by a.account_number";
	
	private static final String ADD_ACCOUNT = "INSERT INTO accounts(account_number,account_balance,account_password,account_type) values(?,?,?,?)" ;
	
	private static final String GET_ACCOUNT = 
	"SELECT a.account_number,a.account_balance,a.account_type,s.min_balance,c.draf_limit FROM accounts LEFT JOIN "+
	"savings_account s ON a.account_number == s.account_number LEFT JOIN current_account c"+
	"ON a.account_number == c.account_number where a.account_number = ?";
	
	private static final String CHECK_ACCOUNT_BALANCE = "SELECT account_balance from accounts WHERE account_number = ?";

	
	private static final String CHANGE_ACCOUNT_PASSWORD = "UPDATE SET account_password = ? FROM accounts WHERE account_number = ?";
	
	private static final String INSERT_MINIMUM_BALANCE = "INSERT INTO savings_account(account_number,min_balance) VALUES(?)";
	
	private static final String INSERT_OVER_DRAFT_LIMIT_AMOUNT = "INSERT INTO current_account(account_number,draft_limit) VALUES(?)";
	

	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","siva");
	}

	@Override
	public void addAnAccount(Account account) {
		
		try(var conn = getConnection();){
			try(var prepareStmt = conn.prepareStatement(ADD_ACCOUNT);){
				prepareStmt.setInt(1,account.getAccountNumber());
				prepareStmt.setDouble(2,account.getAccountBalance());
				prepareStmt.setString(3, account.getAccountPassword());
				
				if(account instanceof SavingsAccount) {
					prepareStmt.setInt(4, 1);
					
				}else {
					prepareStmt.setInt(4,2);
				}
				
				prepareStmt.executeUpdate();
				if(account instanceof SavingsAccount) {
				try(var ps = conn.prepareStatement(INSERT_MINIMUM_BALANCE);){
					ps.setInt(1, account.getAccountNumber());
					ps.setDouble(2, ((SavingsAccount) account).getMinimumBalance());
					
				}
				}
				else {
				try(var ps = conn.prepareStatement(INSERT_OVER_DRAFT_LIMIT_AMOUNT);){
					ps.setInt(1, account.getAccountNumber());
					ps.setDouble(2, ((CurrentAccount) account).getOverdraftLimitAmount());
					ps.executeUpdate();
				}
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public double withdraw(int accountNumber, double amount) {
		
		double balance = checkBalance(accountNumber);
		
		if(balance<amount)
			try {
				throw new InsufficientFundException();
			} catch (InsufficientFundException e1) {
				System.out.println(e1.getMessage());
			}
		
		try(var conn = getConnection();){
			try(var ps = conn.prepareStatement("UPDATE accounts SET account_balance = ? WHERE account_number = ?");){
				double remain = balance - amount;
				ps.setDouble(1,remain);
				ps.setInt(2, accountNumber);
				
				ps.executeUpdate();
				
				return remain;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public double checkBalance(int accountNumber) {
		try(var conn = getConnection();){
			try(var prepareStmt = conn.prepareStatement(CHECK_ACCOUNT_BALANCE);){
				prepareStmt.setInt(1,accountNumber);
				try(var rs = prepareStmt.executeQuery();){
					while(rs.next()) {
						return rs.getDouble(1);
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void changePassword(int accountNumber, String oldPassword, String newPassword) {
		
		try(var conn = getConnection();){
			try(var prepareStmt = conn.prepareStatement(CHANGE_ACCOUNT_PASSWORD);){
				prepareStmt.setString(1, newPassword);
				prepareStmt.setInt(2,accountNumber );
				
				prepareStmt.executeUpdate();
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}

	}

	@Override
	public List<Account> viewAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		
		try(var conn = getConnection();){
			try(var stmt = conn.createStatement();){
				try( var rs = stmt.executeQuery(LIST_OF_ACCOUNTS);){
					while(rs.next()) {
						if(rs.getInt("account_type") == 1)
							accounts.add(new SavingsAccount(rs.getInt(1),rs.getDouble(2),null,rs.getDouble("min_balance")));
						else
							accounts.add(new CurrentAccount(rs.getInt(1),rs.getDouble(2),null,rs.getDouble("draft_limit")));
					}
					
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public Account getAccountDetails(int accountNumber) {
		
		Account account = null;
		
		try(var conn = getConnection();){
			try(var prepareStmt = conn.prepareStatement(GET_ACCOUNT);){
				prepareStmt.setInt(1, accountNumber);
				try(var rs = prepareStmt.executeQuery()){
					while(rs.next()) {
						if(rs.getInt("account_type") == 1) 
								return new SavingsAccount(rs.getInt(1),rs.getDouble(2),null,rs.getDouble("min_balance"));
							else
								return new CurrentAccount(rs.getInt(1),rs.getDouble(2),null,rs.getDouble("draft_limit"));
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

}
