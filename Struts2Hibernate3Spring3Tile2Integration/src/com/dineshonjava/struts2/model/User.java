/*
 * http://www.dineshonjava.com/2013/08/struts-2-hibernate-3-integration.html
 * 
 */

package com.dineshonjava.struts2.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dinesh Rajput
 *
 */
@Entity
@Table(name="User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "id")
	private Long userId;
	@Column(name="username")
	private String userName;
	@Column(name="age")
	private Long userAge;
	@Column(name="gender")
	private String userGender;
	@Column(name="jobtype")
	private String userJobType;
	@Column(name="Hobbies")
	private String userHobbies;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getUserJobType() {
		return userJobType;
	}
	public void setUserJobType(String userJobType) {
		this.userJobType = userJobType;
	}
	public String getUserHobbies() {
		return userHobbies;
	}
	public void setUserHobbies(String userHobbies) {
		this.userHobbies = userHobbies;
	}
	
}
