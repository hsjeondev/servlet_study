package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name="memberUpdateEnd", urlPatterns="/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    public MemberUpdateEndServlet() {
    }

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("member_no"));
		String memberPw = request.getParameter("member_pw");
		String memberName = request.getParameter("member_name");
		
		Member member = new Member(memberNo, memberPw, memberName);
		int result = memberService.updateMember(member);
		JSONObject obj = new JSONObject();
		obj.put("res_code", "500");
		obj.put("res_msg", "변경 중 오류가 발생하였습니다.");
		System.out.println("result : " + result);
		if(result > 0) {
			member = memberService.selectMember(memberNo);
			
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute("member") != null) {
				session.setAttribute("member", member);
				session.setMaxInactiveInterval(60*60*30);
			}
			obj.put("res_code", "200");
			obj.put("res_msg", "정상적으로 변경 되었습니다.");
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
