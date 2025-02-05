package com.gn.controller.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/joinMember")
public class GetSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetSendServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. text 타입
		String userName = request.getParameter("userName");
		// 2. radio 타입
		String userGender = request.getParameter("userGender").equals("1") ? "남성" : request.getParameter("userGender").equals("2") ? "여성" : "성별 입력 안 함";
		// 3. number 타입
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		// 4. checkbox 타입
		String[] hobbyValues = request.getParameterValues("hobby");
		String hobby = "";
		if(hobbyValues != null && hobbyValues.length > 0) {
			String[] hobbyNames = new String[hobbyValues.length];
			for(int i = 0; i < hobbyValues.length; i++) {
				String val = hobbyValues[i];
				if(val.equals("1")) {
					hobbyNames[i] = "야구";
				} else if(val.equals("2")) {
					hobbyNames[i] = "축구";
				} else if(val.equals("3")) {
					hobbyNames[i] = "농구";
				}
			}
			hobby = String.join(", ", hobbyNames);
		} else {
			hobby = "취미 없음";
		}
		
		
		System.out.println("이름 : " + userName);
		System.out.println("성별 : " + userGender);
		System.out.println("나이 : " + userAge);
		System.out.println("취미 : " + hobby);
		
		// 1. 출력할 문서 형태 선언
		response.setContentType("text/html; charset=UTF-8");
		// 2. 터널(스트림)
		PrintWriter out = response.getWriter();
		// 3. 스트림을 통해 HTML 구문을 한줄씩 출력
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<title>회원가입 결과화면</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"+userName+"님, 환영합니다</h1>");
		out.println("<h2>앞으로도 자주 와주실꺼죠?</h2>");
		out.println("<a href='/'>홈페이지로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		// 4. 터널 문닫기
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
