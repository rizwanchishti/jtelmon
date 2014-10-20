/*
 * 
 *  http://yatb.giacomodrago.com/en/post/7/tutorial-restful-web-service-ejb-hibernate-jpa-jboss.html
 *  
 *  This is just a placeholder class telling the container that your application 
 *  should be deployed as a RESTful web service.
 * 
 *  http://localhost:8080/EjbRest/customers/1
 *  
 */

package com.example.ejbrest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {
}
