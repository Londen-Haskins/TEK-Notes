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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.LondenHaskins.Capstone.DAO.FriendListDAO;
import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.FriendList;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.form.FriendshipCreate;
import com.LondenHaskins.Capstone.form.UserAcctCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private FriendListDAO friendListDao;

	@Value("${spring.datasource.url}")
	private String variable;

	@RequestMapping(value = { "/user/profile"}, method = RequestMethod.GET)
	public ModelAndView userProfile(@RequestParam(required = true) Integer id) {

		ModelAndView response = new ModelAndView();
		response.setViewName("profile");

		User u = userDao.findById(id);
		
		logger.info(u.getFirstName());
		logger.info(u.getEmail());
		
		response.addObject("user", u);
		
		List<User> friendSet = new ArrayList<User>();
		List<FriendList> friends = new ArrayList<FriendList>(u.getFriends());
		for (FriendList friend : friends) {
			friendSet.add(userDao.findById(friend.getFriendId()));
		}
		response.addObject("friends", friendSet);

		return response;
	}
	
	@RequestMapping(value = { "/user/profile/add"}, method = RequestMethod.POST)
	public ModelAndView userProfile(@Valid FriendshipCreate form, BindingResult bindingResult) {

		ModelAndView response = new ModelAndView();
		response.setViewName("profile");

		User fri1 = userDao.findById(form.getUserId());
		User fri2 = userDao.findById(form.getFriendId());
				
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

		return response;
	}

}
