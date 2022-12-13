package com.LondenHaskins.Capstone.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.DAO.UserRoleDAO;
import com.LondenHaskins.Capstone.Entity.User;
import com.LondenHaskins.Capstone.Entity.UserRole;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserRoleDAO userRoleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// this class is used by spring security to fetch the user from the database
		// and create the user roles

		User user = userDao.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username '" + username + "' not found in database");
		}

		List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());

		// setup user roles
		Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

		boolean accountIsEnabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, springRoles);
	}

	private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getRolePosition()));
		}

		return authorities;
	}

}