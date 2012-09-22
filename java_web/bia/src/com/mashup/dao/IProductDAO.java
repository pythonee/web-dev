package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Category;
import com.mashup.domain.Product;

public interface IProductDAO {
	public void save(Product transientInstance);

	public void delete(Product persistentInstance);

	public Product findById(java.lang.Integer id);

	public List findByExample(Product instance);

	public List findByProperty(String propertyName, Object value);

	public List findByProductName(Object productName);
	public List findByProductDesc(Object productDes);
	public List findByPrice(Object price);
	public List findBySource(Object source);
	public List findByUrl(Object url);

	public List findByProductImg(Object productImg);

	public List findByLevelClick(Object levelClick);
	public List findByScore(Object score);
	public List getZhengpin();
	public List getClassic();
	public List getFasion();
	public List getTopScore();
	public List findAll();
	public void attachDirty(Product instance);
}
