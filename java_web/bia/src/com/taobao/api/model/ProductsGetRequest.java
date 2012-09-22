package com.taobao.api.model;

/**
 * 查询用户(我)创建的SPU(Product)
 * @author gaoweibin.tw
 *
 */
public class ProductsGetRequest extends TaobaoRequest{
	
	//
	private static final long serialVersionUID = -3545827413684874178L;
	private String fields;
	private String nick;
	private int pageNo;
	private int pageSize;
	
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
