package com.taobao.api.model;

/**
 * 品牌Model
 * 
 * @author jeck.xie 2009-3-25
 */
public class Brand extends TaobaoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9006183388019209274L;

	String vid;
	String name;// 属性的名称
	String pid;
	String propName;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

}
