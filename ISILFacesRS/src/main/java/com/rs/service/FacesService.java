package com.rs.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.amazonaws.util.IOUtils;
import com.ozcorp.ejb.entity.Student;
import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.ejb.facade.local.StudentFacadeLocal;
import com.ozcorp.util.aws.Prediction;

@ManagedBean
@RequestScoped
@Path("faces")
@Consumes(MediaType.WILDCARD)
public class FacesService {
	
	@EJB
	private CompareFacesFacadeLocal compareFacesFacade;
	
	@EJB
	private StudentFacadeLocal studentFacade;
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String echo() {
        //return String.valueOf(compareFacesFacade.compareFaces(null, null));
    	return "Faces Endpoint";
    }
    
    @GET
    @Path("findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> predictions() {
        return studentFacade.findAll();
    }
    
    @GET
    @Path("predict")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prediction> predict(@QueryParam("url") String url) throws IOException {
    	URL imageURL = new URL(url);
    	URLConnection conn = imageURL.openConnection();
    	try(InputStream is = conn.getInputStream()){
    		return compareFacesFacade.compareAllFaces(IOUtils.toByteArray(is), null);
    	}
    }
}
