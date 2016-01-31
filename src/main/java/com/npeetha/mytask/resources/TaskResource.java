package com.npeetha.mytask.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.npeetha.mytask.resonse.IpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("tasks")
@Api(value = "tasks")
public class TaskResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of tasks stored", notes = "This api will return every valid tasks that are not done", produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, response = IpResponse.class, message = "me") })
	public Response getTasks() {
		return Response.ok("me").build();
	}
}
