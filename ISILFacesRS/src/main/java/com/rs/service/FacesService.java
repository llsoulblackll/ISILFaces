package com.rs.service;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;

@ManagedBean
@RequestScoped
@Path("faces")
@Consumes(MediaType.WILDCARD)
public class FacesService {
	
	@EJB
	private CompareFacesFacadeLocal compareFacesFacade;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String echo() {
        return "Hello World";//String.valueOf(compareFacesFacade.compareFaces());
    }
}
