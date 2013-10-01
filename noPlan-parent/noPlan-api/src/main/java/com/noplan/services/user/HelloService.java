package com.noplan.services.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.noplan.data.user.User;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/user")
public class HelloService {

	private static int count = 0;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") Long id) {
		count++;

		// TODO schauen ob das mit interfaces funktioniert
		
		// TODO datenbank-connection!
		
		User u = new User();
		u.setId(id);
		u.setUsername(id + "TEST" + count);
		return u;
	}

}
