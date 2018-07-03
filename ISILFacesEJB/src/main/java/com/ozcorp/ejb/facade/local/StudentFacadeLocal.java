package com.ozcorp.ejb.facade.local;

import java.util.List;

import javax.ejb.Local;

import com.ozcorp.ejb.entity.Student;
import com.ozcorp.util.aws.Prediction;

@Local
public interface StudentFacadeLocal extends EntityFacade<Student> {
	
	List<Prediction> findAllPredictions(); 
	
}
