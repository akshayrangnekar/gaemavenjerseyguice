package com.listerly;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@Singleton
public class HelloWorldServlet extends HttpServlet {

	@Inject
	TestClass theClass;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, handrolled, guiced AppEngines!");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		resp.getWriter().println("Date now is: " + sdf.format(theClass.getDate()));
	}

}
