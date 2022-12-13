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
public class PostCreate {
	
	@NotEmpty(message = "Post content is rquired")
	@Length(max = 500, min = 1, message = "Content must be less than 500 characters.")
	private String contentText;
	
}
