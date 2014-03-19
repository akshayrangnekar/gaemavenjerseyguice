package com.listerly.config.jersey;

import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Used to filter out GAE reserved URLs
 * See: https://developers.google.com/appengine/docs/java/config/appconfig
 */
@SuppressWarnings("serial")
public class JerseyFilter extends ServletContainer {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String path = req.getServletPath();
        if (path.startsWith("/_ah/") || path.equals("/form")) {
            chain.doFilter(req, res);
        } else {
            super.doFilter(req, res, chain);
        }
    }
}