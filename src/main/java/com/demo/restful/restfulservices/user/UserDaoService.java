package com.demo.restful.restfulservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	
	public static int userCount = 3;
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Mark", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(userCount++);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		return users.get(id);
	}

}
