package com.mashup.utils;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import java.util.Map;


public class RequestLogFilter implements Filter {

	protected FilterConfig config;

	//private String filterName;

	static Logger log = Logger.getLogger(RequestLogFilter.class.getName());

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		chain.doFilter(request, response);
		
		Map<String,String[]> params = req.getParameterMap();
		
		String simpleParams = new String();
		
		for(String key : params.keySet())
		{
			simpleParams += "["+key +" : "+ params.get(key)[0].toString()+"]";
		}
		
		String requestUrl = req.getRequestURL().toString();
		requestUrl = requestUrl.substring(requestUrl.indexOf("/bia"));		
				
		RequestLogFilter.log.debug( "{params: " + simpleParams + "} " 
									+"{request url: "+requestUrl +"} "
									+ "{Client: "+req.getRemoteHost()+"}");
	}

	public void init(FilterConfig config) throws ServletException {

		//this.config = config; // In case it is needed by subclass.
		//filterName = config.getFilterName();
	}

	public void destroy() {
	}

}