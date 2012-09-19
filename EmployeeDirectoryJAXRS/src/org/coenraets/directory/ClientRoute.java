package org.coenraets.directory;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientRoute {

    private int agent_id;

    private String client;
    
    private String route;

    private String agent;

    private String zone;

	
	public ClientRoute() {

    }
	
	public int getAgentID() {
		return agent_id;
	}

	public void setAgentID(int id_agent) {
		this.agent_id = id_agent;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String the_route) {
		this.route = the_route;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String the_agent) {
		this.agent = the_agent;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String the_zone) {
		this.zone = the_zone;
	}


}
