package com.gn.practice.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order")
public class PizzaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PizzaController() {}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("user_name");
		String userPhone = req.getParameter("user_phone");
		String userEmail = req.getParameter("user_email");
		String size = req.getParameter("size");
		String[] toppings = req.getParameterValues("add");
		String deliveryTime = req.getParameter("delivery_time");
		
		
		
		int price = 0;
		// 1. size 기준
		// Small : 15900원
		// Medium : 21000원
		// Large : 27900원
		if(size.equals("1")) {
			price = 15900;
//			size = "Small";
		} else if(size.equals("2")) {
			price = 21000;
//			size = "Medium";
		} else if(size.equals("3")) {
			price = 27900;
//			size = "Large";
		}
		
		// 2. 토핑 추가
		// 새우 + 2000원
		// 고구마, 감자, 파인애플 +1000원
		for(int i = 0; i < toppings.length; i++) {
			if(toppings[i].equals("1")) {
				price += 2000;
			} else {
				price += 1000;
			}
		}
		
		String[] toppingNames = new String[toppings.length];
		Map<String, String> toppingMap = 
				Map.of("1","새우", "2","고구마", "3","감자", "4","파인애플");
		System.arraycopy(toppings, 0, toppingNames, 0, toppings.length);
		for(int i = 0; i < toppings.length; i++) {
			toppingNames[i] = toppingMap.get(toppings[i]);
		}
		
		RequestDispatcher view = req.getRequestDispatcher("views/pizzaPayment.jsp");
		
		req.setAttribute("name", userName);
		req.setAttribute("phone", userPhone);
		req.setAttribute("email", userEmail);
		req.setAttribute("size", size);
		req.setAttribute("toppings", toppings);
		req.setAttribute("price", price);
		req.setAttribute("time", deliveryTime);
		
		view.forward(req, resp);
		
//		System.out.println("이름 : " + userName);
//		System.out.println("전화번호 : " + userPhone);
//		System.out.println("이메일 : " + userEmail);
//		System.out.println("피자 사이즈 : " + size);
//		System.out.println("피자 토핑 : " + String.join(", ", toppingNames));
//		System.out.println("가격 : " + price);
//		System.out.println("배달 시간 : " + deliveryTime);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
