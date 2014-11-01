/*
 * http://www.dineshonjava.com/2012/12/spring-30-mvc-hello-world-example.html
 * https://sites.google.com/site/dinesh9582486434/my-forms/SpringMVCHelloWorld.zip
 * 
 */
package com.dineshonjava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Dinesh Rajput
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String sayHelloWorld(ModelMap model) {
		model.addAttribute("message", "Spring 3.0 MVC Framework Hello World Example!");
		model.addAttribute("auther", "DineshOnJava");
		return "hello";
	}
}
