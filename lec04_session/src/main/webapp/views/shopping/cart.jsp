<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.gn.shopping.vo.Product" %>
<%
	@SuppressWarnings("unchecked")	
    List<Product> products = (List<Product>) session.getAttribute("cart");
    
    if (products == null) {
        products = new ArrayList<Product>();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
</head>
<body>
    <% if (products != null && !products.isEmpty()) { %>
            <% for (Product p : products) { %>
            <ul>
                <li>제품 코드 : <%= p.getProdCode() %></li>
                <li>제품 이름 : <%= p.getProdName() %></li>
                <li>제품 가격 : <%= p.getProdPrice() %></li>
            </ul>
            <br>
            <% } %>
    <% } else { %>
        <p>장바구니가 비어있습니다.</p>
    <% } %>
</body>
</html>
