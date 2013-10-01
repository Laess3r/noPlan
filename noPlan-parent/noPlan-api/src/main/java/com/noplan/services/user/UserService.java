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
public interface UserService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO getUserById(@PathParam("id")
    Long id);

    // TODO @POST
    @GET
    @Path("/create/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
	UserDTO createUser(@PathParam("username") String username,@PathParam("password") String password);

}
