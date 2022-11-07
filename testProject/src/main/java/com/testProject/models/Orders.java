package com.testProject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Orders {
	
	// these 2 annotations will got on primary key fields in each entity
	// they are boilerplate code and can be copied from one entity to the next
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNumber;
	
	// the @Column represents the column name in the database
	// and maps the database name to the java variable name
	// in this case they are both the same
	@Column(name = "orderDate")
	private Date orderDate;
	
	@Column(name = "requiredDate")
	private Date requiredDate;
	
	@Column(name = "shippedDate")
	private Date shippedDate;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "comments", columnDefinition = "TEXT")
	private String comments;
	
	@Column(name = "customerNumber")
	private Integer customerNumber;

}
