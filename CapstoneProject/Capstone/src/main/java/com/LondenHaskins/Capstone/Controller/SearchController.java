package com.LondenHaskins.Capstone.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.PostDAO;
import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.Post;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.Security.AuthenticatedUserService;
import com.LondenHaskins.Capstone.form.PostCreate;
import com.LondenHaskins.Capstone.form.UserAcctCreate;
import com.LondenHaskins.Capstone.form.UserSearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private AuthenticatedUserService authentication;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/listing"}, method = RequestMethod.GET)
	public ModelAndView listUser() {

		ModelAndView response = new ModelAndView();
		response.setViewName("search");
		User curUser = new User();
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}

		List<User> users = userDao.getAllUsers();
		for (User u : users) {
			logger.info(u.getFirstName());
			logger.info(u.getEmail());
		}
		
		response.addObject("curUser", curUser);
		response.addObject("users", users);

		return response;
	}
	
	@RequestMapping(value = { "/search"}, method = RequestMethod.GET)
	public ModelAndView search(@Valid UserSearch form, BindingResult bindingResult) {

		ModelAndView response = new ModelAndView();
		response.setViewName("search");
		User curUser = new User();
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}

		List<User> users = userDao.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrderByLastNameAsc(form.getName(), form.getName());
		for (User u : users) {
			logger.info(u.getFirstName());
			logger.info(u.getEmail());
		}
		
		response.addObject("curUser", curUser);
		response.addObject("users", users);

		return response;
	}
	
}
