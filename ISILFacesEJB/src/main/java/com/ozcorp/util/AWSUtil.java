package com.ozcorp.util;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;

public class AWSUtil {
	
	public static final String BUCKET_NAME = "isilfaces-bucket";
	
	private static float SIMILARITY_THRESHOLD = 83f;
	
	public static float compareFaces() {
		AmazonRekognition rekognitionClient = AmazonRekognitionClient.builder()
				.withCredentials(new EnvironmentVariableCredentialsProvider())
				.build();
		CompareFacesRequest request = new CompareFacesRequest()
				.withSourceImage(new Image()
						.withS3Object(new S3Object()
								.withBucket(BUCKET_NAME)
								.withName("dua_lipa.jpg")))
				.withTargetImage(new Image()
						.withS3Object(new S3Object()
								.withBucket(BUCKET_NAME)
								.withName("dua_lipa2.jpg")))
				.withSimilarityThreshold(SIMILARITY_THRESHOLD);
		
		CompareFacesResult result = rekognitionClient.compareFaces(request);
		
		/*for (CompareFacesMatch match : result.getFaceMatches()) {
			ComparedFace face = match.getFace();
		}*/
		return result.getFaceMatches().size() > 0 ? result.getFaceMatches().get(0).getSimilarity() : 0.0f;
	}
	
}
