<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<!DOCTYPE html>
<%
	int visitCount = 1;
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null) {
	    for (Cookie c : cookies) {
	        if ("visit_count".equals(c.getName())) {
	            visitCount = Integer.parseInt(c.getValue()) + 1;
	        }
	    }
	}

	Cookie visitCountCookie = new Cookie("visit_count", String.valueOf(visitCount));
	visitCountCookie.setMaxAge(60 * 60 * 24);
	response.addCookie(visitCountCookie);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
	당신은 이 페이지를 
	<strong><%=visitCount%></strong>번 방문했습니다.
</p>
</body>
</html>