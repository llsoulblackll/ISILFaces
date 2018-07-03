package com.ozcorp.ejb.facade.local;

import javax.ejb.Local;

import com.ozcorp.util.aws.Prediction;

@Local
public interface CompareFacesFacadeLocal {
	Prediction compareFaces(byte[] targetImage, Float threshold);
}
