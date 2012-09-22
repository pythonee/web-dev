/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author biyi
 *
 */
public class TradeRate extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6899575447248412430L;
	private String tid;
	@ApiName("oid")
	private String orderId;
	private String role;
	private String ratedNick;
	private String nick;
	private String result;
	private Date created;
	private String itemTitle; 
	private String itemPrice;
	private String content;
	private String reply;
	
	
	
	@Deprecated
	public String getOrderId() {
		return orderId;
	}
	@Deprecated
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOid() {
		return orderId;
	}
	public void setOid(String oid) {
		this.orderId = oid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRatedNick() {
		return ratedNick;
	}
	public void setRatedNick(String ratedNick) {
		this.ratedNick = ratedNick;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
