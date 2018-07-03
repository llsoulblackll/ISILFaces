package com.ozcorp.util.aws;

import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.ozcorp.ejb.entity.Student;

public class Prediction extends Image {
	
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private float accuracy;
	
	public Prediction() {
		withS3Object(new S3Object()
				.withBucket(AWSUtil.BUCKET_NAME)
				.withName(this.student.getProfilePicture())
		);
	}

	public Prediction(Student student, float accuracy) {
		this();
		this.student = student;
		this.accuracy = accuracy;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
}
