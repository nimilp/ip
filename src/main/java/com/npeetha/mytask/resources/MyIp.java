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

@Path("whatismyip")
public class MyIp {

	private static IpResponse response = new IpResponse();
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@GET @Produces(MediaType.APPLICATION_JSON)
	public Response getMyIp(){
		return Response.ok(response).build();
	}
	
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
