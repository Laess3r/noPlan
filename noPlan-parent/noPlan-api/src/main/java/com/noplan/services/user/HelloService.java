package com.noplan.services.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.noplan.data.user.UserDTO;

/**
 * @author DaHu4wA (Stefan Huber)
 */
// @Path("/user")
public interface HelloService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id")
    Long id);

}
