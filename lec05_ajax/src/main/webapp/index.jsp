<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기 통신</title>
<title>방명록</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<h1>JavaScript</h1>
	<a href="/jsAjaxPage">연습 화면 이동</a>
	<h1>jQeury</h1>
	<a href="/views/jquery/sample.jsp">연습 화면 이동</a>
	<h1>JSON</h1>
	<input type="button" value="조회" id="json_btn">
	<div id="result_div">
		
	</div>
	<h1>팀별 실습</h1>
	<a href="/guestBookPage">방명록 페이지</a>
	<script>
		$(function(){
			$('#json_btn').click(function() {
				$.ajax({
					url : "/accountList",
					type : "get",
					dataType : "JSON",
					success : function(data) {
						console.log(data);
						console.log(data.accountList);
						for(let i = 0; i < data.accountList.length; i++) {
							$("#result_div").append("<p>"+data.accountList[i].no+"</p>")
						}
						/* console.log(data.no+" : "+data.name); */
					}
				})
			})
		})
	</script>
</body>
</html>