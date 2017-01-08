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
import com.npeetha.expensetracker.entity.ExpenseEntity;

/**
 * @author nimilpeethambaran
 *
 */
@Service
public class ExpenseDAO implements IExpenseDAO {
	private static Logger log = Logger.getLogger(ExpenseDAO.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;

//	@Override
	public String save(ExpenseEntity expense) {
		expense.setId(UUID.randomUUID().toString());
		Session session = sessionFactory.openSession();
		Serializable save = session.save(expense);	
		return save.toString();
	}

//	@Override
	
	public List<ExpenseEntity> list() {
		Session session = sessionFactory.openSession();
		List<ExpenseEntity> expenseList = session.createQuery("from ExpenseEntity").list();
		session.flush();
		return expenseList;
	}

//	@Override
	public ExpenseEntity get(String id) {
		ExpenseEntity expense = new ExpenseEntity();
		expense.setId(id);
		Session session = sessionFactory.openSession();
		return (ExpenseEntity) session.createCriteria(ExpenseEntity.class).add(Example.create(expense)).uniqueResult();
	}

//	@Override
	public String update(ExpenseEntity expense) {
		Session session = sessionFactory.openSession();
		session.update(expense);
		session.flush();
		return expense.getId();
	}

//	@Override
	public String delete(String id) {
		
		Session session = sessionFactory.openSession();
		ExpenseEntity expense = get(id);
		session.delete(expense);
		session.flush();
		return id;
	}

	
}
