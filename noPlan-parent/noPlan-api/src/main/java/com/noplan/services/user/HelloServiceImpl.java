package com.noplan.services.user;

import javax.ws.rs.Path;

import com.noplan.data.user.UserDTO;

@Path("/user")
public class HelloServiceImpl implements HelloService {

    private static int count = 0;
    
    @Override
    public UserDTO getUserById(Long id) {
        count++;
        
        UserDTO u = new UserDTO();
        u.setId(id);
        u.setUsername(id + "TEST" + count);
        return u;
    }

}
