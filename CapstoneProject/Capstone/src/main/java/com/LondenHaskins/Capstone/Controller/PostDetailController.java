package com.LondenHaskins.Capstone.Controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.PostCommentDAO;
import com.LondenHaskins.Capstone.DAO.PostDAO;
import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.Post;
import com.LondenHaskins.Capstone.Entity.PostComment;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.Security.AuthenticatedUserService;
import com.LondenHaskins.Capstone.form.PostCommentCreate;
import com.LondenHaskins.Capstone.form.PostCreate;

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
	private PostCommentDAO postCommentDao;
	
	@Autowired
	private AuthenticatedUserService authentication;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/userCtrl/postBuild"}, method = RequestMethod.POST)
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
	
	@RequestMapping(value = { "/user/profile/post"}, method = RequestMethod.GET)
	public ModelAndView viewPost(@RequestParam(required = true) Integer id) {
		
		ModelAndView response = new ModelAndView();
		response.setViewName("post");

		User curUser = new User();
		boolean commentsExist = false;
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}
		Post p = postDao.findById(id);
		
		
		logger.info("Post author: "+p.getAuthor().getFirstName());
		logger.info("Post ID: "+p.getId());
		
		response.addObject("post", p);
		response.addObject("curUser", curUser);
		
		
		List<PostComment> comments = p.getComments();
		List<String> commentNames = new ArrayList<String>();
		for (PostComment c : comments) {
			logger.info(c.getUserId()+ " commented");
			
			//Link name of user to the comment
			commentNames.add(userDao.findById(c.getUserId()).getFirstName() + " " + userDao.findById(c.getUserId()).getLastName());
		}
		
		if(!commentNames.isEmpty()) {
			commentsExist = true;
		}
		
		response.addObject("commentsExist", commentsExist);
		response.addObject("comments", comments);
		response.addObject("commentNames", commentNames);

		return response;
		
	}
	
	@RequestMapping(value = { "/userCtrl/makeComment"}, method = RequestMethod.POST)
	public ModelAndView createComment(@Valid PostCommentCreate form, BindingResult bindingResult) {

		ModelAndView response = new ModelAndView();
		response.setViewName("post");
		Post curPost = postDao.findById(form.getPostId());
		
		log.debug(form.toString());
		
		for (ObjectError e : bindingResult.getAllErrors()) {
			log.debug(e.getObjectName() + " : " + e.getDefaultMessage());
		}

		if ( ! bindingResult.hasErrors()) {
			PostComment newComment = new PostComment();
			Date date = new Date();
			Timestamp curTime = new Timestamp(date.getTime());

			newComment.setMessage(form.getMessage());
			newComment.setUserId(authentication.getCurrentUser().getId());
			newComment.setTimeCommented(curTime);
			newComment.setRefPost(curPost);
			
			try {
				curPost.getComments().add(newComment);
				postDao.save(curPost);
				Post post = postDao.findById(form.getPostId());
				response.addObject("post", post);
			}
			catch(Exception e){
				log.debug("Error saving new comment to DAO");
			}
						
		} else {
			response.addObject("bindingResult", bindingResult);
			response.addObject("form", form);
		}

		return response;
	}

}
