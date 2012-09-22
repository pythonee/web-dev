package com.mashup.dao;

import java.util.List;

import com.mashup.domain.ProductSimilarity;


public interface IProductSimilarityDAO
{
	public void save(ProductSimilarity transientInstance);

	public void delete(ProductSimilarity persistentInstance);

	public ProductSimilarity findById(java.lang.Integer id);

	public List findByExample(ProductSimilarity instance);

	public List findByProperty(String propertyName, Object value);

	public List findBySimilarity(Object similarity);
	
	public List findAll();
}
