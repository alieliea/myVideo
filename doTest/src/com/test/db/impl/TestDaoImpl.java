package com.test.db.impl;

import com.test.db.TestDao;
import com.test.entity.Test;

public class TestDaoImpl extends BaseDaoImpl<Test> implements TestDao{

	public TestDaoImpl(Class<Test> t) {
		super(t);
	}
}