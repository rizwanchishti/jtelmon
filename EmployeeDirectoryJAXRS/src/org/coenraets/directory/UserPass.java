package org.coenraets.directory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserPass {

    private int userID;

    private String password;
    
    private String firstName;

    private String lastName;

    private String city;

    private String officePhone;

    private String cellPhone;

    private String email;
    
	private String picture;    

	private String department;

	private UserPass manager;
	
	private int reportCount;
	
	public UserPass() {

    }
	
	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String title) {
		this.password = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public UserPass getManager() {
		return manager;
	}

	public void setManager(UserPass manager) {
		this.manager = manager;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

}
