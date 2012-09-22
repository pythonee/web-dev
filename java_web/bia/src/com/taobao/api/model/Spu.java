/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class Spu extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2595097603692383158L;
	private String binds;
	private String cid;
	private String name;
	private String picPath;
	private String props;
	public String getBinds() {
		return binds;
	}
	public void setBinds(String binds) {
		this.binds = binds;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	
}
