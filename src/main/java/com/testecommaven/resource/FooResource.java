package com.testecommaven.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "foos")
public class FooResource {

	@GET
	public String sayHello() {
		return "Hello RESTful!";
	}
	
}
