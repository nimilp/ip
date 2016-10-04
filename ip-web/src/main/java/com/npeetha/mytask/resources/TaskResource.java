package com.npeetha.mytask.resources;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.npeetha.mytask.resonse.IpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "tasks", produces=MediaType.APPLICATION_JSON)
public class TaskResource {

//	@GET
	@RequestMapping(value="tasks", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the list of tasks stored", notes = "This api will return every valid tasks that are not done")
	@ApiResponses(value = { @ApiResponse(code = 200, response = IpResponse.class, message = "me") })
	public String getTasks() {
		return "me";
	}
}
