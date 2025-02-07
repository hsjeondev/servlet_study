package com.gn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receive/upper")
public class ChangeUpper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangeUpper() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String upperMsg = request.getParameter("upperMsg");
		System.out.println(upperMsg);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/msgShow.jsp");
	
		request.setAttribute("upperMsg", upperMsg);
		
		view.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
