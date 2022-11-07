package com.testProject.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="employees")
public class Employee {
	
	@Id
	@Column(name = "employeeNumber")
	private Integer employeeNumber;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "extension")
	private String extension;
	
	@Column(name = "officeCode")
	private String officeCode;
	
	// the mapped by value is actually the name of the java variable in the 
	// custoemr object that we are linking too
    @OneToMany(mappedBy = "salesRep", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Customers> customers;
}