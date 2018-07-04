package com.ozcorp.war.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.amazonaws.util.IOUtils;
import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.util.aws.Prediction;

@Named("index")
@ViewScoped
public class IndexController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CompareFacesFacadeLocal compareFacesFacade;
	
	private Part uploadedFile;
	private Float similarityThreshold;
	private String accuracy;
	
	public void compare() throws IOException {
		Prediction result = compareFacesFacade.compareFaces(IOUtils.toByteArray(uploadedFile.getInputStream()), null);
		if(result == null)
			accuracy = "No se detecto ningun rostro en la imagen";
		else
			accuracy = String.valueOf(result.getAccuracy());
	}
	
	public Part getUploadedFile() {
		return uploadedFile;
	}
	
	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	public Float getSimilarityThreshold() {
		return similarityThreshold;
	}
	
	public void setSimilarityThreshold(Float similarityThreshold) {
		this.similarityThreshold = similarityThreshold;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}	
}
