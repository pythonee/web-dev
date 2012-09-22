package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Category;
import com.mashup.domain.Comment;

public interface ICommentDAO {
	public void save(Comment transientInstance);
	public void delete(Comment persistentInstance);

	public Comment findById(java.lang.Integer id);

	public List findByExample(Comment instance);

	public List findByProperty(String propertyName, Object value);

	public List findByCommentContent(Object commentStr);

	public List findAll();
	public void attachDirty(Comment instance);
}
