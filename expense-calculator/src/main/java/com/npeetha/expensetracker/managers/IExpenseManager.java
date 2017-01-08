package com.npeetha.expensetracker.managers;

import java.util.List;

import com.npeetha.expensetracker.bo.Expense;

public interface IExpenseManager {

	List<Expense> list();
	String create(Expense expense);
	String update();
	String delete();
	Expense get(String id);
}
