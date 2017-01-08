package com.npeetha.expensetracker.dao;

import java.util.List;

import com.npeetha.expensetracker.entity.ExpenseEntity;

public interface IExpenseDAO {

	String save(ExpenseEntity expense);
	List<ExpenseEntity> list();
	ExpenseEntity get(String id);
	String update(ExpenseEntity expense);
	String delete(String id);
	
}
