<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String upperMsg = (String)request.getAttribute("upperMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메시지 확인</title>
</head>
<body>
	<h1>메시지</h1>
	<p>
		<%=msg %>
	</p>
	<h1>대문자 변환 메시지</h1>
	<p>
		<%=upperMsg %>
	</p>
</body>
</html>