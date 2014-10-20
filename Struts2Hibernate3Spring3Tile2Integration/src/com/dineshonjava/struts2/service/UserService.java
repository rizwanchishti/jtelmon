/*
 * http://www.dineshonjava.com/2013/08/struts-2-hibernate-3-integration.html
 * 
 */

package com.dineshonjava.struts2.service;

import java.util.List;

import com.dineshonjava.struts2.model.User;

/**
 * @author Dinesh Rajput
 *
 */
public interface UserService {
	void saveUser(User user);
	
	List<User> getUserList(); 
}
