package org.coenraets.directory;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/sales")
public class SoftyResource {

	SoftyDAO dao = new SoftyDAO();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserPass> findAll() {
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
