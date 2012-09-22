package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;

/**
 * 地址区域信息
 * @author gaoweibin.tw
 *
 */
public class Area extends TaobaoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2218038376753251584L;
	
	@ApiName("id")
	private String areaId;
	@ApiName("type")
	private String areaType;
	@ApiName("name")
	private String areaName;
	private String parentId;
	private String zip;
	
	@Deprecated
	public String getAreaId() {
		return areaId;
	}
	@Deprecated
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getId(){
		return this.areaId;
	}
	public void setId(String id){
		this.areaId = id;
	}
	public String getType(){
		return this.areaType;
	}
	public void setType(String type){
		this.areaType = type;
	}
	public String getName(){
		return this.areaName;
	}
	public void setName(String name){
		this.areaName = name;
	}
	
	@Deprecated
	public String getAreaType() {
		return areaType;
	}
	@Deprecated
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	@Deprecated
	public String getAreaName() {
		return areaName;
	}
	@Deprecated
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
}