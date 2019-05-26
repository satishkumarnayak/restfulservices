package com.demo.restful.restfulservices.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@RequestMapping(method = RequestMethod.GET, value = "/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("evyaan");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2("evyaan", "nayak");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/person/param", params = "version=1")
	public PersonV1 getParam1() {
		return new PersonV1("evyaan");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/person/param", params = "version=2")
	public PersonV2 getParam2() {
		return new PersonV2("evyaan", "nayak");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/person/header", headers = "VERSION=1")
	public PersonV1 getHeader1() {
		return new PersonV1("evyaan");

	}

	@RequestMapping(method = RequestMethod.GET, value = "/person/header", headers = "VERSION=2")
	public PersonV2 getHeader2() {
		return new PersonV2("evyaan", "nayak");

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getProduces1() {
		return new PersonV1("evyaan");

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getProduces2() {
		return new PersonV2("evyaan", "nayak");

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
