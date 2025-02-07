package com.gn.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MsgRequestWrapper extends HttpServletRequestWrapper {
	
	public MsgRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		// name은 우리가 지정한 키값
		if(name.equals("msg")) {
			return super.getParameter(name)+"-gn-";
		} else {
			return super.getParameter(name);
		}
	}

}
