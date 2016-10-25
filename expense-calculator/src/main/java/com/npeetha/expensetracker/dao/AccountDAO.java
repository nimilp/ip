package com.npeetha.expensetracker.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.entity.AccountEntity;

/**
 * @author nimilpeethambaran
 *
 */
@Service
public class AccountDAO implements IAccountDAO {
	private static Logger log = Logger.getLogger(AccountDAO.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;

//	@Override
	public String save(AccountEntity account) {
		account.setId(UUID.randomUUID().toString());
		Session session = sessionFactory.openSession();
		Serializable save = session.save(account);	
		return save.toString();
	}

//	@Override
	
	public List<AccountEntity> list() {
		Session session = sessionFactory.openSession();
		List<AccountEntity> accountList = session.createQuery("from AccountEntity").list();
		session.flush();
		return accountList;
	}

//	@Override
	public AccountEntity get(String id) {
		Account account = new Account();
		account.setId(id);
		Session session = sessionFactory.openSession();
		return (AccountEntity) session.createCriteria(Account.class).add(Example.create(account)).uniqueResult();
	}

//	@Override
	public String update(AccountEntity account) {
		Session session = sessionFactory.openSession();
		session.update(account);
		session.flush();
		return account.getId();
	}

//	@Override
	public String delete(String id) {
		
		Session session = sessionFactory.openSession();
		AccountEntity account = get(id);
		session.delete(account);
		session.flush();
		return id;
	}

	
}
