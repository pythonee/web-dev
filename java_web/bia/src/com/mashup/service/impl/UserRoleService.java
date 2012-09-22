package com.mashup.service.impl;

import com.mashup.dao.IUserRoleDAO;
import com.mashup.domain.UserRole;
import com.mashup.service.IUserRoleService;

public class UserRoleService implements IUserRoleService {
	IUserRoleDAO userRoleDAO;

	public void insertUserRole(UserRole userRole) {
		userRoleDAO.save(userRole);
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

}
