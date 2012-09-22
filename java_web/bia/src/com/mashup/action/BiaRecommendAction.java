package com.mashup.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mashup.domain.Friend;
import com.mashup.domain.Product;
import com.mashup.domain.User;
import com.mashup.resys.recommender.BiaUserFriendCollectionBaseRecommender;
import com.mashup.resys.recommender.BiaUserFriendPreferenceBaseRecommender;
import com.mashup.resys.recommender.BiaUserFriendRecommender;
import com.mashup.service.IFriendService;
import com.mashup.service.IProductService;
import com.mashup.service.IUserService;

public class BiaRecommendAction extends BaseAction {
	IFriendService friendService;
	IProductService productService;
	IUserService userService;
	BiaUserFriendCollectionBaseRecommender biaUserFriendCollectionBaseRecommender;
	BiaUserFriendPreferenceBaseRecommender biaUserFriendPreferenceBaseRecommender;
	BiaUserFriendRecommender biaUserFriendRecommender;
		
	public ActionForward userFriendPreferenceBase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<RecommendedItem> recommendList = Collections.EMPTY_LIST;

		biaUserFriendPreferenceBaseRecommender = (BiaUserFriendPreferenceBaseRecommender) getBean("BiaUserFriendPreferenceBaseRecommender");
		productService = (IProductService) getBean("ProductService");
	
		User user = getCurrentUser();
		try{
			recommendList = biaUserFriendPreferenceBaseRecommender.recommend(user.getUserId(), 10);
		}catch(NullPointerException npe){
			log.debug("没有好友或者好友没有评分");
		}

		List<Integer> randomIDs = randomSelectProduct();
		
		List<Integer> recommendIDs = new ArrayList<Integer>();
		
		for (RecommendedItem item : recommendList)
		{
			recommendIDs.add((int)item.getItemID());
		}
		
		Iterator<Integer> it = randomIDs.iterator();
		
		while (recommendIDs.size()<randomIDs.size())
		{
			while(it.hasNext()){
				Integer temp = it.next();
				if (recommendIDs.contains(temp))
				{
					randomIDs.remove(temp);
				}
				else {
					recommendIDs.add(temp);
				}
			}
			//log.debug("循环中");
		}
		
		List productList = new ArrayList();

		log.debug("============user friend preference base recommend============");
		
		for (Integer id : recommendIDs) {
			
			log.debug("we recommend item to you " + id);
			
			Product product = productService.getProductById(id);
			if (product.getProductName().length() > 10) {
				product.setProductName(product.getProductName()
						.substring(0, 10)
						+ "…");
			}
			productList.add(product);
		}

