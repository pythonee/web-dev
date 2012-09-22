package com.mashup.service.impl;

import java.util.*;

import com.mashup.domain.Category;
import com.mashup.dao.ICategoryDAO;
import com.mashup.dao.impl.CategoryDAO;
import com.mashup.service.ICategoryService;

public class CategoryService implements ICategoryService
{
	
	ICategoryDAO categoryDAO;
	
	
	public List findAll()
	{
		List categoryList = categoryDAO.findAll();
		
		return categoryList;
	}
	
	public Category getCategoryById(Integer id){
		return categoryDAO.findById(id);
	}

	
	public void updateCategory(Category category){
		categoryDAO.attachDirty(category);
	}
	
	
	public void insertCategory(Category category){
		categoryDAO.save(category);
	}
	
	public void removeCategoryById(Integer id){
		categoryDAO.delete(categoryDAO.findById(id));
	}
	
	public void batchRemoveCategory(List ls){
		
	}
	public List findByFatherId(int fatherId)
	{
		return categoryDAO.findByFatherId(fatherId);
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	
	
	
}

