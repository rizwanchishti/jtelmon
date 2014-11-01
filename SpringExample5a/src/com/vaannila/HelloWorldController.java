/*
 * http://www.dzone.com/tutorials/java/spring/spring-mvc-tutorial-1.html 
 * http://www.dzone.com/sites/all/files/SpringExample5.zip
 * 
 */

package com.vaannila;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HelloWorldController extends AbstractController {

	private String message;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("welcomePage","welcomeMessage", message);
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
