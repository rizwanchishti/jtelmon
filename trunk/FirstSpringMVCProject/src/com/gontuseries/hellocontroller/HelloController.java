/*
 * http://localhost:8080/FirstSpringMVCProject/welcome.html
 * https://www.youtube.com/watch?v=bnB4x-oAd_M
 * http://www.gontu.org/spring-mvc-tutorials-05-creating-first-spring-mvc-web-application-using-eclipse-ide-01/
 * http://www.gontu.org/wp-content/uploads/2014/08/creatingfirstwebappliaction.zip
 * 
 */

package com.gontuseries.hellocontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
 
public class HelloController extends AbstractController{
 
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		ModelAndView modelandview = new ModelAndView("HelloPage");
		modelandview.addObject("welcomeMessage", "Hi User, welcome to the first Spring MVC Application");
		
		return modelandview;
	}
}