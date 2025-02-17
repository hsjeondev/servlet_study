<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.gn.board.vo.Board" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
	@SuppressWarnings("unchecked")
	List<Board> list = (List<Board>)request.getAttribute("posts");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="/resources/css/board/list.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>
		<div id="section_wrap">
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
						<%if(!list.isEmpty()) {%>
							<%for(Board board : list) {%>
							<tr>
								<td><%=board.getBoardNo() %></td>
								<td><%=board.getBoardTitle() %></td>
								<td><%=board.getMemberName() %></td>
								<td><%= board.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></td>
							</tr>
						<%}%>
						<%} else {%>
							<tr>
								<td rowSpan="4">데이터가 없습니다.</td>
							</tr>
						<%} %>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
</html>