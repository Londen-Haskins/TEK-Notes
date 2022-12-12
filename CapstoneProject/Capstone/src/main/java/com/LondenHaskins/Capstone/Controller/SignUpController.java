package com.LondenHaskins.Capstone.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.DAO.UserRoleDAO;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.Entity.UserRole;
import com.LondenHaskins.Capstone.form.UserAcctCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SignUpController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private UserRoleDAO userRoleDao;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private PasswordEncoder passwordEncoder;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/signup"}, method = RequestMethod.GET)
	public ModelAndView signUp() {
		ModelAndView response = new ModelAndView();
		response.setViewName("signUp");

		
		
		return response;
	}
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public ModelAndView UserCreateSubmit(@Valid UserAcctCreate form, BindingResult bindingResult) {
		ModelAndView response = new ModelAndView();
		response.setViewName("signUp");

		log.debug(form.toString());
		
		for (ObjectError e : bindingResult.getAllErrors()) {
			log.debug(e.getObjectName() + " : " + e.getDefaultMessage());
		}

		if ( ! bindingResult.hasErrors()) {
			User user = new User();
			
			String encodedPassword = passwordEncoder.encode(form.getPassword());
			user.setPassword(encodedPassword);

			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			user.setEmail(form.getEmail());

			userDao.save(user);
			
			UserRole ur = new UserRole();
			ur.setRolePosition("USER");
			ur.setUserId(user.getId());
			userRoleDao .save(ur);
			
			
			
		} else {
			response.addObject("bindingResult", bindingResult);
			response.addObject("form", form);
		}

		return response;
	}

}
