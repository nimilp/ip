package com.npeetha.expensetracker.resources;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.managers.IAccountManager;

@RestController
@RequestMapping(value="/expensetracker")
public class AccountsResource {

	@Autowired
	IAccountManager manager;

	@RequestMapping(value="/accounts",method=RequestMethod.GET)
	public ModelAndView getAccounts() throws JsonProcessingException {
		
		return new ModelAndView("accounts", "accounts", new ObjectMapper().writeValueAsString(manager.getAccounts()));
	}
	
	@ResponseBody
	@RequestMapping(value="/accounts/list", method=RequestMethod.GET)
	public List<Account> listAccounts(){
		return manager.getAccounts();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView getAccount(@PathVariable String id) throws JsonProcessingException {
		
		String s = new ObjectMapper().writeValueAsString(manager.getAccounts());
		System.out.println(s);
		try {
			new ObjectMapper().writeValue(System.out, manager.getAccounts());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("accounts", "accounts", new ObjectMapper().writeValueAsString(manager.getAccounts()));
	}

	@RequestMapping(value="/accounts", method=RequestMethod.POST)
	public String createOrUpdatAccount(@RequestBody Account account) {
		try {

			manager.createAccount(account);
			return  "{\"success\":\"true\",\"id\":\""+account.getId()+"\"}";
		} catch (Exception e) {
			return  "{\"error\":\"true\",\"message\":\""+e.getMessage()+"\"}";
		}
	}

	@ResponseBody
	@RequestMapping(value="/deleteaccounts", method = RequestMethod.POST)
	public String deleteExpense( @RequestParam(value="id[]") String[] id){
		String returnString = "{\"success\":\"true\"}";
		try{
			manager.deleteAccounts(id);
		}catch(Exception e){
			e.printStackTrace();
			returnString = "{\"error\":\"true\",\"message\":\""+e.getMessage()+"\"}";
		}
		return returnString;
	}

//	@RequestMapping( method=RequestMethod.PUT)
//	public String update(@RequestBody Account account) {
//		
//		try {
//			manager.updateAccount(account);
//			return "{\"success\":\"true\"}";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "{\"errorStatus\":\""+Status.INTERNAL_SERVER_ERROR+"\"}";
//		}
//		
//	}
	
//	@RequestMapping(value="/generate")
//	public String generate(){
//		for(int i =0;i<4;i++){
//			Account account = new Account();
//			account.setBudget(new Random().nextDouble());
//			account.setDescription(RandomStringUtils.randomAlphabetic(20));
//			account.setName("account"+i);
//			String s =manager.createAccount(account);
//			System.out.print("at "+i+", with "+s);
//		}
//		return "";
//	}
}
