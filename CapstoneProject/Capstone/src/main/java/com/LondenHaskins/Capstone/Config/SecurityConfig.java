package com.LondenHaskins.Capstone.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
	        .authorizeRequests()
	        	// URL of views that any client can access
	        	.antMatchers("/user/**", "/","/error/**","/social.css").permitAll()
	        	// Restrict full access of application to users only
				.anyRequest().authenticated()
	        	.and()
	        .formLogin()
	            .loginPage("/user/login")
	            .loginProcessingUrl("/user/loginuser")
	            //url route if user hasn't requested a secure path
	            .defaultSuccessUrl("/")
	            //Default login failure url
	            .failureUrl("/")
	            .and()
	        .logout()
	            .invalidateHttpSession(true)
	            // this is the URL to log a user out
	            .logoutUrl("/user/logout")
	            // this is the URL to send the browser to after the user has logged out
	            .logoutSuccessUrl("/");
		

	}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}