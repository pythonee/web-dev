package com.mashup.service;

import java.util.List;

import com.mashup.domain.ProductPreference;

public interface IProductPreferenceService {
	public List<ProductPreference> findByUserId(int userId);
	public void insertProductPreference(ProductPreference productPreference);
}
