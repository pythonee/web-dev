/**
 * 
 */
package com.taobao.api.model;

import java.io.File;
import java.util.Date;

/**
 * 调用taobao.item.update更新商品时需要传入的参数
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemUpdateRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -8384094408547399784L;
	private String iid;
	private String outerId;
	private String approveStatus;
	private String cid;
	private String props;
	private Integer num;
	private String price;
	private String stuffStatus;

	private String title;
	private String desc;
	private Location location;

	private String freightPayer;
	private Integer validThru;
	private Boolean hasInvoice;
	private Boolean hasWarranty;
	private String sellerCids;

	private Boolean hasDiscount;
	private String postFee;
	private String expressFee;
	private Date listTime;
	private String increment;

	private Boolean autoRepost;
	private Boolean hasShowcase;
	private String emsFee;
	private File image;
	//更新商品主图可以上传图片空间图片的url
	private String picPath;
	
	private String auctionPoint;
	private String propertyAlias;
	private String skuIds;
	private String skuProperties;
	private String skuQuantities;
	private String skuPrices;
	private String skuOuterIds;
	private String postageId;
	private String inputPids;
	private String inputStr;
	private String lang;
	private String productId;
	/**
	 * 是否在淘宝显示
	 */
	private Boolean isTaobao;
	
	/**
	 * 是否在外部网店显示
	 */
	private Boolean isEx;
	/**
	 * 是否是3D淘宝的商品
	 */
	private Boolean is3D;
	
	private Boolean replaceSku;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Boolean isSetCid() {
		return this.cid != null;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public Boolean isSetProps() {
		return this.props != null;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStuffStatus() {
		return stuffStatus;
	}

	public void setStuffStatus(String stuffStatus) {
		this.stuffStatus = stuffStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public Integer getValidThru() {
		return validThru;
	}

	public void setValidThru(Integer validThru) {
		this.validThru = validThru;
	}

	public String getSellerCids() {
		return sellerCids;
	}

	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}

	public String getPostFee() {
		return postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(String expressFee) {
		this.expressFee = expressFee;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public Boolean getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public Boolean getHasWarranty() {
		return hasWarranty;
	}

	public void setHasWarranty(Boolean hasWarranty) {
		this.hasWarranty = hasWarranty;
	}

	public Boolean getHasDiscount() {
		return hasDiscount;
	}

	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public Boolean getAutoRepost() {
		return autoRepost;
	}

	public void setAutoRepost(Boolean autoRepost) {
		this.autoRepost = autoRepost;
	}

	public Boolean getHasShowcase() {
		return hasShowcase;
	}

	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getEmsFee() {
		return emsFee;
	}

	public void setEmsFee(String emsFee) {
		this.emsFee = emsFee;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public ItemUpdateRequest withApproveStatus(String approveStatus) {
		setApproveStatus(approveStatus);
		return this;
	}

	public ItemUpdateRequest withCid(String cid) {
		setCid(cid);
		return this;
	}

	public ItemUpdateRequest withProps(String porps) {
		setProps(porps);
		return this;
	}

	public ItemUpdateRequest withNum(Integer num) {
		setNum(num);
		return this;
	}

	public ItemUpdateRequest withPrice(String price) {
		setPrice(price);
		return this;
	}

	public ItemUpdateRequest withTitle(String title) {
		setTitle(title);
		return this;
	}

	public ItemUpdateRequest withDesc(String desc) {
		setDesc(desc);
		return this;
	}

	public ItemUpdateRequest withLocation(Location location) {
		setLocation(location);
		return this;
	}

	public ItemUpdateRequest withFreightPayer(String freightPayer) {
		setFreightPayer(freightPayer);
		return this;
	}

	public ItemUpdateRequest withValidThru(Integer validThru) {
		setValidThru(validThru);
		return this;
	}

	public ItemUpdateRequest withHasInvoice(Boolean hasInvoice) {
		setHasInvoice(hasInvoice);
		return this;
	}

	public ItemUpdateRequest withHasWarranty(Boolean hasWarranty) {
		setHasWarranty(hasWarranty);
		return this;
	}

	public ItemUpdateRequest withSellerCids(String sellerCids) {
		setSellerCids(sellerCids);
		return this;
	}

	public ItemUpdateRequest withHasDiscount(Boolean hasDiscount) {
		setHasDiscount(hasDiscount);
		return this;
	}

	public ItemUpdateRequest withPostFee(String postFee) {
		setPostFee(postFee);
		return this;
	}

	public ItemUpdateRequest withExpressFee(String expressFee) {
		setExpressFee(expressFee);
		return this;
	}

	public ItemUpdateRequest withListTime(Date listTime) {
		setListTime(listTime);
		return this;
	}

	public ItemUpdateRequest withIncrement(String increment) {
		setIncrement(increment);
		return this;
	}

	public ItemUpdateRequest withAutoRepost(Boolean autoRepost) {
		setAutoRepost(autoRepost);
		return this;
	}

	public ItemUpdateRequest withHasShowcase(Boolean hasShowcase) {
		setHasShowcase(hasShowcase);
		return this;
	}

	public ItemUpdateRequest withEmsFee(String emsFee) {
		setEmsFee(emsFee);
		return this;
	}

	public ItemUpdateRequest withImage(File image) {
		setImage(image);
		return this;
	}

	public ItemUpdateRequest withIid(String iid) {
		setIid(iid);
		return this;
	}

	public String getAuctionPoint() {
		return auctionPoint;
	}

	public void setAuctionPoint(String auctionPoint) {
		this.auctionPoint = auctionPoint;
	}

	public String getPropertyAlias() {
		return propertyAlias;
	}

	public void setPropertyAlias(String propertyAlias) {
		this.propertyAlias = propertyAlias;
	}

	public String getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String skuIds) {
		this.skuIds = skuIds;
	}

	public String getSkuProperties() {
		return skuProperties;
	}

	public void setSkuProperties(String skuProperties) {
		this.skuProperties = skuProperties;
	}

	public String getSkuQuantities() {
		return skuQuantities;
	}

	public void setSkuQuantities(String skuQuantities) {
		this.skuQuantities = skuQuantities;
	}

	public String getSkuPrices() {
		return skuPrices;
	}

	public void setSkuPrices(String skuPrices) {
		this.skuPrices = skuPrices;
	}

	public String getSkuOuterIds() {
		return skuOuterIds;
	}

	public void setSkuOuterIds(String skuOuterIds) {
		this.skuOuterIds = skuOuterIds;
	}

	public String getPostageId() {
		return postageId;
	}

	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}
	
	public String getInputPids() {
		return inputPids;
	}

	public void setInputPids(String inputPids) {
		this.inputPids = inputPids;
	}

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public ItemUpdateRequest withAuctionPoint(String auctionPoint) {
		setAuctionPoint(auctionPoint);
		return this;
	}
	
	public ItemUpdateRequest withPropertyAlias(String propertyAlias) {
		setPropertyAlias(propertyAlias);
		return this;
	}
	
	public ItemUpdateRequest withSkuIds(String skuIds) {
		setSkuIds(skuIds);
		return this;
	}
	
	public ItemUpdateRequest withSkuProperties(String skuProperties) {
		setSkuProperties(skuProperties);
		return this;
	}
	
	public ItemUpdateRequest withSkuQuantities(String skuQuantities) {
		setSkuQuantities(skuQuantities);
		return this;
	}
	
	public ItemUpdateRequest withSkuPrices(String skuPrices) {
		setSkuPrices(skuPrices);
		return this;
	}
	
	public ItemUpdateRequest withSkuOuterIds(String skuOuterIds) {
		setSkuOuterIds(skuOuterIds);
		return this;
	}
	
	public ItemUpdateRequest withPostageId(String postageId) {
		setPostageId(postageId);
		return this;
	}
	
	public ItemUpdateRequest withInputPids(String inputPids) {
		setInputPids(inputPids);
		return this;
	}
	
	public ItemUpdateRequest withInputStr(String inputStr) {
		setInputStr(inputStr);
		return this;
	}
	public ItemUpdateRequest withOuterId(String outerId) {
		this.setOuterId(outerId);
		return this;
	}
	public ItemUpdateRequest withProductId(String productId) {
		this.setProductId(productId);
		return this;
	}
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Boolean getIsTaobao() {
		return isTaobao;
	}

	public void setIsTaobao(Boolean isTaobao) {
		this.isTaobao = isTaobao;
	}

	public Boolean getIsEx() {
		return isEx;
	}

	public void setIsEx(Boolean isEx) {
		this.isEx = isEx;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Boolean getIs3D() {
		return this.is3D;
	}

	public void setIs3D(Boolean is3D) {
		this.is3D = is3D;
	}

	public Boolean getReplaceSku() {
		return this.replaceSku;
	}

	public void setReplaceSku(Boolean replaceSku) {
		this.replaceSku = replaceSku;
	}
	
}
