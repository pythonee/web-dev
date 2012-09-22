package com.mashup.domain;

/**
 * ProductSimilarity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ProductSimilarity implements java.io.Serializable
{

	// Fields

	private Integer productSimilarityId;
	private Product productByFirstSimilarityId;
	private Product productBySecondSimilarityId;
	private Integer similarity;

	// Constructors

	/** default constructor */
	public ProductSimilarity()
	{
	}

	/** full constructor */
	public ProductSimilarity(Product productByFirstSimilarityId,
			Product productBySecondSimilarityId, Integer similarity)
	{
		this.productByFirstSimilarityId = productByFirstSimilarityId;
		this.productBySecondSimilarityId = productBySecondSimilarityId;
		this.similarity = similarity;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Product Similarity: ");
		toString.append(this.getSimilarity().toString());
		return toString.toString();
	}

	// Property accessors

	public Integer getProductSimilarityId()
	{
		return this.productSimilarityId;
	}

	public void setProductSimilarityId(Integer productSimilarityId)
	{
		this.productSimilarityId = productSimilarityId;
	}

	public Product getProductByFirstSimilarityId()
	{
		return this.productByFirstSimilarityId;
	}

	public void setProductByFirstSimilarityId(Product productByFirstSimilarityId)
	{
		this.productByFirstSimilarityId = productByFirstSimilarityId;
	}

	public Product getProductBySecondSimilarityId()
	{
		return this.productBySecondSimilarityId;
	}

	public void setProductBySecondSimilarityId(
			Product productBySecondSimilarityId)
	{
		this.productBySecondSimilarityId = productBySecondSimilarityId;
	}

	public Integer getSimilarity()
	{
		return this.similarity;
	}

	public void setSimilarity(Integer similarity)
	{
		this.similarity = similarity;
	}

}