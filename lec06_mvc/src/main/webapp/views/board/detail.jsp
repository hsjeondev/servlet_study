<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gn.board.vo.Board,java.time.format.*" %>
<%
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 화면</title>
<link href='<%=request.getContextPath()%>/resources/css/board/detail.css' rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="/views/include/header.jsp" %>
	<%@ include file="/views/include/nav.jsp" %>
	<section>	
		<div id="section_wrap">
			<div class="word">
				<h3>게시글 상세 정보</h3>
			</div>
			<div class="board_detail">
				<ul>
					<li>
						<img src="<%=request.getContextPath()%>/filePath?attach_no=<%= board.getAttachNo()%>" >
						<br>
						<a href="<%=request.getContextPath()%>/fileDownload?attach_no=<%= board.getAttachNo()%>">파일 다운로드</a>
					</li>
					<li>
						<table>
							<tr>
								<td>제목</td>
								<td><%=board.getBoardTitle() %></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><%=board.getBoardContent() %></td>
							</tr>
							<tr>
								<td>작성자</td>
								<td><%=board.getMemberName() %></td>
							</tr>
							<tr>
								<td>등록일</td>
								<td><%=board.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></td>
							</tr>
							<tr>
								<td>수정일</td>
								<td><%=board.getModDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) %></td>
							</tr>
						</table>
					</li>
				</ul>
				
			</div>
			
			<div class="buttons">
				<a href="">수정</a>
				<a href="">삭제</a>			
			</div>
		</div>
	</section>
</body>
</html>