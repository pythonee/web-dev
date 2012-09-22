package com.taobao.api.model;

/**
 * 淘客商品搜索信息
 * @author gaoweibin.tw
 *
 */
public class TaokeItem extends TaobaoModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1278370694034314934L;
	
	private String iid;//商品id 
	private String title; //商品名称
	private String nick; //卖家昵称
	private String picUrl; // 图片url
	private String price; //商品价格
	private String checkUrl; //推广点击url
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
	public String getCheckUrl() {
		return checkUrl;
	}
	public void setCheckUrl(String checkUrl) {
		this.checkUrl = checkUrl;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
