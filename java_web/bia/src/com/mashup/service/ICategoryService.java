package com.mashup.service;

import java.util.List;

import com.mashup.dao.ICategoryDAO;
import com.mashup.domain.Category;
public interface ICategoryService {
	public List findAll();
	
	public Category getCategoryById(Integer id);

	
	public void updateCategory(Category category);
	
	
	public void insertCategory(Category category);
	
	public void removeCategoryById(Integer id);
	
	public void batchRemoveCategory(List ls);
	
	public List findByFatherId(int fatherId);
	public ICategoryDAO getCategoryDAO();
	public void setCategoryDAO(ICategoryDAO categoryDAO) ;

}
