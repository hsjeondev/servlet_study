<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.gn.board.vo.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.getAttribute("posts");
	request.getAttribute("paging");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="/resources/css/board/list.css" rel="stylesheet" type="text/css">
<link href="/resources/css/include/paging.css" rel="stylesheet" type="text/css">
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.js"></script> --%>
<script src="<c:url value='/resuorces/js/jquery-3.7.1.js'/>"></script>
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>
		<div id="section_wrap">
			<div class="search">
				<form action="/boardList" name="search_board_form" method="get">
					<input type="text" name="board_title" placeholder="검색하고자 하는 게시글 제목을 입력하세요."
					value="${paging.boardTitle}">
					<input type="submit" value="검색">
				</form>	
			</div>
			<div class="word">
				<h3>게시글 목록</h3>
			</div><br>
			<div class="board_list">
				<table>
					<colgroup>
						<col width="10%">
						<col width="50%">
						<col width="20%">
						<col width="20%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${not empty posts }">
							<c:forEach var="post" items="${posts }" varStatus="vs">
								<tr data-board-no="${post.boardNo }">
									<td>${(paging.nowPage - 1) * paging.numPerPage + (vs.index+1) }</td>
									<td>${post.boardTitle}</td>
									<td>${post.memberName}</td>
									<%-- <td>${post.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td> --%>
										<fmt:parseDate value="${post.regDate }" pattern="yyyy-MM-dd'T'HH:mm:ss" var="strRedDate" />
									<td>
										<fmt:formatDate value="${strRedDate}" pattern="yy-MM-dd HH:mm" />
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty posts }">
							<tr>
								<td colSpan="4">데이터가 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<c:if test="${not empty paging }">
		<div class="center">
			<div class="pagination">
				<c:if test="${not empty paging.boardTitle}">
    				<c:set var="boardTitleParam" value="${'&board_title='}${paging.boardTitle}" />
				</c:if>
				<c:if test="${paging.prev}">
				<c:url var="testUrl" value="/boardList">
					<c:param name="nowPage" value="${paging.pageBarStart - 1}${boardTitleParam}"/>
				</c:url>
				    <a href="${testUrl}">&laquo;</a>
				</c:if>
				<c:forEach var="i" begin="${paging.pageBarStart}" end="${paging.pageBarEnd}">
				    <a href="/boardList?nowPage=${i}${boardTitleParam}">${i}</a>
				</c:forEach>
				<c:if test="${paging.next}">
				    <a href="/boardList?nowPage=${paging.pageBarEnd + 1}${boardTitleParam}">&raquo;</a>
				</c:if>
			</div>
		</div>
	</c:if>
	<script>
		$('.board_list tbody tr').on('click', function() {
			const boardNo = $(this).data('board-no');
			location.href='/boardDetail?board_no='+boardNo;
		})
	</script>
</body>
</html>