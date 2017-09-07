package com.capgemini.cisco.portal.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class SessionTrackingFilter implements Filter {

	private ArrayList<String> urlList;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("Session Tracking Filter Execution");
		String url = request.getServletPath(); // get url from current request
		System.out.println("url=" + url); // print url-debugging purpose
		boolean allowedRequest = false;
	
		if (urlList.contains(url)) {
			allowedRequest = true;
		}
		if (!allowedRequest) { // for every other url
			HttpSession session = request.getSession(false); // returns null if session has expired or invalidated
															
			if (null == session) { // check for null(expired) session
				
				String name = ((HttpServletRequest) request).getRequestURI();
				System.out.println("No Cache Filtering: " + name);
				HttpServletResponse httpresponse = (HttpServletResponse) response;
				// Set the Cache-Control and Expires header
				httpresponse.setHeader("Cache-Control", "no-cache");
				httpresponse.setHeader("Expires", "0");
				// Print out the URL we're filtering
				request.getRequestDispatcher("/CommonJsp/Logout.jsp").forward(
						request, response);
				
				
				return;
			} 
			else if (session.getAttribute("valid") == null) { // check for valid session													
		
				String name = ((HttpServletRequest) request).getRequestURI();
				System.out.println("No Cache Filtering: " + name);
				HttpServletResponse httpresponse = (HttpServletResponse) response;
				// Set the Cache-Control and Expires header
				httpresponse.setHeader("Cache-Control", "no-cache");
				httpresponse.setHeader("Expires", "0");
				// Print out the URL we're filtering
				
				response.sendRedirect("/LoginPage.jsp"); // in this case also, redirect to login.jsp								
				return;
			}
		}
		chain.doFilter(request, response);

		//
		// HttpServletRequest request = (HttpServletRequest) req;
		// HttpServletResponse response = (HttpServletResponse) res;
		// System.out.println("Filter Execution");
		// String url = request.getServletPath();
		// System.out.println(url);
		// boolean allowedRequest = false;
		//
		// if (urlList.contains(url)) {
		// allowedRequest = true;
		// }
		//
		// if (!allowedRequest) {
		// HttpSession session = request.getSession(false);
		// if (session==null) {
		// response.sendRedirect("./LoginPage.jsp");
		// return;
		// }
		// }
		// chain.doFilter(req, res);
		//
	}

	public void init(FilterConfig config) throws ServletException {

		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());

		}
	}

}
