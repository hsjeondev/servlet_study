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

import com.gn.common.wrapper.StringUpperWrapper;

/**
 * Servlet Filter implementation class UpperCaseFilter
 */
@WebFilter("/receive/upper")
public class UpperCaseFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;


    public UpperCaseFilter() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		StringUpperWrapper suw = new StringUpperWrapper((HttpServletRequest)request);
		System.out.println("너 되니");
		chain.doFilter(suw, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
