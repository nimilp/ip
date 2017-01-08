package com.npeetha.expensetracker.resources;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.managers.IAccountManager;

@RestController
public class AccountsResource {

	@Autowired
	IAccountManager manager;
//	ExpenseTrackerHDAO hdao;

	@RequestMapping(value="/accounts",method=RequestMethod.GET)
	public ModelAndView getAccounts() throws JsonProcessingException {
		
		return new ModelAndView("accounts", "accounts", new ObjectMapper().writeValueAsString(manager.getAccounts()));
	}

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
	@RequestMapping(value="/accounts/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON)
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
	public String insertTimes(@RequestBody Account account) {
		try {

			manager.createAccount(account);
			return  "{\"success\":\"true\"}";
		} catch (Exception e) {
			return  "{\"errorStatus\":\""+Status.INTERNAL_SERVER_ERROR+"\"}";
		}
	}
//
//	@DELETE
	@RequestMapping(value="/accounts/{id}",method=RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable String id) {
		
		manager.deleteAccount(id);
	}
//
//	@PUT
	@RequestMapping(value="/accounts", method=RequestMethod.PUT)
	public String update(@RequestBody Account account) {
		
		try {
			manager.updateAccount(account);
			return "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"errorStatus\":\""+Status.INTERNAL_SERVER_ERROR+"\"}";
		}
		
	}
	
	@RequestMapping(value="/generate")
	public String generate(){
		for(int i =0;i<50;i++){
			Account account = new Account();
			account.setBudget(new Random().nextDouble());
			account.setDescription(RandomStringUtils.randomAlphabetic(20));
			account.setName("account"+i);
			String s =manager.createAccount(account);
			System.out.print("at "+i+", with "+s);
		}
		return "";
	}
}
