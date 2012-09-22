package com.mashup.service.impl;

import java.util.*;

import com.mashup.domain.Product;
import com.mashup.dao.IProductDAO;
import com.mashup.service.IProductService;

public class ProductService implements IProductService
{
	
	IProductDAO productDAO;
	
	public List findAll()
	{
		List productList = productDAO.findAll();
		
		return productList;
	}
	
	public Product getProductById(Integer id){
		return productDAO.findById(id);
	}

	
	public void updateProduct(Product product){
		productDAO.attachDirty(product);
	}
	
	
	public void insertProduct(Product product){
		productDAO.save(product);
	}
	
	public void removeProductById(Integer id){
		productDAO.delete(productDAO.findById(id));
	}
	
	public List findByExample(Product product) {
		// TODO Auto-generated method stub
		return productDAO.findByExample(product);
	}
	public List getClassic(){
		List list=productDAO.getClassic();
		ArrayList alist=new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Product product=(Product)list.get(i);
			String s=product.getProductName();
			if(s.length()>8){
				s=s.substring(0, 8)+"…";
				product.setProductName(s);
			}
			alist.add(product);
		}
		return alist;
	}
	public List getZhengpin(){
		List list=productDAO.getZhengpin();
		ArrayList alist=new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Product product=(Product)list.get(i);
			String s=product.getProductName();
			if(s.length()>8){
				s=s.substring(0, 8)+"…";
				product.setProductName(s);
			}
			alist.add(product);
		}
		return alist;
	}
	public List getFasion(){
		List list=productDAO.getFasion();
		ArrayList alist=new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Product product=(Product)list.get(i);
			String s=product.getProductName();
			if(s.length()>8){
				s=s.substring(0, 8)+"…";
				product.setProductName(s);
			}
			alist.add(product);
		}
		return alist;
	}
	public List getTopScore(){
		List list=productDAO.getTopScore();
		ArrayList alist=new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Product product=(Product)list.get(i);
			String s=product.getProductName();
			if(s.length()>8){
				s=s.substring(0, 8)+"…";
				product.setProductName(s);
			}
			alist.add(product);
		}
		return alist;
	}
	public void batchRemoveProduct(List ls){
		
	}

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	

	
}

