package com.gn.account.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.account.vo.Account;

@WebServlet("/loginEnd")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String url = "jdbc:mariadb://127.0.0.1:3306/login_project";
	private static final String id = "scott";
	private static final String pw = "tiger";
       
    public LoginEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountId = request.getParameter("account_id");
		String accountPw = request.getParameter("account_pw");
		String rememberId = request.getParameter("remember_id");
		System.out.println("아이디 : " + accountId);
		System.out.println("비밀번호 : " + accountPw);
		System.out.println("아아디 저장 유무 : " + rememberId);
		
		// 1. 아이디, 비밀번호 일치하는 데이터 있는지 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		
		String sql = "SELECT *"
				+ " FROM account"
				+ " WHERE account_id = ? AND account_pw = ?";
		try {
			// SELECT
			// 기준 아이디와 비밀번호 일치(LIKE X)
			// 전체 정보 조회 -> account 객체에 담기
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountId);
			pstmt.setString(2, accountPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("account_no");
				String id = rs.getString("account_id");
				String pw = rs.getString("account_pw");
				String name = rs.getString("account_name");
				account = new Account(no, id, pw, name);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null && !conn.isClosed()) conn.close();
				if(pstmt != null && !conn.isClosed()) conn.close();
				if(conn != null && !conn.isClosed()) conn.close();
			} catch(Exception e) {
				e.getMessage();
			}
		}
		
		if(account != null) {
			// 2-1. O
			// 		-> 사용자의 정보(번호, 아이디, 비밀번호, 이름) 담고 있는 객체 세션에 저장
			HttpSession session = request.getSession();
			if(session.isNew() || session.getAttribute("account") == null) {
				session.setAttribute("account", account);
				session.setMaxInactiveInterval(10);
			//		-> 아이디 정보 저장 O : Cookie에 아이디 저장
				if(rememberId != null) {
					// 검증이 끝난 데이터를 사용하는 게 맞음
					Cookie cookie = new Cookie("remember_id", account.getAccountId());
					cookie.setMaxAge(60*60*24*7);
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("remember_id", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			//		-> 홈화면 이동 : 로그인한 사용자 정보 노출
			response.sendRedirect("/");
			}
		} else {
			// 2-2. X
			//		-> 로그인 페이지 다시 요청(sendRedirect)
			response.sendRedirect("/login");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
