package com.LondenHaskins.Capstone.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.FriendListDAO;
import com.LondenHaskins.Capstone.DAO.PostDAO;
import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.FriendList;
import com.LondenHaskins.Capstone.Entity.Post;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.Security.AuthenticatedUserService;
import com.LondenHaskins.Capstone.form.FriendshipCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private AuthenticatedUserService authentication;
	
	@Autowired
	private FriendListDAO friendListDao;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/user/profile"}, method = RequestMethod.GET)
	public ModelAndView userProfile(@RequestParam(required = true) Integer id) {

		ModelAndView response = new ModelAndView();
		response.setViewName("profile");

		User curUser = new User();
		boolean commentsExist = false;
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}
		
		User u = userDao.findById(id);
		
		logger.info(u.getFirstName());
		logger.info(u.getEmail());
		
		response.addObject("user", u);
		
		List<Post> posts = postDao.getAllPostsFrom(u);
		for (Post p : posts) {
			logger.info(p.getAuthor().getFirstName() + "Post displayed");
		}
		
		List<User> friendSet = new ArrayList<User>();
		List<FriendList> friends = new ArrayList<FriendList>(u.getFriends());
		for (FriendList friend : friends) {
			friendSet.add(userDao.findById(friend.getFriendId()));
		}
		response.addObject("curUser", curUser);
		response.addObject("friends", friendSet);
		response.addObject("posts", posts);

		return response;
	}
	
	@RequestMapping(value = { "/userCtrl/myProfile"}, method = RequestMethod.GET)
	public ModelAndView myProfile() {

		ModelAndView response = new ModelAndView();
		response.setViewName("profile");

		User curUser = new User();
		boolean commentsExist = false;
		
		if(authentication.isAuthenticated()) {
			curUser = authentication.getCurrentUser();
		}
				
		logger.info(curUser.getFirstName());
		logger.info(curUser.getEmail());
		
		response.addObject("user", curUser);
		
		List<Post> posts = postDao.getAllPostsFrom(curUser);
		for (Post p : posts) {
			logger.info(p.getAuthor().getFirstName() + "Post displayed");
		}
		
		List<User> friendSet = new ArrayList<User>();
		List<FriendList> friends = new ArrayList<FriendList>(curUser.getFriends());
		for (FriendList friend : friends) {
			friendSet.add(userDao.findById(friend.getFriendId()));
		}
		response.addObject("curUser", curUser);
		response.addObject("friends", friendSet);
		response.addObject("posts", posts);

		return response;
	}
	
	@RequestMapping(value = { "/userCtrl/add"}, method = RequestMethod.POST)
	public ModelAndView userProfile(@Valid FriendshipCreate form, BindingResult bindingResult) {

		ModelAndView response = new ModelAndView();
		response.setViewName("profile");

		Boolean alreadyFriend = false;
		User fri1 = userDao.findById(form.getUserId());
		User fri2 = userDao.findById(form.getFriendId());
		
		//Test if the user tried to add themselves
		if(fri1.getId() == fri2.getId()) {
			logger.info("Cannot add yourself as a friend");
			return response;
		}
		
		//Test if the user and possible friend are already friends
		List<FriendList>currentList = new ArrayList<FriendList>(fri1.getFriends());
		for (FriendList fl : currentList) {
			logger.info("Friend of user "+fl.getUserId()+" is user "+fl.getFriendId());
			if(fl.getFriendId() == fri2.getId()) {
				alreadyFriend = true;
				logger.info("These users are already friends");
			}
		}
				
		//If the users are not already friends
		if(!alreadyFriend) {
			
			FriendList friendship = new FriendList();
			FriendList friendship2 = new FriendList();
			
			friendship.setUserId(fri1.getId());
			friendship.setUser(fri1);
			friendship.setFriendId(fri2.getId());
			friendship2.setUserId(fri2.getId());
			friendship2.setUser(fri2);
			friendship2.setFriendId(fri1.getId());

			fri2.getFriends().add(friendship);
			fri2.getFriends().add(friendship2);
			
			friendListDao.save(friendship);
			friendListDao.save(friendship2);
			userDao.save(fri1);
			userDao.save(fri2);
			
			logger.info("Friendship between "+fri1.getFirstName()+" and "+fri2.getFirstName()+" is submitted");
			
		}
		

		return response;
	}

}
