/*
 * http://www.dineshonjava.com/2013/08/struts-2-hibernate-3-integration.html
 * 
 */

package com.dineshonjava.struts2.utils;

import java.util.ArrayList;
import java.util.List;

import com.dineshonjava.struts2.bean.UserBean;
import com.dineshonjava.struts2.model.User;

/**
 * @author Dinesh Rajput
 *
 */
public class CommonUtility {
	public static User createModel(UserBean userBean){
		User user = new User();
		user.setUserName(userBean.getUserName());
		user.setUserAge(userBean.getUserAge());
		user.setUserGender(userBean.getUserGender());
		user.setUserJobType(userBean.getUserJob());
		user.setUserHobbies(convertArrayToCsv(userBean.getUserHobbies()));
		return user;
	}
	
	public static List<UserBean> createUserBeanList(List<User> users){
		List<UserBean> beans = new ArrayList<UserBean>();
		UserBean userBean = null;
		for(User user : users){
			userBean = new UserBean();
			userBean.setUserName(user.getUserName());
			userBean.setUserAge(user.getUserAge());
			userBean.setUserGender(user.getUserGender());
			userBean.setUserJob(user.getUserJobType());
			userBean.setUserHobbies(convertCsvToArr(user.getUserHobbies()));
			beans.add(userBean);
		}
		return beans;
		
	}
	public static String convertArrayToCsv(String [] arr){
		String csv = "";
		for(String value : arr){
			csv += value+",";
		}
		return csv;
	}
	public static String[] convertCsvToArr(String csv){
		String [] values = csv.split(",");
		return values;
	}
}
