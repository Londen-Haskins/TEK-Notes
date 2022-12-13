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
public class FriendshipCreate {
	
	private Integer userId;
	
	private Integer friendId;

}
