package com.npeetha.expensetracker.dao;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.npeetha.common.dao.CoreDAO;
import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.entity.AccountEntity;

/**
 * @author nimilpeethambaran
 *
 */
@Service
public class AccountDAO extends CoreDAO implements IAccountDAO {
	private static Logger log = Logger.getLogger(AccountDAO.class.getName());
	
//	@Autowired
//	private SessionFactory sessionFactory;
//
//	private Session getSession(){
//		Session session = sessionFactory.getCurrentSession();
//		if(session == null){
//			session = sessionFactory.openSession();
//		}
//		return session;
//	}
//	@Override
	public void save(AccountEntity account) {
		if(account.getId() == null){
			account.setId(UUID.randomUUID().toString());
		}
		Session session = getSession();
		session.saveOrUpdate(account);	
//		return save.toString();
	}

//	@Override
	
	public List<AccountEntity> list() {
		Session session = getSession();
		List<AccountEntity> accountList = session.createQuery("from AccountEntity").list();
		session.flush();
		return accountList;
	}

//	@Override
	public AccountEntity get(String id) {
		AccountEntity account = new AccountEntity();
		account.setId(id);
		Session session = getSession();
		return (AccountEntity) session.createCriteria(AccountEntity.class).add(Example.create(account)).uniqueResult();
	}

//	@Override
	public String update(AccountEntity account) {
		Session session = getSession();
		session.update(account);
		session.flush();
		return account.getId();
	}

//	@Override
	public void deleteAccounts(String[] id) {
		
		Session session = getSession();
		Query deleteQuery = session.createQuery("delete from AccountEntity where id in (:id)");
		deleteQuery.setParameterList("id", id);
		deleteQuery.executeUpdate();
		
	}

	
}
