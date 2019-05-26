package com.demo.restful.restfulservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

	@Autowired
	UserDaoService service;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	PostRepository prepo;
	

	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users")
	public List<User> retreviveAllUsers() {
		return urepo.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{id}")
	public Resource<User> retreviveOneUser(@PathVariable Integer id) {
		Optional<User> user = urepo.findById(id);

		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(this.getClass(), this.retreviveAllUsers());
		
		resource.add(linkTo.withRel("all users"));

		if (!user.isPresent()) {
			throw new UserNotFoundException("Id:" + id);
		}

		return resource;
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		urepo.deleteById(id);
		/*
		 * if (user == null) { throw new UserNotFoundException("Id:" + id); }
		 */
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User body) {
		// users/4
		User savedUser = urepo.save(body);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id ){
		Optional<User> posts = urepo.findById(id);
		if (!posts.isPresent()) {
			throw new UserNotFoundException("Id:" + id);
		}
		return posts.get().getPosts();
		
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,  @RequestBody Post post) {
		// users/4
		
		Optional<User> optuser = urepo.findById(id);
		if (!optuser.isPresent()) {
			throw new UserNotFoundException("Id:" + id);
		}
		
		User user = optuser.get();
		
		post.setUser(user);
		
		Post savedPost = prepo.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
