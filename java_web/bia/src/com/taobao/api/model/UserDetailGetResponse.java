package com.taobao.api.model;

import com.taobao.api.convert.reader.ApiName;
import com.taobao.api.model.TaobaoResponse;

public class UserDetailGetResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8147680424782981135L;
	
	@ApiName("user")
	User userDetail;
	
	public UserDetailGetResponse(){
		super();
	}
	
	public UserDetailGetResponse(TaobaoResponse rsp){
		super(rsp);
	}

	public User getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(User userDetail) {
		this.userDetail = userDetail;
	}
	
}
