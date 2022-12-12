package com.LondenHaskins.Capstone.Security;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.LondenHaskins.Capstone.DAO.UserDAO;
import com.LondenHaskins.Capstone.Entity.User;

@Service
public class AuthenticatedUserService {

	@Autowired
	private UserDAO userDao;

	// added @Lazy to this to prevent a circular loading reference in component scan
	// https://stackoverflow.com/questions/65807838/spring-authenticationmanager-and-circular-dependency
	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public User getCurrentUser() {
		User user = userDao.findByEmail(getCurrentUsername());
		return user;
	}

	public String getCurrentUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null) {
			final org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal();
			return principal.getUsername();
		} else {
			return null;
		}
	}
	
	
	

	public boolean isUserInRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null) {
			Collection<? extends GrantedAuthority> authorities = context.getAuthentication().getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(role)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}

		return (authentication != null && authentication.isAuthenticated());
	}
	
}
