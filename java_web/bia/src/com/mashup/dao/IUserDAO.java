package com.mashup.dao;

import java.util.List;
import org.springframework.context.ApplicationContext;
import com.mashup.dao.impl.UserDAO;
import com.mashup.domain.Category;
import com.mashup.domain.User;

public interface IUserDAO {
	public void save(User transientInstance);
	public void delete(User persistentInstance);
	public User findById(java.lang.Integer id);
	public List findByExample(User instance);

	public List findByProperty(String propertyName, Object value);
	public List findByUsername(Object userName);
	public List findByPassword(Object password);
	public List findByEmail(Object email);
	public List findByStatus(Object status);
	public List findAll();
	public User merge(User detachedInstance);
	public void attachDirty(User instance);
}
