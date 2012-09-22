package com.mashup.domain;

import java.sql.Timestamp;

/**
 * ProductPreference entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ProductPreference implements java.io.Serializable
{

	// Fields

	private Integer productPreferenceId;
	private User user;
	private Product product;
	private Double preference;
	private Timestamp timestamp;

	// Constructors

	/** default constructor */
	public ProductPreference()
	{
	}

	/** full constructor */
	public ProductPreference(User user, Product product, Double preference,
			Timestamp timestamp)
	{
		this.user = user;
		this.product = product;
		this.preference = preference;
		this.timestamp = timestamp;
	}

	// Property accessors

	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append("User:");
		toString.append(this.getUser().getUsername());
		toString.append("Product:");
		toString.append(this.getProduct().getProductName());
		toString.append("Preference");
		toString.append(this.getPreference().toString());
		return toString.toString();
	}
	public Integer getProductPreferenceId()
	{
		return this.productPreferenceId;
	}

	public void setProductPreferenceId(Integer productPreferenceId)
	{
		this.productPreferenceId = productPreferenceId;
	}

	public User getUser()
	{
		return this.user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Product getProduct()
	{
		return this.product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public Double getPreference()
	{
		return this.preference;
	}

	public void setPreference(Double preference)
	{
		this.preference = preference;
	}

	public Timestamp getTimestamp()
	{
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp)
	{
		this.timestamp = timestamp;
	}

}