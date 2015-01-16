/*
 * http://www.sivalabs.in/2011/04/springmvc3-hibernate-crud-sample.html
 * http://www.javacodegeeks.com/2011/04/spring-mvc3-hibernate-crud-sample.html
 *
 * http://dtr-trading.blogspot.ro/2014/02/spring-mvc-4-and-hibernate-4.html
 *  https://github.com/dtr-trading/spring-ex02
 * 
 * http://fruzenshtein.com/spring-mvc-hibernate-maven-crud/
 * https://github.com/Fruzenshtein/spr-mvc-hib
 * http://www.javacodegeeks.com/2013/04/spring-mvc-hibernate-maven-crud-operations-example.html
 * 
 */


package com.sivalabs.contacts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author SivaLabs
 *
 */

@Controller
public class ContactsControllers
{
	@Autowired
	private ContactsDAO contactsDAO;
	
	@Autowired
	private ContactFormValidator validator;
	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
		
	@RequestMapping("/searchContacts")
	public ModelAndView searchContacts(@RequestParam(required= false, defaultValue="") String name)
	{
		ModelAndView mav = new ModelAndView("showContacts");
		List<Contact> contacts = contactsDAO.searchContacts(name.trim());
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}
	
	@RequestMapping("/viewAllContacts")
	public ModelAndView getAllContacts()
	{
		ModelAndView mav = new ModelAndView("showContacts");
		List<Contact> contacts = contactsDAO.getAllContacts();
		mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
		return mav;
	}
	
	@RequestMapping(value="/saveContact", method=RequestMethod.GET)
	public ModelAndView newuserForm()
	{
		ModelAndView mav = new ModelAndView("newContact");
		Contact contact = new Contact();
		mav.getModelMap().put("newContact", contact);
		return mav;
	}
	
	@RequestMapping(value="/saveContact", method=RequestMethod.POST)
	public String create(@ModelAttribute("newContact")Contact contact, BindingResult result, SessionStatus status)
	{
		validator.validate(contact, result);
		if (result.hasErrors()) 
		{				
			return "newContact";
		}
		contactsDAO.save(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}
	
	@RequestMapping(value="/updateContact", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("editContact");
		Contact contact = contactsDAO.getById(id);
		mav.addObject("editContact", contact);
		return mav;
	}
	
	@RequestMapping(value="/updateContact", method=RequestMethod.POST)
	public String update(@ModelAttribute("editContact") Contact contact, BindingResult result, SessionStatus status)
	{
		validator.validate(contact, result);
		if (result.hasErrors()) {
			return "editContact";
		}
		contactsDAO.update(contact);
		status.setComplete();
		return "redirect:viewAllContacts.do";
	}
	
	
	@RequestMapping("deleteContact")
	public ModelAndView delete(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("redirect:viewAllContacts.do");
		contactsDAO.delete(id);
		return mav;
	}
	
}
