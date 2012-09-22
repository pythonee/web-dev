package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable
{

	// Fields

	private Integer categoryId;
	private String categoryName;
	private String tags;
	private Integer fatherId;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public Category()
	{
	}

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("Product Category Name: ");
		toString.append(this.getCategoryName());
		return toString.toString();
	}

	/** minimal constructor */
	public Category(String categoryName, String tags, Integer fatherId)
	{
		this.categoryName = categoryName;
		this.tags = tags;
		this.fatherId = fatherId;
	}

	/** full constructor */
	public Category(String categoryName, String tags, Integer fatherId,
			Set products)
	{
		this.categoryName = categoryName;
		this.tags = tags;
		this.fatherId = fatherId;
		this.products = products;
	}

	// Property accessors

	public Integer getCategoryId()
	{
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId)
	{
		this.categoryId = categoryId;
	}

	public String getCategoryName()
	{
		return this.categoryName;
	}

	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	public String getTags()
	{
		return this.tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}

	public Integer getFatherId()
	{
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId)
	{
		this.fatherId = fatherId;
	}

	public Set getProducts()
	{
		return this.products;
	}

	public void setProducts(Set products)
	{
		this.products = products;
	}

}