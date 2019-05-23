package com.demo.restful.restfulservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	UserDaoService service;
	

	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> retreviveAllUsers() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/users/{id}")
	public Resource<User> retreviveOneUser(@PathVariable Integer id) {
		User user = service.findOne(id);

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(this.getClass(), this.retreviveAllUsers());
		
		resource.add(linkTo.withRel("all users"));

		if (user == null) {
			throw new UserNotFoundException("Id:" + id);
		}

		return resource;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("Id:" + id);
		}
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User body) {
		// users/4
		User savedUser = service.save(body);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

}
