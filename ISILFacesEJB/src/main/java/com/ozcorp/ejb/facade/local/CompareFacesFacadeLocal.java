package com.ozcorp.ejb.facade.local;

import java.util.List;

import javax.ejb.Local;

import com.ozcorp.util.aws.Prediction;

@Local
public interface CompareFacesFacadeLocal {
	Prediction compareFaces(byte[] targetImage, Float threshold);
	List<Prediction> compareAllFaces(byte[] targetImage, Float threshold);
	List<String> detectFace(byte[] targetImage);
	List<String> detectLabels(byte[] targetImage);
}
