/* 
 * 
 * http://localhost:8080/EjbRest/customers/1
 * 
 */

package com.example.ejbrest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/customers")
@Stateless
@LocalBean
public class CustomerWS {

    @EJB
    private CustomerDAO customersDao;

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        return customersDao.getCustomer(id);
    }
    
    @POST
    public void addCustomers(List<Customer> customers) {
        customersDao.addCustomers(customers);
    }
    
}


/*
* CREATE TABLE Customer (
*   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
*   name VARCHAR(255),
*   surname VARCHAR(255)
*  );
*
*  INSERT INTO Customer (name, surname) VALUES ('John','Smith');
*  
*/
