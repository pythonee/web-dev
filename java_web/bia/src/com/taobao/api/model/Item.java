/**
 * 
 */
package com.taobao.api.model;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author sulinchong.pt
 *
 */
public class Item extends TaobaoModel{
	private static final long serialVersionUID = 33230486742413888L;
	private String iid;
	private String detailUrl;
	private String numIid;
	private String outerId;
	private String title;
	private String nick;
	private String type;
	private String cid;
	
	public String getNumIid() {
		return numIid;
	}
	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}
	private String sellerCids;
	private String props;
	private String desc;
	
	@ApiName("pic_url")
	private String picPath;
	
	private Integer num;
	
	private Integer validThru;
	private Date listTime;
	private Date delistTime;
	private String stuffStatus;
	private Location location;
	
	private String price;	
	private String postFee;
	private String expressFee;
	private String emsFee;
	private Boolean hasDiscount;
	
	private String freightPayer;
	private Boolean hasInvoice;
	private Boolean hasWarranty;
	private Boolean hasShowcase;
	private Integer bulkBaseNum;
	
	private Date modified;
	private Date created;
	private String increment;
	private Boolean autoRepost;
	private String approveStatus;
	private File image;
	
	private String postageId;
	private String productId;
	private String auctionPoint;
	private String propertyAlias;
	private String inputPids;
	private String inputStr;
	@ApiName("is_virtual")
	private String isVirtural;
	
	private List<ItemImg> itemImgs;
	private List<PropImg> propImgs;
	private List<Sku> skus;
	private List<Video> videos;
	
	/**
	 * 是否定时
	 */
	private Boolean isTiming;
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
	@ApiName("is_3D")
	private Boolean is3D;
	
	//卖家信用数,只有taobao.items.get和taobao.items.search能够得到
	private Long score;
	
	//商品30天成交量,只有taobao.items.get和taobao.items.search能够得到
	private Long volume;
	
	/**
	 * 是否淘1站商品
	 */
	private Boolean oneStation;
	
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCid() {
		return cid;
	}

	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSellerCids() {
		return sellerCids;
	}
	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Deprecated
	public String getPicPath() {
		return picPath;
	}
	public String getPicUrl() {
		return picPath;
	}
	@Deprecated
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public void setPicUrl(String picPath) {
		this.picPath = picPath;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getValidThru() {
		return validThru;
	}
	public void setValidThru(Integer validThru) {
		this.validThru = validThru;
	}
	public Date getListTime() {
		return listTime;
	}
	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}
	public Date getDelistTime() {
		return delistTime;
	}
	public void setDelistTime(Date delistTime) {
		this.delistTime = delistTime;
	}
	public String getStuffStatus() {
		return stuffStatus;
	}
	public void setStuffStatus(String stuffStatus) {
		this.stuffStatus = stuffStatus;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getEmsFee() {
		return emsFee;
	}
	public void setEmsFee(String emsFee) {
		this.emsFee = emsFee;
	}
	public Boolean getHasDiscount() {
		return hasDiscount;
	}
	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}
	public String getFreightPayer() {
		return freightPayer;
	}
	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
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
	public Boolean getHasShowcase() {
		return hasShowcase;
	}
	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}
	public Integer getBulkBaseNum() {
		return bulkBaseNum;
	}
	public void setBulkBaseNum(Integer bulkBaseNum) {
		this.bulkBaseNum = bulkBaseNum;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getIncrement() {
		return increment;
	}
	public void setIncrement(String increment) {
		this.increment = increment;
	}
	public Boolean getAutoRepost() {
		return autoRepost;
	}
	public void setAutoRepost(Boolean autoRepost) {
		this.autoRepost = autoRepost;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getPostageId() {
		return postageId;
	}
	public void setPostageId(String postageId) {
		this.postageId = postageId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	public List<ItemImg> getItemImgs() {
		return itemImgs;
	}
	public void setItemImgs(List<ItemImg> itemImgs) {
		this.itemImgs = itemImgs;
	}
	public List<PropImg> getPropImgs() {
		return propImgs;
	}
	public void setPropImgs(List<PropImg> propImgs) {
		this.propImgs = propImgs;
	}
	public List<Sku> getSkus() {
		return skus;
	}
	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}
	public String getOuterId() {
		return outerId;
	}
	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}
	public Boolean getIsTiming() {
		return isTiming;
	}
	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Deprecated
	public String getIsVirtural() {
		return isVirtural;
	}
	public String getIsVirtual() {
		return isVirtural;
	}
	@Deprecated
	public void setIsVirtural(String isVirtural) {
		this.isVirtural = isVirtural;
	}
	public void setIsVirtual(String isVirtural) {
		this.isVirtural = isVirtural;
	}
	public List<Video> getVideos() {
		return this.videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	public Boolean getIs3D() {
		return this.is3D;
	}
	public void setIs3D(Boolean is3D) {
		this.is3D = is3D;
	}
	public Long getScore() {
		return this.score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Long getVolume() {
		return this.volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public Boolean getOneStation() {
		return this.oneStation;
	}
	public void setOneStation(Boolean oneStation) {
		this.oneStation = oneStation;
	}
	
}
