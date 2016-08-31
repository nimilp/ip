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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.types.ObjectId;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.hdao.ExpenseTrackerHDAO;

@Path("/accounts")
public class AccountsResource {

	ExpenseTrackerHDAO hdao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccounts() {
		if (hdao == null) {
			hdao = new ExpenseTrackerHDAO();
		}
		return hdao.getAccounts();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Account getAccount(@PathParam(value = "id") String id) {
		if (hdao == null) {
			hdao = new ExpenseTrackerHDAO();
		}
		return hdao.getAccount(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Response insertTimes(Account account) {
		Response response = null;
		try {
			if (hdao == null) {
				hdao = new ExpenseTrackerHDAO();
			}

			hdao.insertAccounts(account);
			response = Response.status(Status.CREATED).entity("{\"success\":\"true\"}").build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam(value = "id") String id) {
		if (hdao == null) {
			hdao = new ExpenseTrackerHDAO();
		}

		hdao.deleteAccount(id);
	}

	@PUT
	public Response update(Account account) {
		Response response = null;
		try {
			if (hdao == null) {
				hdao = new ExpenseTrackerHDAO();
			}

			hdao.updateAccount(account);
			response = Response.status(Status.OK).entity("{\"success\":\"true\"}").build();
		} catch (Exception e) {
			response = Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return response;
	}
}
