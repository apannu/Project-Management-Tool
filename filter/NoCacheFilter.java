package com.capgemini.cisco.portal.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

	private ArrayList<String> urlList;

	public NoCacheFilter() {

	}

	public void destroy() {

		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		try {
			if (response instanceof HttpServletResponse) {
			
				boolean allowedRequest = false;
				String url = ((HttpServletRequest) request).getRequestURI();
				System.out.println(url);
				
				if (urlList.contains(url)) {
					allowedRequest = true;
				}
				if (!allowedRequest) { 
				
					String name = ((HttpServletRequest) request).getRequestURI();
					System.out.println("No Cache Filtering: " + name);
					HttpServletResponse httpresponse = (HttpServletResponse) response;
					// Set the Cache-Control and Expires header
					httpresponse.setHeader("Cache-Control", "no-cache");
					httpresponse.setHeader("Expires", "0");
					// Print out the URL we're filtering
				}
				
			}
			chain.doFilter(request, response);
		} catch (IOException e) {
			System.out.println("IOException in NoCacheFilter");
			e.printStackTrace();
		} catch (ServletException e) {
			System.out.println("ServletException in NoCacheFilter");
			e.printStackTrace();
		}

	}

	public void init(FilterConfig config) throws ServletException {

		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());
		}

	}

	private FilterConfig filterConfig;

	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
