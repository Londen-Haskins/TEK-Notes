package com.LondenHaskins.Capstone.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import com.LondenHaskins.Capstone.Entity.UserRole;
import com.LondenHaskins.Capstone.Security.AuthenticatedUserService;
import com.LondenHaskins.Capstone.form.PostCreate;
import com.LondenHaskins.Capstone.form.UserAcctCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PostDetailController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private AuthenticatedUserService authentication;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/user/profile/makePost"}, method = RequestMethod.POST)
	public ModelAndView createPost(@Valid PostCreate form, BindingResult bindingResult) {

		ModelAndView response = new ModelAndView();
		response.setViewName("post");
		
		log.debug(form.toString());
		
		for (ObjectError e : bindingResult.getAllErrors()) {
			log.debug(e.getObjectName() + " : " + e.getDefaultMessage());
		}

		if ( ! bindingResult.hasErrors()) {
			Post newPost = new Post();

			newPost.setContentText(form.getContentText());
			newPost.setAuthor(authentication.getCurrentUser());
			
			Date date = new Date();
			Timestamp curTime = new Timestamp(date.getTime());
			newPost.setTimePosted(curTime);
			
			try {
				postDao.save(newPost);
				response.addObject("post", newPost);
			}
			catch(Exception e){
				log.debug("Error saving new post to DAO");
			}
						
		} else {
			response.addObject("bindingResult", bindingResult);
			response.addObject("form", form);
		}

		return response;
	}

}