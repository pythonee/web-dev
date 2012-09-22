package com.mashup.domain;

import java.util.Date;

/**
 * Comment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable
{

	// Fields

	private Integer commentId;
	private User user;
	private Product product;
	private String commentContent;
	private Date commentTime;

	// Constructors

	/** default constructor */
	public Comment()
	{
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Comment Content: ");
		toString.append(this.getCommentContent());
		return toString.toString();
	}


	/** full constructor */
	public Comment(User user, Product product, String commentContent,
			Date commentTime)
	{
		this.user = user;
		this.product = product;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
	}

	// Property accessors

	public Integer getCommentId()
	{
		return this.commentId;
	}

	public void setCommentId(Integer commentId)
	{
		this.commentId = commentId;
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

	public String getCommentContent()
	{
		return this.commentContent;
	}

	public void setCommentContent(String commentContent)
	{
		this.commentContent = commentContent;
	}

	public Date getCommentTime()
	{
		return this.commentTime;
	}

	public void setCommentTime(Date commentTime)
	{
		this.commentTime = commentTime;
	}

}