package com.npeetha.expensetracker.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npeetha.expensetracker.bo.Expense;
import com.npeetha.expensetracker.dao.IAccountDAO;
import com.npeetha.expensetracker.dao.IExpenseDAO;
import com.npeetha.expensetracker.entity.ExpenseEntity;

@Service
public class ExpenseManager implements IExpenseManager {

	@Autowired
	IExpenseDAO dao;
	
	@Autowired
	IAccountDAO accountDao;
	
	@Override
	@Transactional
	public List<Expense> list() {
		List<ExpenseEntity> list =dao.list();
		List<Expense> expenseList = new ArrayList<Expense>(list.size());
		for(ExpenseEntity entity : list){
			Expense ex = entity.transfer();
			ex.setAccountName(accountDao.get(entity.getAccountId()).getName());
			expenseList.add(ex);
		}
		return expenseList;
	}

	@Override
	@Transactional
	public String create(Expense expense) {
		ExpenseEntity e = new ExpenseEntity();
		e.copy(expense);
		// TODO Auto-generated method stub
		return dao.save(e);
	}

	@Override
	@Transactional
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		
		dao.delete(ids);
	}
	@Override
	@Transactional
	public Expense get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
