package com.taobao.api.model;

import java.util.List;

/**
 * 查询地址区域信息返回的结果
 * @author gaoweibin.tw
 *
 */
public class AreasGetResponse extends TaobaoListResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2687725827228830225L;
	
	private List<Area> areas;
	
	public AreasGetResponse(){
		super();
	}
	
	public AreasGetResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
}
