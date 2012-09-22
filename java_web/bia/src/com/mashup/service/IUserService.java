package com.mashup.service;

import java.util.List;

import com.mashup.dao.IUserDAO;
import com.mashup.domain.Role;
import com.mashup.domain.User;

public interface IUserService {

	public List findAll();

	public User getUserById(Integer id);

	public void updateUser(User user);

	public void insertUser(User user);

	public void removeUserById(Integer id);

	public void batchRemoveUser(String[] ls);

	public void login();

	public void logoff();

	public List findByExample(User instance);

	public void forgotPasswd(User user);

	public void sendActivateMail(User user, String passString);

	public IUserDAO getUserDAO();

	public void setUserDAO(IUserDAO userDAO);

	public void addUserRole(User user, Role role);

	// TODO : 用户验证部分
}
