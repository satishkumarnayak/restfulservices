package com.demo.restful.restfulservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
//	@RequestMapping(method=RequestMethod.GET, value="hello_world")
	@GetMapping(path="hello_world")
	public String getHelloWorld() {
		return "Hello World!";
	}

}
