package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class PropImgDeleteRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = 1523833714532830359L;
	private String propimgId;
	private String iid;
	@Deprecated
	public String getPropimgId() {
		return propimgId;
	}
	public String getId() {
		return propimgId;
	}
	
	@Deprecated
	public void setPropimgId(String propimgId) {
		this.propimgId = propimgId;
	}
	public void setId(String id) {
		this.propimgId = id;
	}
	
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	
	
}
