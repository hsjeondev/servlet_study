<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키, 세션</title>
</head>
<body>
	<%if(session.isNew() || session.getAttribute("account") == null) {%>
		<a href="/login">로그인</a>
	<%} else {%>
		<a href="/logout">로그아웃</a>
	<%} %>
	<h1>쿠키 연습하기</h1>
	<ul>
		<li>
			<a href="/createCookie">생성하기</a>
		</li>
		<li>
			<% 
			String userId = "쿠키없음";
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if("user_id".equals(cookie.getName())) {
						userId = cookie.getValue();
					}
				}
			}
			%>
			아이디 : <%=userId %>
		</li>
		<li>
			<a href="/editCookie">수정하기</a>
		</li>
		<li>
			<a href="/removeCookie">삭제하기</a>
		</li>
		<li>
			<a href="/changePage">화면 전환</a>
		</li>
	</ul>
	<h2>세션 연습하기</h2>
	<ol>
		<li>
			<a href="/createSession">
				생성하기
			</a>
		</li>
		<li>
			<%
				String memberId = "세션 없음";
				if(session.getAttribute("member_id") != null) {
					memberId = (String)session.getAttribute("member_id");
				}
			%>
			<%=memberId %>
		</li>
	</ol>
	<hr>
	<h1>장바구니 담기</h1>
	<a href="/productList">상품목록</a>
	<a href="/carList">장바구니</a>
</body>
</html>