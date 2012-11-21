package org.coenraets.directory;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Access-Control-Allow-Origin @Produces MediaType.APPLICATION_JSON
// import javax.ws.rs.core.Context;
// import javax.servlet.http.HttpServletResponse;
// public List<Employee> findAll(@Context HttpServletResponse serverResponse) {
 
@Path("/employees")
public class EmployeeResource {

	EmployeeDAO dao = new EmployeeDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Employee> findAll() {
		return dao.findAll();
	}

	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee findById(@PathParam("id") String id) {
		return dao.findById(Integer.parseInt(id));
	}
	
	@GET @Path("{id}/reports")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Employee> findByManager(@PathParam("id") String managerId) {
		return dao.findByManager(Integer.parseInt(managerId));
	}
}
