package com.npeetha.ipfetch.resources;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.npeetha.mytask.resonse.IpResponse;

//@Path("whatismyip")
@RestController
//@Api(value = "whatismyip", produces = "application/json")
public class MyIp {

	private static IpResponse response = new IpResponse();

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

//	@ApiOperation(value = "get my current ip stored", notes = "This api is used to get the current external ip sent from my machine")
//	@ApiResponses(value = { @ApiResponse(code = 200, response = IpResponse.class, message = "Success") })
	
	@RequestMapping(value="/whatismyip",method=RequestMethod.GET)
	public ModelAndView getMyIp() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ModelAndView maV = new ModelAndView("whatismyip", "whatismyip", mapper.writeValueAsString(response));
		return maV;
	}

//	@ApiOperation(value = "stores the ip of the client", notes = "If you access this url, then your ip is stored")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESSFUL") })
	@RequestMapping(value="whatismyip",method=RequestMethod.POST)
	public String setMyIp(@Context HttpServletRequest request) {

		if (response.getIpAddress() == null || !response.getIpAddress().equals(request.getRemoteAddr())) {
			response.setIpChanged(format.format(new Date()));
		}
		response.setIpAddress(request.getRemoteAddr());
		response.setLastUpdate(format.format(new Date()));
		return "SUCCESSFUL";
	}

//	@ApiOperation(value = "clear the ip of the client", notes = "If you access this url, then every ip stored are deleted")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESSFUL") })
	@RequestMapping(value="whatismyip",method=RequestMethod.DELETE)
	public String delete() {
		response = new IpResponse();
		return "SUCCESSFUL";
	}
}
