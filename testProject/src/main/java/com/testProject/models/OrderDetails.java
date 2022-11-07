package com.testProject.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERDETAILS")

public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;
	
	@Column(name="productCode")
    private String productCode;
	
	@Column(name="quanityOrdered")
	private Integer quanityOrdered;
	
	@Column(name="priceEach")
	private double priceEach;
	
	@Column(name="orderLineNumber")
	private Integer orderLineNumber;
	
	public OrderDetails(String productCode, int quanityOrdered, double priceEach, int orderLineNumber) {
		this.productCode = productCode;
		this.quanityOrdered = quanityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	public OrderDetails() {
		
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuanityOrdered() {
		return quanityOrdered;
	}

	public void setQuanityOrdered(Integer quanityOrdered) {
		this.quanityOrdered = quanityOrdered;
	}

	public double getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(double priceEach) {
		this.priceEach = priceEach;
	}

	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}
	
	

}