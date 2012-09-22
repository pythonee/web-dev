package com.mashup.service;

import com.mashup.dao.IUserRoleDAO;
import com.mashup.domain.UserRole;

public interface IUserRoleService {
	public void insertUserRole(UserRole userRole);

	public IUserRoleDAO getUserRoleDAO();

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO);
}
