package com.npeetha.mytask.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.npeetha.mytask.resonse.IpResponse;

import io.swagger.annotations.ApiOperation;

@Path("whatismyip")
public class MyIp {

	private static IpResponse response = new IpResponse();
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@ApiOperation(value="get my current ip stored", notes="This api is used to get the current external ip sent from my machine",
			response=IpResponse.class)
	@GET @Produces(MediaType.APPLICATION_JSON)
	public Response getMyIp(){
		return Response.ok(response).build();
	}
	
	@ApiOperation(value="stores the ip of the client", notes="If you access this url, then your ip is stored")
	@POST @Produces(MediaType.APPLICATION_JSON)
	public Response setMyIp(@Context HttpServletRequest request){
		
		if(response.getIpAddress()==null || !response.getIpAddress().equals(request.getRemoteAddr())){
			response.setIpChanged(format.format(new Date()));
		}
		response.setIpAddress(request.getRemoteAddr());
		response.setLastUpdate(format.format(new Date()));
		return Response.ok().build();
	}
	
	@DELETE
	public Response delete(){
		response = new IpResponse();
		return Response.ok().build();
	}
}
