package com.npeetha.expensetracker.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.hdao.ExpenseTrackerHDAO;
import com.npeetha.timetracker.bo.Time;

@Path("/accounts")
public class AccountsResource {

	ExpenseTrackerHDAO hdao;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getTimes(){
		if(hdao==null){
			hdao = new ExpenseTrackerHDAO();
		}
		return hdao.getAccounts();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public void insertTimes(Account account){
		if(hdao==null){
			hdao= new ExpenseTrackerHDAO();
		}
		hdao.insertAccounts(account);
	}
}