		request.setAttribute("productList", productList);
		log.debug("========================over=================================");
		return null;
	}
	
	public ActionForward userFriendCollectionBase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		List<RecommendedItem> recommendList = Collections.EMPTY_LIST;
		biaUserFriendCollectionBaseRecommender = (BiaUserFriendCollectionBaseRecommender) getBean("BiaUserFriendCollectionBaseRecommender");
		productService = (IProductService) getBean("ProductService");
		User user = getCurrentUser();	
		try{
			recommendList = biaUserFriendCollectionBaseRecommender.recommend(user.getUserId(),10);
		}catch(NullPointerException npe){
			log.debug("没有好友或者好友没有收藏");
		}
		
		List<Integer> randomIDs = randomSelectProduct();
		
		List<Integer> recommendIDs = new ArrayList<Integer>();
		
		for (RecommendedItem item : recommendList)
		{
			recommendIDs.add((int)item.getItemID());
			
		}
		
		Iterator<Integer> it = randomIDs.iterator();
		
		while (recommendIDs.size()<randomIDs.size())
		{
			while(it.hasNext()){
				Integer temp = it.next();
				if (recommendIDs.contains(temp))
				{
					randomIDs.remove(temp);
				}
				else {
					recommendIDs.add(temp);
				}
			}
		}
		
		List productList = new ArrayList();

		log.debug("============user friend collection base recommend============");
		
		for (Integer id : recommendIDs) {
			log.debug("we recommend the item to you " + id);
			Product product = productService.getProductById(id);
			if (product.getProductName().length() > 10) {
				product.setProductName(product.getProductName()
						.substring(0, 10)
						+ "…");
			}
			productList.add(product);
		}

		request.setAttribute("collectionBaseProductList", productList);
		log.debug("========================over=================================");
		return null;
	}

	//推荐好友
	public ActionForward recommendFriend(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		long[] recommendFriends=null;
		User user = getCurrentUser();	
		biaUserFriendRecommender = (BiaUserFriendRecommender) getBean("BiaUserFriendRecommender");
		userService = (IUserService)getBean("UserService");
		try{
			recommendFriends = biaUserFriendRecommender.recommend(user.getUserId(),10);
			
		}catch(NullPointerException npe){
			log.debug("没有好友可以推荐");
		}
		
		List<Integer> randomIDs = randomSelectFriend(user.getUserId());
		
		List<Integer> recommendIDs = new ArrayList<Integer>();
		
		for (long id : recommendFriends)
		{
			recommendIDs.add((int)id);
			
		}
		
		Iterator<Integer> it = randomIDs.iterator();
		
		while (recommendIDs.size()<randomIDs.size())
		{
			while(it.hasNext()){
				Integer temp = it.next();
				if (recommendIDs.contains(temp))
				{
					randomIDs.remove(temp);
				}
				else {
					recommendIDs.add(temp);
				}
			}
		}
		
		List<User> userFriendList = new ArrayList();
		log.debug("============bia user friend recommend============");

		for (int id : recommendIDs) {
			log.debug("We recommend the friend to you: " + id);
			
			User friend = userService.getUserById(id);
			if (friend.getUsername().length()>10)
			{
				friend.setUsername(friend.getUsername().substring(0,10)+"...");
			}
			userFriendList.add(friend);
			
		}
		
		request.setAttribute("recommendFriendList", userFriendList);
		
		log.debug("========================over=================================");
		return null;
	}
	
	public ActionForward historyBase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return null;
	}

	public ActionForward hotHighestScore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		DataSource dataSource = (DataSource)getBean("DataSource");
		String query = "SELECT * FROM product order by score DESC,levelClick ASC limit 10";
		Connection conn = null;
		List<Product> topItems = new ArrayList<Product>();

		try
		{
			conn = (Connection)dataSource.getConnection();
			Statement getTopItemsIdStam = conn.createStatement();
			ResultSet resultSet = getTopItemsIdStam.executeQuery(query);
			
			while (resultSet.next())
			{
				Product temp = new Product();
				temp.setProductId(resultSet.getInt(1));
				temp.setPrice(resultSet.getDouble(5));
				if (resultSet.getString(2).length() >= 10)
				{
					temp.setProductName(resultSet.getString(2).substring(0,9) + "...");
				}
				else {
					temp.setProductName(resultSet.getString(2));
				}
				
				temp.setProductImg(resultSet.getString(8));
				topItems.add(temp);
			}
			request.setAttribute("productList", topItems);
			resultSet.close();
			getTopItemsIdStam.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}finally{
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	} 
	
	//随机选择不超过10个商品
	 private List<Integer> randomSelectProduct(){
			DataSource dataSource = (DataSource)getBean("DataSource");
			String query = "SELECT productId FROM product order by RAND() limit 10";
			Connection conn = null;
			List<Integer> randomItemIDs = new ArrayList<Integer>();
				
			try
			{
				conn = (Connection)dataSource.getConnection();
				Statement getRandomItemsIdStam = conn.createStatement();
				ResultSet resultSet = getRandomItemsIdStam.executeQuery(query);
				
				while (resultSet.next())
				{
					randomItemIDs.add(resultSet.getInt(1));
				}
				resultSet.close();
				getRandomItemsIdStam.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally{
				if (conn != null) {
					try
					{
						conn.close();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			return randomItemIDs;
	   }
	 
	 //推荐好友
	 private List<Integer> randomSelectFriend(int uid){
			DataSource dataSource = (DataSource)getBean("DataSource");
			String query = "SELECT userId FROM user where userId not in (select friendId from friend as f where f.userId=?) and userId !=2 order by RAND() limit 10";
			Connection conn = null;
			List<Integer> randomItemIDs = new ArrayList<Integer>();
				
			try
			{
				conn = (Connection)dataSource.getConnection();
				PreparedStatement getRandomFriendsIdStam = conn.prepareStatement(query);
				getRandomFriendsIdStam.setInt(1, uid);
				ResultSet resultSet = getRandomFriendsIdStam.executeQuery();
				
				while (resultSet.next())
				{
					randomItemIDs.add(resultSet.getInt(1));
				}
				
				resultSet.close();
				
				getRandomFriendsIdStam.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally{
				if (conn != null) {
					try
					{
						conn.close();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			return randomItemIDs;
	   }
	 
	 
  
}
