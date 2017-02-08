package com.npeetha.expensetracker.managers;

import java.util.List;

import com.npeetha.expensetracker.bo.Account;

public interface IAccountManager {

	List<Account> getAccounts();
	void createAccount(Account account);
	Account getAccount(String id);
	String updateAccount(Account account);
	String deleteAccount(String id);
}
