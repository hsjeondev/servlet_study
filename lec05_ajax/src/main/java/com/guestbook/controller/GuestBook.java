package com.guestbook.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/guestBook")
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBook() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String guestName = request.getParameter("guestName");
		String guestMessage = request.getParameter("guestMessage");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("guestName", guestName);
		jsonObject.put("guestMessage", guestMessage);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
