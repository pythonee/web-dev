package com.mashup.service;

import java.util.List;

import com.mashup.dao.ICollectionDAO;
import com.mashup.domain.Collection;

public interface ICollectionService {
	public List findAll();
	
	public Collection getCollectionById(Integer id);
	
	public void updateCollection(Collection collection);
	
	public List<Integer> findItemIdsByUserId(int userId);
	
	public void insertCollection(Collection collection);
	
	public void removeCollectionById(Integer id);
	
	public void batchRemoveCollection(List ls);
	
	public List findByUserId(int userId);
	public List getCollectionTopList();
	public ICollectionDAO getCollectionDAO();

	public void setCollectionDAO(ICollectionDAO collectionDAO) ;
}
