package com.mashup.service;

import java.util.List;

import com.mashup.domain.Comment;
import com.mashup.domain.Product;

public interface ICommentService {
	public List findAll();
	
	public Comment getCommentById(Integer id);
	
	public void updateComment(Comment comment);
	
	public void insertComment(Comment comment);
	
	public void removeCommentById(Integer id);
	
	public void batchRemoveComment(List ls);
	public List getCommentByProduct(Product product);
}
