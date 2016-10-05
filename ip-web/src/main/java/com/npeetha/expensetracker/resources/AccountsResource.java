package com.npeetha.expensetracker.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.managers.IAccountManager;

@RestController
public class AccountsResource {

	@Autowired
	IAccountManager manager;
//	ExpenseTrackerHDAO hdao;

	@RequestMapping(value="/accounts",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON)
	public List<Account> getAccounts() {
		
		return manager.getAccounts();
	}

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
	@RequestMapping(value="/accounts/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON)
	public Account getAccount(@PathVariable String id) {
		
		return manager.getAccount(id);
	}
//
	@RequestMapping(value="/accounts", method=RequestMethod.POST)
	public String insertTimes(@RequestBody Account account) {
//		Response response = null;
		try {

			manager.createAccount(account);
			return  "{\"success\":\"true\"}";
		} catch (Exception e) {
			return  "{\"errorStatus\":\""+Status.INTERNAL_SERVER_ERROR+"\"}";
		}
		//return response;
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
}
