package com.mashup.service.impl;

import java.util.*;
import com.mashup.domain.Comment;
import com.mashup.dao.ICommentDAO;
import com.mashup.service.ICommentService;
import com.mashup.domain.Product;
public class CommentService implements ICommentService
{
	
	ICommentDAO commentDAO;
	
	
	public List findAll()
	{
		List commentList = commentDAO.findAll();
		
		return commentList;
	}
	
	public Comment getCommentById(Integer id){
		return commentDAO.findById(id);
	}

	
	public void updateComment(Comment comment){
		commentDAO.attachDirty(comment);
	}
	
	
	public void insertComment(Comment comment){
		commentDAO.save(comment);
	}
	
	public void removeCommentById(Integer id){
		commentDAO.delete(commentDAO.findById(id));
	}
	
	public void batchRemoveComment(List ls){
		
	}

	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public List getCommentByProduct(Product product){
		
		return commentDAO.findByProperty("product", product);
	}
	
	
	
}

