package org.coenraets.directory;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.json.JSONWithPadding;

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

	// http://ftp.pangram.ro:8090/PgWebS/rest/employees/jsonp?callback
	@GET
	@Path("/jsonp")
	@Produces({"application/javascript"})
	public JSONWithPadding findAllJsonP( @QueryParam("callback") String callback) {
	    Collection<Employee> employees = findAll();
	    return new JSONWithPadding(new GenericEntity<Collection<Employee>>(employees){},callback);
	}

	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee findById(@PathParam("id") String id) {
		return dao.findById(Integer.parseInt(id));
	}

	
	// http://ftp.pangram.ro:8090/PgWebS/rest/employees/jsonp/1?callback
	@GET
	@Path("/jsonp/{id}")
	@Produces({"application/javascript"})
	public JSONWithPadding findByIdJsonP( @PathParam("id") String id , @QueryParam("callback") String callback) {
	    return new JSONWithPadding(new GenericEntity<Employee>(dao.findById(Integer.parseInt(id))){},callback);
	}
	
	@GET @Path("{id}/reports")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Employee> findByManager(@PathParam("id") String managerId) {
		return dao.findByManager(Integer.parseInt(managerId));
	}
}
