package com.LondenHaskins.Capstone.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserAcctCreate {
	
	@NotEmpty(message = "Email is required.")
	@Length(max = 200, message = "Email must be less than 200 characters.")
	//Work on email validation class and implementation
	//@EmailUnique(message = "Email already exists in the database.")
	private String email;
	
	@Pattern(regexp = "^[a-zA-Z0-9!@#]+$", message = "Password can only contain lowercase, uppercase, and special caracters")
	@Length(min = 6, message = "Password must be longer than 5 characters.")
	@Length(max = 25, message = "Password must be shorter than 25 characters.")
	private String password;
		
	private String firstName;
	private String lastName;
		
}