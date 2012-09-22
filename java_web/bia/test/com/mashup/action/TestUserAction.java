package com.mashup.action;

import org.easymock.MockControl;
import org.junit.After;
import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.mashup.dao.IUserDAO;
import com.mashup.service.IUserService;
import com.mashup.service.impl.UserService;
import com.mashup.base.*;

public class TestUserAction extends BaseDependencyInjectionTests {

	MockHttpServletRequest requestMock;
	MockHttpServletResponse responseMock;
	UserService userService;
	UserAction userAction;

	public UserService getUserService() {
		return null;
	}
}