package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name="memberCreateEndServlet", urlPatterns="/memberCreateEnd")
public class MemberCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    public MemberCreateEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		
		Member member = new Member(id, pw, name);
		int result = memberService.createMember(member);
		JSONObject obj = new JSONObject();
		obj.put("res_code", "500");
		obj.put("res_msg", "회원가입 중 오류가 발생하였습니다.");
		
		if(result > 0) {
			obj.put("res_code", "200");
			obj.put("res_msg", "정상적으로 회원가입 되었습니다.");
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
		
//		RequestDispatcher view = request.getRequestDispatcher("/views/member/create_fail.jsp");
//		int result = memberService.createMember(member);
//		if(result > 0) {
//			view = request.getRequestDispatcher("/views/member/create_success.jsp");
//		}
//		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
