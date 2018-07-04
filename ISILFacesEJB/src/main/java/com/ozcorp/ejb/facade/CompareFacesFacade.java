package com.ozcorp.ejb.facade;

import static com.ozcorp.util.aws.AWSUtil.SIMILARITY_THRESHOLD;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.Attribute;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.DetectFacesRequest;
import com.amazonaws.services.rekognition.model.DetectFacesResult;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Emotion;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.InvalidParameterException;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.ejb.facade.local.StudentFacadeLocal;
import com.ozcorp.util.aws.AWSUtil;
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
					.withSourceImage(prediction)
					.withTargetImage(new Image()
							.withBytes(ByteBuffer.wrap(targetImage)))
					.withSimilarityThreshold(threshold != null ? threshold : SIMILARITY_THRESHOLD);
			
			CompareFacesResult result;
			
			try {
				result = rekognitionClient.compareFaces(request);
			} catch (InvalidParameterException ex) {
				return null;
			}
			
			if(result.getFaceMatches().size() > 0) {
				CompareFacesMatch bestMatch = result.getFaceMatches().stream().max((f1, f2) -> {
					return (f1.getSimilarity() > f2.getSimilarity() ? f1.getSimilarity() : f2.getSimilarity()).intValue();
				}).get();
				prediction.setAccuracy(bestMatch.getSimilarity());
				closestResult = prediction;
				break;
			}
		}
		
		if(closestResult == null)
			closestResult = new Prediction(null, 0);
		
		return closestResult;
		//return result.getFaceMatches().size() > 0 ? result.getFaceMatches().get(0).getSimilarity() : 0.0f;
	}

	@Override
	public List<Prediction> compareAllFaces(byte[] targetImage, Float threshold) {
		List<Prediction> predictions = new ArrayList<>();
		
		for(Prediction posible : studentFacade.findAllPredictions()) {
			CompareFacesRequest request = new CompareFacesRequest()
					.withSourceImage(posible)
					.withTargetImage(new Image()
							.withBytes(ByteBuffer.wrap(targetImage)))
					.withSimilarityThreshold(threshold != null ? threshold : AWSUtil.SIMILARITY_THRESHOLD);
			
			CompareFacesResult result;
			
			try {
				result = rekognitionClient.compareFaces(request);
			} catch (InvalidParameterException e) {
				continue;
			}
			
			if(result.getFaceMatches().size() > 0) {
				CompareFacesMatch bestMatch = result.getFaceMatches().stream().max((f1, f2) -> {
					return (f1.getSimilarity() > f2.getSimilarity() ? f1.getSimilarity() : f2.getSimilarity()).intValue(); 
				}).get();
				posible.setAccuracy(bestMatch.getSimilarity());
				predictions.add(posible);
			}
		}
		
		return predictions;
	}
	
	public List<String> detectLabels(byte[] targetImage) {
		DetectLabelsRequest request = new DetectLabelsRequest()
				.withImage(new Image()
						.withBytes(ByteBuffer.wrap(targetImage)));
		DetectLabelsResult result = rekognitionClient.detectLabels(request);
		
		return result.getLabels().stream().filter(l -> l.getConfidence() > 80).map(Label::getName).collect(Collectors.toList());
	}

	@Override
	public List<String> detectFace(byte[] targetImage) {
		List<String> details = new ArrayList<>();
		DetectFacesRequest request = new DetectFacesRequest()
				.withImage(new Image()
						.withBytes(ByteBuffer.wrap(targetImage)))
				.withAttributes(Attribute.ALL);
		
		DetectFacesResult result = rekognitionClient.detectFaces(request);
		FaceDetail bestMatch = result.getFaceDetails().stream().max(Comparator.comparing(FaceDetail::getConfidence)).orElseGet(() -> null);
		
		if(bestMatch != null) {
			details.add("Edad: " + Integer.toString((bestMatch.getAgeRange().getHigh() + bestMatch.getAgeRange().getLow()) / 2));
			details.add("Emociones: " + bestMatch.getEmotions().stream().map(Emotion::getType).collect(Collectors.joining(", ")));
			details.add("Sexo: " + bestMatch.getGender().getValue());
		}
		
		return details;
	}
	
}
