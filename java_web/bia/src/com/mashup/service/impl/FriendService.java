package com.mashup.service.impl;

import java.util.*;

import com.mashup.domain.Friend;
import com.mashup.domain.User;
import com.mashup.dao.IFriendDAO;
import com.mashup.service.IFriendService;
import com.mashup.dao.IUserDAO;

public class FriendService implements IFriendService
{
	
	IFriendDAO friendDAO;
	IUserDAO userDAO;
	
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List findAll()
	{
		List friendList = friendDAO.findAll();
		
		return friendList;
	}
	
	public Friend getFriendById(Integer id){
		return friendDAO.findById(id);
	}

	
	public void updateFriend(Friend friend){
		friendDAO.attachDirty(friend);
	}
	
	
	public void insertFriend(Friend friend){
		friendDAO.save(friend);
	}
	
	public void removeFriendById(Integer id){
		friendDAO.delete(friendDAO.findById(id));
	}
	
	public void batchRemoveFriend(List ls){
		
	}

	public IFriendDAO getFriendDAO() {
		return friendDAO;
	}

	public void setFriendDAO(IFriendDAO friendDAO) {
		this.friendDAO = friendDAO;
	}

	public List findByUserId(int userId) {
		User user=userDAO.findById(userId);
		return this.friendDAO.findByProperty("userByUserId", user);
	}
	public List findByPageAndUserId(int page,int userId) {
		User user=userDAO.findById(userId);
		List list=this.friendDAO.findByProperty("userByUserId", user);
		List friendList=new ArrayList();
		page++;
		for (int i = (page - 1) * 10; i < list.size()&& i < page * 10; i++) {
			friendList.add(list.get(i));
		}
		return friendList;
	}
	
	public List findByExample(Friend friend){
		return this.friendDAO.findByExample(friend);
	}

	public boolean isExsit(Friend friend){
		List list=this.friendDAO.findByProperty("userByUserId", friend.getUserByUserId());
		if(list==null||list.size()==0){
			return false;
		}
		for(int i=0;i<list.size();i++){
			Friend f=(Friend)list.get(i);
			if(friend.getUserByFriendId().getUserId()==f.getUserByFriendId().getUserId()){
				return true;
			}
		}
		return false;
		
	}


	
	
}
