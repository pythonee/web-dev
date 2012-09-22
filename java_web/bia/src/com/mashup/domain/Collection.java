package com.mashup.domain;



/**
 * Collection entity. @author MyEclipse Persistence Tools
 */

public class Collection  implements java.io.Serializable {


    // Fields    

     private Integer collectionId;
     private Product product;
     private Integer userId;


    // Constructors

    /** default constructor */
    public Collection() {
    }

    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder toString = new StringBuilder();
		toString.append("Collection Product Name: ");
		toString.append(this.getProduct().getProductName());
		return toString.toString();
	}


	/** full constructor */
    public Collection(Product product, Integer userId) {
        this.product = product;
        this.userId = userId;
    }

   
    // Property accessors

    public Integer getCollectionId() {
        return this.collectionId;
    }
    
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
   








}