/*
 * http://www.dineshonjava.com/2013/08/struts-2-hibernate-3-integration.html#.VEVrh3WUdFQ
 * 
 */

package com.dineshonjava.struts2.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dineshonjava.struts2.bean.UserBean;
import com.dineshonjava.struts2.service.UserService;
import com.dineshonjava.struts2.utils.CommonUtility;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Dinesh Rajput
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<UserBean>{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserBean userBean;
	@Autowired
	private UserService userService;
	private List<UserBean> users;

	public String execute()	{
		users = CommonUtility.createUserBeanList(userService.getUserList());
		return "user";
	}
	
	public String addUser(){
		userService.saveUser(CommonUtility.createModel(userBean));
		users = CommonUtility.createUserBeanList(userService.getUserList());
		return "addUser";
	}
	public String listUser(){
		users = CommonUtility.createUserBeanList(userService.getUserList());
		return "users";
	}
	
	@Override
	public UserBean getModel() {
		return userBean;
	}
	public String alia() { 
		return "alia";
	}
	public String madhuri() { 
		return "madhuri"; 
	}
	public String user() { 
		return "user"; 
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}
	
}