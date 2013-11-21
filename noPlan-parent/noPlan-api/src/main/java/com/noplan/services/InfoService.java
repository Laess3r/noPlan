package com.noplan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author DaHu4wA (Stefan Huber)
 */
// @Path("/info")
public interface InfoService {

	/**
	 * @return call this to check if the session is valid. If not, a exception
	 *         will be thrown.
	 */
	@GET
	@Path("/checksession")
	@Produces(MediaType.APPLICATION_JSON)
	String isSessionValid();

}
