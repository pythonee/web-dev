package com.mashup.domain;

/**
 * Sensitiveword entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Sensitiveword implements java.io.Serializable
{

	// Fields

	private Integer sensitiveId;
	private String sensitiveWord;

	// Constructors

	/** default constructor */
	public Sensitiveword()
	{
	}

	/** full constructor */
	public Sensitiveword(String sensitiveWord)
	{
		this.sensitiveWord = sensitiveWord;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Sensitive Word: ");
		toString.append(this.getSensitiveWord());
		return toString.toString();
	}
	// Property accessors

	public Integer getSensitiveId()
	{
		return this.sensitiveId;
	}

	public void setSensitiveId(Integer sensitiveId)
	{
		this.sensitiveId = sensitiveId;
	}

	public String getSensitiveWord()
	{
		return this.sensitiveWord;
	}

	public void setSensitiveWord(String sensitiveWord)
	{
		this.sensitiveWord = sensitiveWord;
	}

}