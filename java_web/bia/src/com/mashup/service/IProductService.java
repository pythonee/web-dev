package com.mashup.service;

import java.util.List;

import com.mashup.dao.IProductDAO;
import com.mashup.domain.Product;

public interface IProductService {
	public List findAll();
	
	public Product getProductById(Integer id);

	public void updateProduct(Product product);
	
	public void insertProduct(Product product);
	
	public void removeProductById(Integer id);
	
	public void batchRemoveProduct(List ls);
	
	public List findByExample(Product product);
	public List getClassic();
	public List getZhengpin();
	public List getFasion();
	public List getTopScore();
	public IProductDAO getProductDAO();

	public void setProductDAO(IProductDAO productDAO);
}
