package org.coenraets.directory;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.json.JSONWithPadding;

@Path("/sales")
public class SoftyResource {

	SoftyDAO dao = new SoftyDAO();
	
	// all users = agenti : inactiv + activ cu si fara rute + desktop users 
	@GET @Path("/allusers")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserPass> findAllAgents() {
		return dao.findAllAgents();
	}
	
	// http://192.168.61.207:8080/PostgresWebService/rest/sales/allusers/jsonp
	@GET
	@Path("/allusers/jsonp")
	@Produces({"application/javascript"})
	public JSONWithPadding findAllAgentsJsonP( @QueryParam("callback") String callback) {
	    Collection<UserPass> agents = findAllAgents();
	    return new JSONWithPadding(new GenericEntity<Collection<UserPass>>(agents) {
	    }, callback);
	}

	@GET 
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<OrderData> findAllOrders() {
		return dao.findAllOrders();
	}

	// http://192.168.61.207:8080/PostgresWebService/rest/sales/eurobit/allproducts
	// http://192.168.61.207:8080/EmployeeDirectoryJAXRS20140420/rest/sales/eurobit/allproducts
	@GET @Path("/eurobit/allproducts")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ProdusEurobit> findAllEurobitProducts() {
		return dao.findAllEurobitProducts();
	}

	
	@GET @Path("/allproducts")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Product> findAllProducts() {
		return dao.findAllProducts();
	}

	@GET @Path("/allclients")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Client> findAllClients() {
		return dao.findAllClients();
	}
	
	@GET @Path("/routes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ClientRoute> GetRoutesByAgent() {
		return dao.GetRoutesByAgent();
	}

	@GET @Path("{id}/routes")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ClientRoute> GetRoutesByAgent(@PathParam("id") String agent_id) {
		return dao.GetRoutesByAgent(Integer.parseInt(agent_id));
	}
	
	@GET @Path("/search/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserPass findAgentById(@PathParam("id") String id) {
		return dao.findAgentById(Integer.parseInt(id));
	}

	// UserPass confirmAgentByPassId(int agent_id, String agent_password)
	@GET @Path("/search/{id}/{password}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserPass confirmAgentByPassId(@PathParam("id") String id , @PathParam("password") String password) {
		return dao.confirmAgentByPassId(Integer.parseInt(id), password);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public OrderData insertOrder(OrderData theOrder) {
		System.out.println("creating wine");
		return dao.insertOrder(theOrder);
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
