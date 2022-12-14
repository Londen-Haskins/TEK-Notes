package com.LondenHaskins.Capstone.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCommentCreate {

	@NotNull
	private Integer userId;
	
	@NotEmpty(message = "Comment message is required")
	@Length(max = 200, min = 1, message = "Content must be less than 200 characters.")
	private String message;
	
	@NotNull
	private Integer postId;
	
}
