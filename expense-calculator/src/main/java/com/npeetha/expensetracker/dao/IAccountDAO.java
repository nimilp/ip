package com.npeetha.expensetracker.dao;

import java.util.List;

import com.npeetha.expensetracker.entity.AccountEntity;

public interface IAccountDAO {

	void save(AccountEntity account);
	List<AccountEntity> list();
	AccountEntity get(String id);
	String update(AccountEntity account);
	String delete(String id);
	
}
