package com.marakana.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.marakana.contracts.entities.Address;
import com.marakana.contracts.entities.Contact;
import com.marakana.contracts.repositories.AddressRepository;
import com.marakana.contracts.repositories.ContactRepository;

@WebListener
public class Setup implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
			new AddressRepository().init();
			//System.out.println("@@@ STEP 1 COMPLETED");
			//new AddressRepository().create(new Address("asdf", "asdf", "asdf", "asdf"));
			//System.out.println("@@@ STEP 2 COMPLETED");
			new ContactRepository().init();
			//System.out.println("@@@ STEP 3 COMPLETED");
			//new ContactRepository().create(new Contact("dan", 0L));
			//System.out.println("@@@ STEP 4 COMPLETED");
		} catch (SQLException e) {
			//System.out.println("@@@ SOMETHING BAD HAPPENED");
			e.printStackTrace();
		}

		
	}

}
