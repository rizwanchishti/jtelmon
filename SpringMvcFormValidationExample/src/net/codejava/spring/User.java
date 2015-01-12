/*
 * http://www.codejava.net/frameworks/spring/spring-mvc-form-validation-example-with-bean-validation-api
 * http://localhost:8080/SpringMvcFormValidationExample/login
 * 
 * http://springjavatutorial.blogspot.ro/2013/03/spring-3-mvc-form-validation.html
 * 
 */

package net.codejava.spring;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author www.codejava.net
 *
 */
public class User {
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty(message = "Please enter your password.")
	@Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}