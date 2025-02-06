package com.gn.homework.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/borrow")
public class BookReservationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public BookReservationController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String book = request.getParameter("selectBook");
		String loanPeriod = request.getParameter("loanPeriod");
		int pay = 0;
		
		String bookName = "";
		if(book.equals("1")) {
			bookName = "자바 프로그래밍 입문";
			pay = 1500;
		} else if(book.equals("2")) {
			bookName = "웹 개발의 기초";
			pay = 1800;
		} else if(book.equals("3")) {
			bookName = "데이터베이스 시스템";
			pay = 2000;
		}
		
		pay *= Integer.parseInt(loanPeriod);
		
		RequestDispatcher view = request.getRequestDispatcher("views/homework/inquiryMyborrow.jsp");
		
		request.setAttribute("userName", userName);
		request.setAttribute("userPhone", userPhone);
		request.setAttribute("userEmail", userEmail);
		request.setAttribute("bookName", bookName);
		request.setAttribute("loanPeriod", loanPeriod);
		request.setAttribute("pay", pay);
		
		view.forward(request, response);
	}

}
