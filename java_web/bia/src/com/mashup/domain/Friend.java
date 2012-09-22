package com.mashup.domain;

/**
 * Friend entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Friend implements java.io.Serializable
{

	// Fields

	private Integer relationId;
	private User userByFriendId;
	private User userByUserId;

	// Constructors

	/** default constructor */
	public Friend()
	{
	}
	
	/** full constructor */
	public Friend(User userByFriendId, User userByUserId)
	{
		this.userByFriendId = userByFriendId;
		this.userByUserId = userByUserId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("User Name: ");
		toString.append(this.getUserByUserId().getUsername());
		toString.append("Friend Name: ");
		toString.append(this.getUserByFriendId().getUsername());
		return toString.toString();
	}

	// Property accessors

	public Integer getRelationId()
	{
		return this.relationId;
	}

	public void setRelationId(Integer relationId)
	{
		this.relationId = relationId;
	}

	public User getUserByFriendId()
	{
		return this.userByFriendId;
	}

	public void setUserByFriendId(User userByFriendId)
	{
		this.userByFriendId = userByFriendId;
	}

	public User getUserByUserId()
	{
		return this.userByUserId;
	}

	public void setUserByUserId(User userByUserId)
	{
		this.userByUserId = userByUserId;
	}
}