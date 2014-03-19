package com.listerly;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestClass {

	@Inject
	private Date date;
	
	public TestClass() {
	}
	
	public Date getDate() {
		return date;
	}
}
