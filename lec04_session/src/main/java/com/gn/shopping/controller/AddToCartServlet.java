package com.gn.shopping.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.shopping.vo.Product;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String className = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://127.0.0.1:3306/shopping_project";
    private static final String id = "scott";
    private static final String pwd = "tiger";
       
    public AddToCartServlet() {}

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectProductCode = request.getParameter("product");
        List<Product> products = (List<Product>) request.getSession().getAttribute("cart");
        
        if (products == null) {
            products = new ArrayList<Product>();
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT prod_code, prod_name, prod_price "
                + "FROM sh_product "
                + "WHERE prod_code = ?";
        
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url, id, pwd);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, selectProductCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int prodCode = rs.getInt("prod_code");
                String prodName = rs.getString("prod_name");
                int prodPrice = rs.getInt("prod_price");
                Product product = new Product(prodCode, prodName, prodPrice);
                if (!products.contains(product)) {
                    products.add(product);
                    request.getSession().setAttribute("cart", products);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        response.sendRedirect("/views/shopping/cart.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }
}
