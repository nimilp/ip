package com.npeetha.expensetracker.managers;

import java.util.List;

import com.npeetha.expensetracker.bo.Expense;

public interface IExpenseManager {

	List<Expense> list();
	String create(Expense expense);
	String update();
	void delete(String[] ids);
	Expense get(String id);
}
