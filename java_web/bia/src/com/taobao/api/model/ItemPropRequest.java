/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class ItemPropRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 2875492326049243414L;
	private String cid;
	private String pid;
	private String childPath;
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
	public String getChildPath() {
		return childPath;
	}
	public void setChildPath(String childPath) {
		this.childPath = childPath;
	}
	public ItemPropRequest withCid(String cid){
		setCid(cid);
		return this;
	}
	public ItemPropRequest withPid(String pid){
		setPid(pid);
		return this;
	}
	public ItemPropRequest withChildPath(String childPath){
		setChildPath(childPath);
		return this;
	}
}
