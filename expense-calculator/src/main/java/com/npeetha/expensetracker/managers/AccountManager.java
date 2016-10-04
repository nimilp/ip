package com.npeetha.expensetracker.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.dao.IAccountDAO;

@Service
public class AccountManager implements IAccountManager {

	@Autowired
	public IAccountDAO accountDao;
	
	@Override
	@Transactional
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accountDao.list();
	}

	@Override
	@Transactional
	public String createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.save(account);
		
	}

	@Override
	@Transactional
	public Account getAccount(String id) {
		// TODO Auto-generated method stub
		return accountDao.get(id);
	}

	@Override
	@Transactional
	public String updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.update(account);
	}

	@Override
	@Transactional
	public String deleteAccount(String id) {
		// TODO Auto-generated method stub
		return accountDao.delete(id);
	}
	
	

}
