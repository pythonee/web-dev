package com.taobao.api.model;

/**
 * 淘客商品查询请求参数
 * @author gaoweibin.tw
 *
 */
public class TaokeItemsGetRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 9121085919398005302L;
	//查询字段
	private String field;
	//商品标题中包含的关键字
	private String keyword;
	//商品所属分类id
	private String cid;
	//淘客id 
	private String pid;
	//宝贝字符串id
	private String itemId;
	//起始价格
	private String startPrice;
	//最高价格
	private String endPrice;
	//发货方式
	private String sendType;
	//付款方式
	private String payType;
	//城市
	private String city;
	//卖家信用
	private String cedit;
	//排序方式
	private String sort;
	//消保方式
	private String guarantee;
	private String pageNo;
	private String pageSize;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCedit() {
		return cedit;
	}
	public void setCedit(String cedit) {
		this.cedit = cedit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
