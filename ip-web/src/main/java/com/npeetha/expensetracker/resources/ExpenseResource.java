package com.npeetha.expensetracker.resources;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npeetha.expensetracker.bo.Expense;
import com.npeetha.expensetracker.managers.IExpenseManager;

@RestController
@RequestMapping(value="/expenses")
public class ExpenseResource {

	@Autowired
	IExpenseManager manager;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getExpenses() throws JsonProcessingException {
		
		return new ModelAndView("expenses", "expenses", new ObjectMapper().writeValueAsString(manager.list()));
	}
	
	@RequestMapping(value="generate")
	public String generate(){
		for(int i =0;i<10;i++){
			Expense account = new Expense();
			account.setAmount(new Random().nextDouble());
			account.setVenue(RandomStringUtils.randomAlphabetic(20));
			account.setItem("item"+i);
			account.setPaidOn(new Date());
			account.setAccountId("956a347c-0da3-497e-b831-31a83626058a");
			String s =manager.create(account);
			System.out.print("at "+i+", with "+s);
		}
		return "";
	}
}
