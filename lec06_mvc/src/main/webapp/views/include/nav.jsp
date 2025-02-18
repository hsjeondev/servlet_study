<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gn.member.vo.Member" %>
<%	Member member = (Member)session.getAttribute("member"); %>
<link href="<%=request.getContextPath()%>/resources/css/include/nav.css" rel="stylesheet" type="text/css">
<nav>
	<div id="nav_wrap">
		<div class="menu">
			<ul>
				<li>
					<a href="/boardList">게시판</a>
				</li>
			<% if(member == null) {%>
				<li>
					<a href="/memberLogin">로그인</a>
				</li>
				<li>
					<a href="/memberCreate">회원가입</a>
				</li>
			<%} else {%>
				<li>
					<a href="/boardCreate">게시글 등록</a>
				</li>
				<li>
					<a href="/memberLogout">로그아웃</a>
				</li>
				<li>					
					<a href="/memberUpdate">계정수정</a>
				</li>
			<%} %>
			</ul>
		</div>
	</div>
</nav>