package com.SBRestApi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping(value="say", method = RequestMethod.GET)
	public @ResponseBody String sayHello() {
		return "FDX THYMELEAF, NAO TIMELEAF";
		
	}

}
