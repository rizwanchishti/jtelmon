
/*
 * 
 * http://persistentdesigns.com/wp/2009/08/jsonwithpadding-callbacks-json-xml-string-and-the-genericentity/
 * 
 * http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
 * 
 * http://stackoverflow.com/questions/7112672/jquery-ajax-call-to-rest-service
 * http://www.ryannedolan.info/teaching/cs4830/topics/same-origin-policy/json-with-padding
 * http://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=myFunctionName
 * http://code.google.com/p/mobile-app-demo/source/browse/src/main/java/com/mobilepoc/jersey/AccountsRetriever.java?r=e38a19954ee57590aae677392c17a1bfeb00964b
 * https://programmaticponderings.wordpress.com/tag/jsonwithpadding/
 * http://programmaticponderings.wordpress.com/2012/10/01/returning-jsonp-from-a-restful-web-services-using-jquery-jersey-glassfish-part-1-of-2/
 * 
 */

package org.coenraets.directory;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;

import org.codehaus.jettison.json.JSONObject;
// import org.json.JSONObject;

import org.coenraets.directory.Person;
import com.sun.jersey.api.json.JSONWithPadding;

@Path("/jsonp")
@Produces("application/x-javascript")


public class PersonResource {
	private static final List<Person> persons = new ArrayList<Person>();
	static {
		persons.add(new Person(1,"Authur Koestler",99));
		persons.add(new Person(1,"Karl Popper",44));
		persons.add(new Person(1,"Stephen Mumford",79));
	}

	// http://192.168.61.207:8080/PostgresWebServiceJSONP/rest/jsonp/string
	@GET
	@Path("/string")
	public JSONWithPadding getString(@QueryParam("callback") String callback) {
		StringBuffer buffer = new StringBuffer("'");
		for (Person person : persons) {
			buffer.append(person.getName()).append(": ").append(person.getAge());
		}
		buffer.append("'");
		System.out.println(buffer.toString());
		return new JSONWithPadding(buffer.toString(),callback);
	}

	// http://localhost:8080/PostgresWebServiceJSONP/rest/jsonp/json
	@GET
	@Path("/json")
	public JSONWithPadding getJson(@QueryParam("callback") String callback)
			throws Exception {
		JSONObject jsonPerson = new JSONObject();

		for (Person person : persons) {
			JSONObject obj = new JSONObject();
			obj.put("id", person.getId());
			obj.put("name", person.getName());
			obj.put("age", person.getAge());
			jsonPerson.accumulate("persons", obj);
		}
		System.out.println(jsonPerson.toString());
		return new JSONWithPadding(jsonPerson, callback);
	}

	@GET
	@Path("/jaxbObject")
	public JSONWithPadding getJaxbObject(@QueryParam("callback") String callback)
			throws Exception {
		return new JSONWithPadding(persons.get(0), callback);
	}

	@GET
	@Path("/jaxbCollection")
	public JSONWithPadding getJaxbCollection(@QueryParam("callback") String callback)
			throws Exception {
		return new JSONWithPadding(new GenericEntity<List<Person>>(persons) {}, callback);
	}

}
