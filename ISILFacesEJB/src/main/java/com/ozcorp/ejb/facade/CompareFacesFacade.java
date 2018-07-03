package com.ozcorp.ejb.facade;

import static com.ozcorp.util.aws.AWSUtil.SIMILARITY_THRESHOLD;

import java.nio.ByteBuffer;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.ejb.facade.local.StudentFacadeLocal;
import com.ozcorp.util.aws.Prediction;

@Stateless
public class CompareFacesFacade implements CompareFacesFacadeLocal {

	@EJB
	private StudentFacadeLocal studentFacade;
	
	private AmazonRekognition rekognitionClient;
	
	public CompareFacesFacade() {
		rekognitionClient = AmazonRekognitionClient.builder()
				.withCredentials(new EnvironmentVariableCredentialsProvider())
				.withRegion(Regions.US_WEST_2)
				.build();
	}
	
	@Override
	public Prediction compareFaces(byte[] targetImage, Float threshold) {
		
		Prediction closestResult = null;
		
		for(Prediction prediction : studentFacade.findAllPredictions()) {
			CompareFacesRequest request = new CompareFacesRequest()
					.withSourceImage(new Image()
							.withBytes(ByteBuffer.wrap(targetImage)))
					.withTargetImage(prediction)
					.withSimilarityThreshold(threshold != null ? threshold : SIMILARITY_THRESHOLD);

			CompareFacesResult result = rekognitionClient.compareFaces(request);
			
			if(result.getFaceMatches().size() > 0) {
				CompareFacesMatch bestMatch = result.getFaceMatches().stream().max((f1, f2) -> {
					return (f1.getSimilarity() > f2.getSimilarity() ? f1.getSimilarity() : f2.getSimilarity()).intValue();
				}).get();
				prediction.setAccuracy(bestMatch.getSimilarity());
				closestResult = prediction;
				break;
			}
		}
		
		return closestResult;
		//return result.getFaceMatches().size() > 0 ? result.getFaceMatches().get(0).getSimilarity() : 0.0f;
	}

}
