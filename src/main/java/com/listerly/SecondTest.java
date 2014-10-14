package com.listerly;

import com.google.inject.servlet.RequestScoped;

import javax.inject.Inject;
import java.util.Date;

@RequestScoped
public class SecondTest {

	@Inject
	private Date date;

	public SecondTest() {
	}

	public Date getDate() {
		return date;
	}

}
