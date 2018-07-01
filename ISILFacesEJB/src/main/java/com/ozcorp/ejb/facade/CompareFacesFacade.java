package com.ozcorp.ejb.facade;

import com.ozcorp.ejb.facade.local.CompareFacesFacadeLocal;
import com.ozcorp.util.AWSUtil;

public class CompareFacesFacade implements CompareFacesFacadeLocal {

	@Override
	public float compareFaces() {
		return AWSUtil.compareFaces();
	}

}
