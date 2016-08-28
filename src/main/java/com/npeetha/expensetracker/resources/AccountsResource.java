package com.npeetha.expensetracker.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.hdao.ExpenseTrackerHDAO;

@Path("/accounts")
public class AccountsResource {

	ExpenseTrackerHDAO hdao;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccounts(){
		if(hdao==null){
			hdao = new ExpenseTrackerHDAO();
		}
		return hdao.getAccounts();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Account getAccount(@PathParam(value = "id") String id){
		if(hdao==null){
			hdao = new ExpenseTrackerHDAO();
		}
		return hdao.getAccount(id);
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
	
	@DELETE
	public void delete(String id){
		if(hdao==null){
			hdao= new ExpenseTrackerHDAO();
		}
		
		hdao.deleteAccount(id);
	}
	
	@PUT
	public void update(Account account){
		if(hdao==null){
			hdao= new ExpenseTrackerHDAO();
		}
		
		hdao.updateAccount(account);
	}
}
