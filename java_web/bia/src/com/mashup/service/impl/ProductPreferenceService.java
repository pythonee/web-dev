package com.mashup.service.impl;

import java.util.List;

import com.mashup.service.IProductPreferenceService;
import com.mashup.dao.IProductPreferenceDAO;
import com.mashup.domain.ProductPreference;
import com.mashup.domain.User;

public class ProductPreferenceService implements IProductPreferenceService{

	private IProductPreferenceDAO productPreferenceDAO;
	
	@Override
	public List<ProductPreference> findByUserId(int userId) {
		// TODO Auto-generated method stub
		
		ProductPreference example = new ProductPreference();
		User user = new User();
		
		user.setUserId(userId);
		
		example.setUser(user);
				
		return productPreferenceDAO.findByExample(example);
	}

	public void setProductPreferenceDAO(IProductPreferenceDAO productPreferenceDAO) {
		this.productPreferenceDAO = productPreferenceDAO;
	}
	
	public void insertProductPreference(ProductPreference productPreference){
		productPreferenceDAO.save(productPreference);
	}
	
		
}
