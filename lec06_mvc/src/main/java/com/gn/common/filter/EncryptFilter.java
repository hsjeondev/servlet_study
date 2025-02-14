package com.gn.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames = {"memberCreateEndServlet", "memberLoginEnd", "memberUpdateEnd"})
public class EncryptFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;

	public EncryptFilter() {}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PassWordEncodingWrapper pew = new PassWordEncodingWrapper((HttpServletRequest)request);
		chain.doFilter(pew, response);
	}

	public void destroy() {
	
	}

}
