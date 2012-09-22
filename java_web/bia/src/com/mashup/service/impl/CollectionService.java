package com.mashup.service.impl;

import java.util.*;

import com.mashup.domain.Collection;
import com.mashup.domain.Product;
import com.mashup.domain.User;
import com.mashup.dao.ICollectionDAO;
import com.mashup.service.ICollectionService;

public class CollectionService implements ICollectionService
{
	
	ICollectionDAO collectionDAO;
	
	
	public List findAll()
	{
		List collectionList = collectionDAO.findAll();
		
		return collectionList;
	}
	
	public Collection getCollectionById(Integer id){
		return collectionDAO.findById(id);
	}

	
	public void updateCollection(Collection collection){
		collectionDAO.attachDirty(collection);
	}
	
	
	public void insertCollection(Collection collection){
		collectionDAO.save(collection);
	}
	
	public void removeCollectionById(Integer id){
		collectionDAO.delete(collectionDAO.findById(id));
	}
	public List getCollectionTopList(){
		List list=collectionDAO.getCollectionTopList();
		ArrayList alist=new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Product product=((Collection)list.get(i)).getProduct();
			String s=product.getProductName();
			if(s.length()>8){
				s=s.substring(0, 8)+"…";
				product.setProductName(s);
			}
			alist.add(product);
		}
		return alist;
	}
	public void batchRemoveCollection(List ls){
		
	}

	public ICollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	public void setCollectionDAO(ICollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}

	public List<Collection> findByUserId(int userId) {
		return this.collectionDAO.findByProperty("userId", userId);
	}
	
	public List<Integer> findItemIdsByUserId(int userId){
		
		List<Collection> collectionLs = findByUserId(userId);
		List<Integer> itemIDList = new ArrayList<Integer>();
		
		for (Collection collection : collectionLs) {
			itemIDList.add(collection.getProduct().getProductId());
		}
		
		return itemIDList;
	}
	
	
}

