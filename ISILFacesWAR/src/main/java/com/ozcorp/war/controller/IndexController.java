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
	private String base64Holder;
	private Prediction chosen;
	private String resultImage;
	
	public void compare() throws IOException {
		System.out.println(base64Holder);
		chosen = compareFacesFacade.compareFaces(IOUtils.toByteArray(uploadedFile.getInputStream()), null);
		if(chosen == null)
			accuracy = "No se detecto ningun rostro en la imagen";
		else {
			accuracy = String.valueOf(chosen.getAccuracy());
			resultImage = String.format("https://isilfaces-bucket.s3.amazonaws.com/%s", chosen.getStudent().getProfilePicture());
		}
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

	public String getBase64Holder() {
		return base64Holder;
	}

	public void setBase64Holder(String base64Holder) {
		this.base64Holder = base64Holder;
	}

	public Prediction getChosen() {
		return chosen;
	}

	public void setChosen(Prediction chosen) {
		this.chosen = chosen;
	}

	public String getResultImage() {
		return resultImage;
	}

	public void setResultImage(String resultImage) {
		this.resultImage = resultImage;
	}	
}
