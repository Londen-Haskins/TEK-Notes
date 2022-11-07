package com.testProject.models;

import java.awt.print.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customers {

	@Id
	@Column(name = "customerNumber")
	private Integer id;
	
	@Column(name = "customerName")
	private String name;
	
	@Column(name = "contactLastName")
	private String lastName;
	
	@Column(name = "contactFirstName")
	private String firstName;
	
//	@Column(name = "salesRepEmployeeNumber", insert="false" update="false")
//	private Integer salesRepId;
	
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    // this is the actual column name in the table we are going to reference
    // in this case it is the employee table primary key which is employeeNumber
    @JoinColumn(name = "salesRepEmployeeNumber", nullable = false)
    private Employee salesRep;
	
}