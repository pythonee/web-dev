package com.mashup.service;

import java.util.ArrayList;
import java.util.List;

import com.mashup.dao.IFriendDAO;
import com.mashup.dao.IUserDAO;
import com.mashup.domain.Friend;

public interface IFriendService {
	public List findAll();
	public Friend getFriendById(Integer id);
	
	public void updateFriend(Friend friend);
	
	
	public void insertFriend(Friend friend);
	
	public void removeFriendById(Integer id);
	
	public void batchRemoveFriend(List ls);
	public List findByUserId(int userId);
	public List findByPageAndUserId(int page,int userId);
	public IFriendDAO getFriendDAO();
	public void setFriendDAO(IFriendDAO friendDAO);
	public IUserDAO getUserDAO() ;

	public void setUserDAO(IUserDAO userDAO);
	
	public List findByExample(Friend friend);
	
	public boolean isExsit(Friend friend);
}
