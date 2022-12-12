package com.LondenHaskins.Capstone.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView response = new ModelAndView();
		response.setViewName("login");
		return response;
	}
		
}
