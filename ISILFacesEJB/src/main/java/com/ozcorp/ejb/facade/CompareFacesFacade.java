package com.ozcorp.ejb.facade;

import javax.ejb.Stateless;

import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.util.AWSUtil;

@Stateless
public class CompareFacesFacade implements CompareFacesFacadeLocal {

	@Override
	public float compareFaces() {
		return AWSUtil.compareFaces();
	}

}
