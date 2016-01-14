package com.npeetha.ip.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("tasks")
public class TaskResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasks(){
		return  Response.ok("me").build();
	}
}
