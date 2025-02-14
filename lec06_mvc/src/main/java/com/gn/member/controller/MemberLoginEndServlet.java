package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name="memberLoginEnd", urlPatterns="/memberLoginEnd")
public class MemberLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    public MemberLoginEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member_id");
		String memberPw = request.getParameter("member_pw");
		
		Member member = memberService.loginMember(memberId, memberPw);
		if(member != null) {
			HttpSession session = request.getSession();
			if(session.isNew() || session.getAttribute("member") == null) {
				session.setAttribute("member", member);
				session.setMaxInactiveInterval(60*30);
			}
			response.sendRedirect("/");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/views/member/login_fail.jsp");
			view.forward(request, response);		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
