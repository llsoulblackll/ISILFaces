package com.rs.service;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.ejb.facade.local.StudentFacadeLocal;
import com.ozcorp.util.aws.Prediction;

@ManagedBean
@RequestScoped
@Path("faces")
@Consumes(MediaType.WILDCARD)
public class FacesService {
	
	//@EJB
	//private CompareFacesFacadeLocal compareFacesFacade;
	
	@EJB
	private StudentFacadeLocal studentFacade;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String echo() {
        //return String.valueOf(compareFacesFacade.compareFaces(null, null));
    	return "";
    }
    
    @GET
    @Path("findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prediction> predictions() {
        return studentFacade.findAllPredictions();
    }
    
    
}
