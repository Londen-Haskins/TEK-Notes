package com.LondenHaskins.Capstone.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {
	
	private static final String PACKAGE_NAME = "com.LondenHaskins";

	@RequestMapping(value = "/error/404")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String error404(HttpServletRequest request, Exception ex) {

		String origialUri = request.getRequestURI();
		log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  Requested URL not found : " + request.getMethod() + " " + origialUri);
	

		return "error/404";
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {
		log.warn("Error page exception : " + getRequestURL(request), ex);

		ModelAndView model = new ModelAndView("/error/500");
		model.addAllObjects(buildExceptionParameters(ex, request));

		return model;
	}
	
	private String getRequestURL(HttpServletRequest request) {
		String result = request.getRequestURL().toString();
		if (request.getQueryString() != null) {
			result = result + "?" + request.getQueryString();
		}

		return result;
	}
	
	public Map<String, Object> buildExceptionParameters(Exception ex, String message) {
		Map<String, Object> result = new HashMap<String, Object>();

		String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

		// message is the request URL if it was an error page, otherwise it can be a message 
		// from the class that calls it
		result.put("requestUrl", message);
		result.put("message", ex.getMessage());
		result.put("stackTrace", stackTrace);

		if (ex.getCause() != null) {
			result.put("rootcause", ExceptionUtils.getRootCause(ex));

			String roottrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
			result.put("roottrace", roottrace);
		}

		return result;
	}
	
	public Map<String, Object> buildExceptionParameters(Exception ex, HttpServletRequest request) {
		String message = getRequestURL(request);
		return buildExceptionParameters(ex,message);
	}

	private String getHTMLStackTrace(String[] stack) {
		StringBuffer result = new StringBuffer();
		for (String frame : stack) {
			if (frame.contains(PACKAGE_NAME)) {
				result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
			} else if (frame.contains("Caused by:")) {
				result.append("Caused By:<br>");
			}
		}

		return result.toString();
	}

}
