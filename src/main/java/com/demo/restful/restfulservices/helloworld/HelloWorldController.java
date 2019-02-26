package com.demo.restful.restfulservices.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class HelloWorldController {
	
	@RequestMapping(method=RequestMethod.GET, value="/hello_world")
//	@ResponseBody
	@GetMapping(path="/hello_world")
	public String getHelloWorld() {
		return "Hello World!";
	}

	@RequestMapping(method=RequestMethod.GET, value="/hello_world_bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean !");
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello_world/pathvar/{name}")
	public HelloWorldBean getHelloWorldPathVar(@PathVariable String name) {
		return new HelloWorldBean("Hello World Bean !"+String.format("%s", name));
	}
}
