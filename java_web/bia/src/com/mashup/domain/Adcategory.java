package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;


/**
 * Adcategory entity. @author MyEclipse Persistence Tools
 */

public class Adcategory  implements java.io.Serializable {


    // Fields    

     private Integer adCategoryId;
     private String adCategoryName;
     private Set advertises = new HashSet(0);


    // Constructors

    @Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Ad Category Name: ");
		toString.append(this.getAdCategoryName());
		return toString.toString();
		
	}

	/** default constructor */
    public Adcategory() {
    }

	/** minimal constructor */
    public Adcategory(String adCategoryName) {
        this.adCategoryName = adCategoryName;
    }
    
    /** full constructor */
    public Adcategory(String adCategoryName, Set advertises) {
        this.adCategoryName = adCategoryName;
        this.advertises = advertises;
    }

   
    // Property accessors

    public Integer getAdCategoryId() {
        return this.adCategoryId;
    }
    
    public void setAdCategoryId(Integer adCategoryId) {
        this.adCategoryId = adCategoryId;
    }

    public String getAdCategoryName() {
        return this.adCategoryName;
    }
    
    public void setAdCategoryName(String adCategoryName) {
        this.adCategoryName = adCategoryName;
    }

    public Set getAdvertises() {
        return this.advertises;
    }
    
    public void setAdvertises(Set advertises) {
        this.advertises = advertises;
    }
   








}