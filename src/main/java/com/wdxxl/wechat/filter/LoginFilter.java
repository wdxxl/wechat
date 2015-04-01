package com.wdxxl.wechat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;


public class LoginFilter implements Filter {
	Logger logger = Logger.getLogger(LoginFilter.class);
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		//Do Some Validation
		logger.debug("LoginFilter doFilter.");
		System.out.println("LoginFilter doFilter.");
		//If Already Login then Continue Else return Exception or sendRedirect or forword
		chain.doFilter(request, response);
	}

	public void destroy() {
		
	}

}
