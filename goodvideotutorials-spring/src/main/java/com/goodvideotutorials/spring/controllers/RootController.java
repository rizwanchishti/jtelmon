/*
 * Run As -> Spring Boot App or Java Application 
 * 
 */
package com.goodvideotutorials.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/Hello")

public class RootController {

		// @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	    @RequestMapping("/")
		// public String getHello(@PathVariable String name, ModelMap model) {
		public String home() {
			// model.addAttribute("name", name);
			return "welcome";
		}
}
