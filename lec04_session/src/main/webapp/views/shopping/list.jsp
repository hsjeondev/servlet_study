<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gn.shopping.vo.Product" %>

<%
	@SuppressWarnings("unchecked")
	List<Product> products = (List<Product>)request.getAttribute("resultList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="productForm" action="/addToCart" method="post">
		<select name="product">
			<% for(Product p : products) { %>
				<option value="<%=p.getProdCode() %>"><%=p.getProdName() %></option>
				<%}%>
		</select>
		<a href="#" id="submitTag">추가</a>
	</form>
	<script>
		document.getElementById("submitTag").addEventListener("click", function(e) {
			e.preventDefault();
			document.getElementById("productForm").submit();
		})
	</script>
</body>
</html>