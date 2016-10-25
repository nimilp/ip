package com.npeetha.expensetracker.managers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.dao.IAccountDAO;
import com.npeetha.expensetracker.entity.AccountEntity;

@Service
public class AccountManager implements IAccountManager {

	@Autowired
	public IAccountDAO accountDao;
	
	@Override
	@Transactional
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		List<AccountEntity> entityList = accountDao.list();
		List<Account> accounts = new ArrayList<Account>(entityList.size());
		for(AccountEntity e : entityList){
			accounts.add(e.transfer());
		}
		return accounts;
	}

	@Override
	@Transactional
	public String createAccount(Account account) {
		// TODO Auto-generated method stub
		AccountEntity e = new AccountEntity();
		e.copy(account);
		return accountDao.save(e);
		
	}

	@Override
	@Transactional
	public Account getAccount(String id) {
		// TODO Auto-generated method stub
		return accountDao.get(id).transfer();
	}

	@Override
	@Transactional
	public String updateAccount(Account account) {
		// TODO Auto-generated method stub
		AccountEntity e = new AccountEntity();
		e.copy(account);
		return accountDao.update(e);
	}

	@Override
	@Transactional
	public String deleteAccount(String id) {
		// TODO Auto-generated method stub
		return accountDao.delete(id);
	}
	
	

}
