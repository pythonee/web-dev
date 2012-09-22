package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable
{

	// Fields

	private Integer productId;
	private Category category;
	private String productName;
	private String productDesc;
	private Double price;
	private String source;
	private String url;
	private String productImg;
	private Integer levelClick;
	private Double score;
	private Set productPreferences = new HashSet(0);
	private Set productSimilaritiesForFirstSimilarityId = new HashSet(0);
	private Set productSimilaritiesForSecondSimilarityId = new HashSet(0);
	private Set collections = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Product()
	{
	}

	/** minimal constructor */
	public Product(Category category, String productName, String productDesc,
			Double price, String source, String url)
	{
		this.category = category;
		this.productName = productName;
		this.productDesc = productDesc;
		this.price = price;
		this.source = source;
		this.url = url;
	}

	/** full constructor */
	public Product(Category category, String productName, String productDesc,
			Double price, String source, String url, String productImg,
			Integer levelClick, Double score, Set productPreferences,
			Set productSimilaritiesForFirstSimilarityId,
			Set productSimilaritiesForSecondSimilarityId, Set collections,
			Set comments)
	{
		this.category = category;
		this.productName = productName;
		this.productDesc = productDesc;
		this.price = price;
		this.source = source;
		this.url = url;
		this.productImg = productImg;
		this.levelClick = levelClick;
		this.score = score;
		this.productPreferences = productPreferences;
		this.productSimilaritiesForFirstSimilarityId = productSimilaritiesForFirstSimilarityId;
		this.productSimilaritiesForSecondSimilarityId = productSimilaritiesForSecondSimilarityId;
		this.collections = collections;
		this.comments = comments;
	}

	// Property accessors

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("Product Name: ");
		toString.append(this.getProductName());
		return toString.toString();
	}

	public Integer getProductId()
	{
		return this.productId;
	}

	public void setProductId(Integer productId)
	{
		this.productId = productId;
	}

	public Category getCategory()
	{
		return this.category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public String getProductName()
	{
		return this.productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public String getProductDesc()
	{
		return this.productDesc;
	}

	public void setProductDesc(String productDesc)
	{
		this.productDesc = productDesc;
	}

	public Double getPrice()
	{
		return this.price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public String getSource()
	{
		return this.source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getProductImg()
	{
		return this.productImg;
	}

	public void setProductImg(String productImg)
	{
		this.productImg = productImg;
	}

	public Integer getLevelClick()
	{
		return this.levelClick;
	}

	public void setLevelClick(Integer levelClick)
	{
		this.levelClick = levelClick;
	}

	public Double getScore()
	{
		return this.score;
	}

	public void setScore(Double score)
	{
		this.score = score;
	}

	public Set getProductPreferences()
	{
		return this.productPreferences;
	}

	public void setProductPreferences(Set productPreferences)
	{
		this.productPreferences = productPreferences;
	}

	public Set getProductSimilaritiesForFirstSimilarityId()
	{
		return this.productSimilaritiesForFirstSimilarityId;
	}

	public void setProductSimilaritiesForFirstSimilarityId(
			Set productSimilaritiesForFirstSimilarityId)
	{
		this.productSimilaritiesForFirstSimilarityId = productSimilaritiesForFirstSimilarityId;
	}

	public Set getProductSimilaritiesForSecondSimilarityId()
	{
		return this.productSimilaritiesForSecondSimilarityId;
	}

	public void setProductSimilaritiesForSecondSimilarityId(
			Set productSimilaritiesForSecondSimilarityId)
	{
		this.productSimilaritiesForSecondSimilarityId = productSimilaritiesForSecondSimilarityId;
	}

	public Set getCollections()
	{
		return this.collections;
	}

	public void setCollections(Set collections)
	{
		this.collections = collections;
	}

	public Set getComments()
	{
		return this.comments;
	}

	public void setComments(Set comments)
	{
		this.comments = comments;
	}

}