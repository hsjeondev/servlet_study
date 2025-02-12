package com.gn.shopping.vo;

import java.util.Objects;

public class Product {

	private int prodCode;
	private String prodName;
	private int prodPrice;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(int prodCode, String prodName, int prodPrice) {
		super();
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}

	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	@Override
	public String toString() {
		return "Product [prodCode=" + prodCode + ", prodName=" + prodName + ", prodPrice=" + prodPrice + "]";
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return prodCode == product.prodCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodCode);
    }
	
}
