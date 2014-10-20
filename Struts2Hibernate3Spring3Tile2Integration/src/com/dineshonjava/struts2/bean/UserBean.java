/*
 * http://www.dineshonjava.com/2013/08/struts-2-hibernate-3-integration.html
 * 
 */

package com.dineshonjava.struts2.bean;

/**
 * @author Dinesh Rajput
 *
 */
public class UserBean {
	private String userName;
	private Long userAge;
	private String userGender;
	private String userJob;
	private String []userHobbies;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserAge() {
		return userAge;
	}
	public void setUserAge(Long userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public String[] getUserHobbies() {
		return userHobbies;
	}
	public void setUserHobbies(String[] userHobbies) {
		this.userHobbies = userHobbies;
	}
	
}
