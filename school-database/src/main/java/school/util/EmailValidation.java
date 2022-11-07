package school.util;

import org.apache.commons.lang3.StringUtils;


public class EmailValidation {
	
	//HOMEWORK
	// create a new method for validating a phone number and return true or false
	// a phone number is must have 9 numbers
	// 
	
	
	public EmailValidation() {
		
	}
	
	public boolean isValidEmail(String email) {
		// 1) make sure the string has an @ in it
		// 2) make sure the string has only letters or numbers before the @ sign
		// 3) make sure the string ends with a '.' then 3 characters with no numbers
		// 4) make sure the string is not empty and is not null
		// REGULAR EXPRESSION: AcAbddd  { A = a, a = alphanumeric, b = '.', c = '@', d = alphabetical }
		
		//return true if all conditions are good otherwise false
		boolean flag = false;
		String address;
		String domain;
		String extension;
		
		if(email == null || email.isEmpty()) {
			
		}else {
			
			address = StringUtils.substringBefore(email,"@");
			domain = StringUtils.substringBetween(email,"@",".");
			extension = StringUtils.substringAfterLast(email,".");
			
		}
		
		
		return flag;
	}

}
