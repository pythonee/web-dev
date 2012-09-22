/**
 * 
 */
package com.taobao.api.model;

import java.util.List;

/**
 * @author sulinchong.pt
 * 
 */
public class UsersGetResponse extends TaobaoListResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5109826029519121841L;
	private List<User> users;

	public UsersGetResponse() {
		super();
	}

	public UsersGetResponse(TaobaoResponse rsp) {
		super(rsp);
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}
}
