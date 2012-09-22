/**
 * 
 */
package com.taobao.api.model;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * 调用 taobao.item.add 添加商品时 需要传入的参数
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemAddRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = -2238026364865830092L;
	private String approveStatus;
	private String cid;
	private String props;
	private Integer num;
	private String price;
	private String outerId;

	private String type;
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
	//新增商品可以上传图片空间图片的url
	private String picPath;
	
	private String auctionPoint;
	private String propertyAlias;
	private String skuProperties;
	private String skuQuantities;
	private String skuPrices;
	private String skuOuterIds;
	private String postageId;
	private String inputPids;
	private String inputStr;
	private String lang;
	private Boolean isTaobao;
	private Boolean isEx;
	private String productId;
	/**
	 * 是否是3D淘宝的商品
	 */
	private Boolean is3D;

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Boolean isSetApproveStatus() {
		return this.approveStatus != null;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public void setListTime(Date listTime) {
		this.listTime = listTime;
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

	public Date getListTime() {
		return listTime;
	}

	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public void setHasWarranty(Boolean hasWarranty) {
		this.hasWarranty = hasWarranty;
	}

	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public void setAutoRepost(Boolean autoRepost) {
		this.autoRepost = autoRepost;
	}

	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}

	public Boolean getHasInvoice() {
		return hasInvoice;
	}

	public Boolean getHasWarranty() {
		return hasWarranty;
	}

	public Boolean getHasDiscount() {
		return hasDiscount;
	}

	public Boolean getAutoRepost() {
		return autoRepost;
	}

	public Boolean getHasShowcase() {
		return hasShowcase;
	}

	public ItemAddRequest withApproveStatus(String approveStatus) {
		setApproveStatus(approveStatus);
		return this;
	}

	public ItemAddRequest withCid(String cid) {
		setCid(cid);
		return this;
	}

	public ItemAddRequest withProps(String porps) {
		setProps(porps);
		return this;
	}

	public ItemAddRequest withNum(Integer num) {
		setNum(num);
		return this;
	}

	public ItemAddRequest withPrice(String price) {
		setPrice(price);
		return this;
	}

	public ItemAddRequest withType(String type) {
		setType(type);
		return this;
	}

	public ItemAddRequest withStuffStatus(String stuffStatus) {
		setStuffStatus(stuffStatus);
		return this;
	}

	public ItemAddRequest withTitle(String title) {
		setTitle(title);
		return this;
	}

	public ItemAddRequest withDesc(String desc) {
		setDesc(desc);
		return this;
	}

	public ItemAddRequest withLocation(Location location) {
		setLocation(location);
		return this;
	}

	public ItemAddRequest withFreightPayer(String freightPayer) {
		setFreightPayer(freightPayer);
		return this;
	}

	public ItemAddRequest withValidThru(Integer validThru) {
		setValidThru(validThru);
		return this;
	}
	
	public ItemAddRequest withHasInvoice(Boolean hasInvoice) {
		setHasInvoice(hasInvoice);
		return this;
	}

	public ItemAddRequest withHasWarranty(Boolean hasWarranty) {
		setHasWarranty(hasWarranty);
		return this;
	}

	public ItemAddRequest withSellerCids(String sellerCids) {
		setSellerCids(sellerCids);
		return this;
	}

	public ItemAddRequest withHasDiscount(Boolean hasDiscount) {
		setHasDiscount(hasDiscount);
		return this;
	}

	public ItemAddRequest withPostFee(String postFee) {
		setPostFee(postFee);
		return this;
	}

	public ItemAddRequest withExpressFee(String expressFee) {
		setExpressFee(expressFee);
		return this;
	}

	public ItemAddRequest withListTime(Date listTime) {
		setListTime(listTime);
		return this;
	}

	public ItemAddRequest withIncrement(String increment) {
		setIncrement(increment);
		return this;
	}

	public ItemAddRequest withAutoRepost(Boolean autoRepost) {
		setAutoRepost(autoRepost);
		return this;
	}

	public ItemAddRequest withHasShowcase(Boolean hasShowcase) {
		setHasShowcase(hasShowcase);
		return this;
	}

	public ItemAddRequest withEmsFee(String emsFee) {
		setEmsFee(emsFee);
		return this;
	}

	public ItemAddRequest withImage(File image) {
		setImage(image);
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

	public ItemAddRequest withAuctionPoint(String auctionPoint) {
		setAuctionPoint(auctionPoint);
		return this;
	}
	
	public ItemAddRequest withPropertyAlias(String propertyAlias) {
		setPropertyAlias(propertyAlias);
		return this;
	}
	
	public ItemAddRequest withSkuProperties(String skuProperties) {
		setSkuProperties(skuProperties);
		return this;
	}
	
	public ItemAddRequest withSkuQuantities(String skuQuantities) {
		setSkuQuantities(skuQuantities);
		return this;
	}
	
	public ItemAddRequest withSkuPrices(String skuPrices) {
		setSkuPrices(skuPrices);
		return this;
	}
	
	public ItemAddRequest withSkuOuterIds(String skuOuterIds) {
		setSkuOuterIds(skuOuterIds);
		return this;
	}
	
	public ItemAddRequest withPostageId(String postageId) {
		setPostageId(postageId);
		return this;
	}
	
	public ItemAddRequest withInputPids(String inputPids) {
		setInputPids(inputPids);
		return this;
	}
	public ItemAddRequest withOuterId(String outerId) {
		this.setOuterId(outerId);
		return this;
	}
	public ItemAddRequest withInputStr(String inputStr) {
		setInputStr(inputStr);
		return this;
	}
	public ItemAddRequest withProductId(String productId) {
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

	
}
