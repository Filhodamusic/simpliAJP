package com.SBRestApi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
	@RequestMapping(value = "hi", method = RequestMethod.GET)
	public String sayHi() {
		return "ESS AR NO";
	}
	
	@RequestMapping(value = "text", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String sayHi2() {
		return "<h2>ESS AR NO</h2>";
	}
	
	@RequestMapping(value = "html", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String sayHi3() {
		return "<h2>ESS AR NO</h2>";
	}
	
	@RequestMapping(value = "xml", method = RequestMethod.GET, produces = MediaType.TEXT_XML_VALUE)
	public String sayHi4() {
		return "<h2>ESS AR NO</h2>";
	}
}
