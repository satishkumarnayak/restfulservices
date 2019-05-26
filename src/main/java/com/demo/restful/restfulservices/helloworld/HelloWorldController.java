package com.demo.restful.restfulservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

//@Controller
@RestController
public class HelloWorldController {
	@Autowired
	MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, value = "/hello_world")
//	@ResponseBody
	@GetMapping(path = "/hello_world")
	public String getHelloWorld() {
		return "Hello World!";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello_world_bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean !");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello_world/pathvar/{name}")
	public HelloWorldBean getHelloWorldPathVar(@PathVariable String name) {
		return new HelloWorldBean("Hello World Bean !" + String.format("%s", name));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/hello_worldint")
	public String getHelloWorldInternational(@RequestHeader(name = "Accept-language", required=false) Locale locale) {
		return messageSource.getMessage("goodmorning.msg", null, locale);
	}
	
	@GetMapping(path="/hello_worldintnew")
	public String getHelloWorldInternationalUpdated() {
		return messageSource.getMessage("goodmorning.msg", null, LocaleContextHolder.getLocale());
	}
}
