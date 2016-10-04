package com.npeetha.expensetracker.dao;

import java.util.List;

import com.npeetha.expensetracker.bo.Account;

public interface IAccountDAO {

	String save(Account account);
	List<Account> list();
	Account get(String id);
	String update(Account account);
	String delete(String id);
	
}
