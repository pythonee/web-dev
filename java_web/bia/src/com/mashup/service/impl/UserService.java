package com.mashup.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math.random.JDKRandomGenerator;
import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
import org.apache.log4j.Logger;
import org.springframework.security.providers.encoding.PasswordEncoder;

import com.mashup.dao.IUserDAO;
import com.mashup.dao.IUserRoleDAO;
import com.mashup.domain.Role;
import com.mashup.domain.User;
import com.mashup.domain.UserRole;
import com.mashup.service.IUserService;
import com.mashup.utils.VelocityMailSupport;

public class UserService implements IUserService {

	IUserDAO userDAO;
	IUserRoleDAO userRoleDAO;
	VelocityMailSupport activateMailSupport;
	VelocityMailSupport findPasswordSupport;
	PasswordEncoder md5Encoder;
	Logger log=Logger.getLogger(UserService.class);

	public void login() {
		// TODO Auto-generated method stub

	}

	public List<User> findAll() {
		List userList = userDAO.findAll();

		return userList;
	}

	public User getUserById(Integer id) {
		return userDAO.findById(id);
	}

	public void updateUser(User user) {
		userDAO.attachDirty(user);
	}

	public void insertUser(User user) {
		userDAO.save(user);
	}

	public void removeUserById(Integer id) {
		userDAO.delete(userDAO.findById(id));
	}

	public void batchRemoveUser(String[] ids) {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++)
				removeUserById(Integer.parseInt(ids[i]));
		}
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mashup.service.IUserService#forgotPasswd()
	 */
	public void forgotPasswd(User user) {
		Map model = new HashMap();
		model.put("username", user.getUsername());

		RandomData randomer = new RandomDataImpl(new JDKRandomGenerator());

		char[] newPassword = new char[12];

		for (int i = 0; i < 12; i++) {
			newPassword[i] = (char) randomer.nextInt(33, 126);
		}

		String str = new String(newPassword);
		model.put("password", str);
		log.debug(str +" "+user.getUsername());
		user.setPassword(md5Encoder.encodePassword(str, user.getUsername()));

		userDAO.attachDirty(user);

		findPasswordSupport
				.sendMime(user.getUsername(), user.getEmail(), model);
	}

	public void sendActivateMail(User user, String passString) {
		Map model = new HashMap();
		model.put("username", user.getUsername());

		String url = "http://localhost:8080/bia/activateUser.do?method=activateUser&userId="
				+ user.getUserId() + "&pass=" + passString;
		model.put("url", url);

		activateMailSupport
				.sendMime(user.getUsername(), user.getEmail(), model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mashup.service.IUserService#logoff()
	 */
	public void logoff() {
		// TODO Auto-generated method stub

	}

	public List findByExample(User instance) {
		return this.userDAO.findByExample(instance);
	}

	public VelocityMailSupport getActivateMailSupport() {
		return activateMailSupport;
	}

	public void setActivateMailSupport(VelocityMailSupport activateMailSupport) {
		this.activateMailSupport = activateMailSupport;
	}

	public VelocityMailSupport getFindPasswordSupport() {
		return findPasswordSupport;
	}

	public void setFindPasswordSupport(VelocityMailSupport findPasswordSupport) {
		this.findPasswordSupport = findPasswordSupport;
	}

	public void addUserRole(User user, Role role) {
		UserRole userRole = new UserRole();

		userRole.setRole(role);
		userRole.setUser(user);

		userRoleDAO.save(userRole);
	}

	public IUserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	public void setUserRoleDAO(IUserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	/**
	 * @return the md5Encoder
	 */
	public PasswordEncoder getMd5Encoder() {
		return md5Encoder;
	}

	/**
	 * @param md5Encoder
	 *            the md5Encoder to set
	 */
	public void setMd5Encoder(PasswordEncoder md5Encoder) {
		this.md5Encoder = md5Encoder;
	}

}
