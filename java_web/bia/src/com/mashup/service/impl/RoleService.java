package com.mashup.service.impl;

import java.util.List;

import com.mashup.dao.IRoleDAO;
import com.mashup.domain.Role;
import com.mashup.service.IRoleService;

public class RoleService implements IRoleService{
	
	private IRoleDAO roleDAO;
	
	/* (non-Javadoc)
	 * @see com.mashup.service.IRoleService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDAO.findAll();
	}

	/**
	 * @param roleDAO the roleDAO to set
	 */
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
