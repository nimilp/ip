package com.npeetha.security.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/")
public class LoginResource {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getExpenses() throws JsonProcessingException {
		
		return new ModelAndView("login", "login",null);
	};
}
