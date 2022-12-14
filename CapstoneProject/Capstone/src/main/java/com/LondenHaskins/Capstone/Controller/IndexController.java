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
import com.LondenHaskins.Capstone.form.UserAcctCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private AuthenticatedUserService authentication;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView slash() {

		ModelAndView response = new ModelAndView();
		response.setViewName("index");
		User curUser = new User();
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}

		List<Post> posts = postDao.getAllPosts();
		List<User> users = userDao.getAllUsers();
		for (User u : users) {
			logger.info(u.getFirstName());
			logger.info(u.getEmail());
		}
		for (Post p : posts) {
			logger.info(p.getAuthor().getFirstName() + "Post displayed");
		}
		response.addObject("curUser", curUser);
		response.addObject("users", users);
		response.addObject("posts", posts);

		return response;
	}
	
}
