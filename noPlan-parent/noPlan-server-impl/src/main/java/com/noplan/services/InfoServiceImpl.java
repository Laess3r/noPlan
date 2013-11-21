package com.noplan.services;

import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

/**
 * @author DaHu4wA (Stefan Huber)
 */
@Path("/info")
@Service
public class InfoServiceImpl implements InfoService {

	@Override
	public String isSessionValid() {
		return "valid";
	}

	

}
