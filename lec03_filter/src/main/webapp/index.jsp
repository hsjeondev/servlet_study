<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Filter, Listener 학습</title>
</head>
<body>
	<h1>필터 테스트 하기</h1>
	<h2>(1) 어노테이션 방식</h2>
	<form action="/receive/data" method="post">
		<input type="text" name="test_data">
		<button>데이터 전송</button>
	</form>
	<h2>(2) web, xml방식</h2>
	<form action="/receive/msg" method="post">
		<fieldset>
			<legend>메시지 작성</legend>
			<textarea cols="20" rows="3" name="msg"></textarea>
			<input type="submit" value="보내기">
		</fieldset>
	</form>
	<h2>실습 문제</h2>
	<form action="/receive/upper" method="post">
		<fieldset>
			<legend>대문자로 변환할 문자 입력</legend>
			<textarea cols="10" rows="3" name="upperMsg"></textarea>
			<button>변환</button>
		</fieldset>
	</form>
</body>
</html>