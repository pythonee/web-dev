package com.mashup.action;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.struts.DispatchActionSupport;
import org.apache.log4j.*;
import org.springframework.security.context.SecurityContextHolder;

import com.mashup.dao.IUserDAO;
import com.mashup.domain.User;
import com.mashup.service.IUserService;

import org.springframework.security.userdetails.UserDetails;

public class BaseAction extends DispatchActionSupport
{
	protected static Logger log;
	private IUserService userService;
	private final static int DEFAULT_USER_ID = 2;
	public BaseAction(){
		log = Logger.getLogger(this.getClass().getName());
	}
	
	public Object getBean(String beanName)
	{
		return getWebApplicationContext().getBean(beanName);
	}
	
	
	public User getCurrentUser() {
		try
		{
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			return (User)userDetails;
		} catch (Exception e)
		{
			return null;
		}

	}
	
	public User getDefaultUser(){
		userService = (IUserService)getBean("UserService");
		return userService.getUserById(DEFAULT_USER_ID);				
	}
	
	public Timestamp getCurrTime(){
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		return new Timestamp(new java.sql.Date(date.getTime()).getTime());
	}
	
	public Logger getLog(){
		return log;
	}
}
