package com.npeetha.ip.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/tasks")
public class TaskResource {

	@GET
	public Response getTasks(){
		return  Response.ok("me").build();
	}
}
