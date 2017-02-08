package com.npeetha.expensetracker.resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npeetha.expensetracker.bo.Expense;
import com.npeetha.expensetracker.managers.IExpenseManager;

@RestController
@RequestMapping(value="/expensetracker")
public class ExpenseResource {

	@Autowired
	IExpenseManager manager;
	
	@RequestMapping(value="/expense.exp",method=RequestMethod.GET)
	public ModelAndView getExpenses() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return new ModelAndView("expenses", "expenses", mapper.writeValueAsString(manager.list()));
	};
	
	@ResponseBody
	@RequestMapping(value="/listexpenses.exp",method=RequestMethod.GET)
	public List<Expense> getExpenseList() throws JsonProcessingException {
		
		return manager.list();
	};

	
//	@RequestMapping(value="generate")
//	public String generate(){
//		String[] ids = {"072932f6-7cb3-4603-acc9-3debacd118f5","a168d88b-39ff-4654-83fb-c056cfb9c548","d42de180-0c98-43ff-ab42-e25f78f704d7","8a456c17-4aef-4390-aaac-0cfc1510c5f5"};
//		for(int i =0;i<10;i++){
//			Expense account = new Expense();
//			account.setAmount(new Random().nextDouble());
//			account.setVenue(RandomStringUtils.randomAlphabetic(20));
//			account.setItem("item"+i);
//			account.setPaidOn(new Date());
//			account.setAccountId(ids[new Random().nextInt(3)]);
//			String s =manager.create(account);
//			System.out.print("at "+i+", with "+s);
//		}
//		return "";
//	}
	
	@ResponseBody
	@RequestMapping(value="/expense.exp",method = RequestMethod.POST)
	public String updateExpense(@RequestBody Expense expense){
		String returnString = "";
		try{
		manager.create(expense);
		returnString = "{\"success\":\"true\",\"id\":\""+expense.getId()+"\"}";
		}catch(Exception e){
			e.printStackTrace();
			returnString = "{\"error\":\"true\",\"message\":\""+e.getMessage()+"\"}";
		}
		return returnString;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete.exp", method = RequestMethod.POST)
	public String deleteExpense( @RequestParam(value="id[]") String[] id){
		String returnString = "{\"success\":\"true\"}";
		try{
			manager.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			returnString = "{\"error\":\"true\",\"message\":\""+e.getMessage()+"\"}";
		}
		return returnString;
	}
}
