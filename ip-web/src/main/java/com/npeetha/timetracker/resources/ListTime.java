package com.npeetha.timetracker.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.npeetha.timetracker.bo.Time;
import com.npeetha.timetracker.hdao.TimeTrackerHDAO;

@Path("/times")
public class ListTime {

//	TimeTrackerHDAO hdao;
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Time> getTimes(){
//		if(hdao==null){
//			hdao = new TimeTrackerHDAO();
//		}
//		return hdao.getTimesheets();
//	}
//	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	//@Produces(MediaType.APPLICATION_JSON)
//	public void insertTimes(Time time){
//		if(hdao==null){
//			hdao= new TimeTrackerHDAO();
//		}
//		hdao.insertTimeSheet(time);
//	}
}
