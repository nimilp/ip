package com.npeetha.expensetracker.resources;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
//	public Account getAccount(@PathParam(value = "id") String id) {
//		
//		return manager.getAccount(id);
//	}
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	// @Produces(MediaType.APPLICATION_JSON)
//	public Response insertTimes(Account account) {
//		Response response = null;
//		try {
//
//			manager.createAccount(account);
//			response = Response.status(Status.CREATED).entity("{\"success\":\"true\"}").build();
//		} catch (Exception e) {
//			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
//		}
//		return response;
//	}
//
//	@DELETE
//	@Path("/{id}")
//	public void delete(@PathParam(value = "id") String id) {
//		
//		manager.deleteAccount(id);
//	}
//
//	@PUT
//	public Response update(Account account) {
//		Response response = null;
//		try {
//			manager.updateAccount(account);
//			response = Response.status(Status.OK).entity("{\"success\":\"true\"}").build();
//		} catch (Exception e) {
//			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
//		}
//		return response;
//	}
}
