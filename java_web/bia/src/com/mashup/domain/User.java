
package com.mashup.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.apache.commons.lang.StringUtils;

public class User implements java.io.Serializable,UserDetails{

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private Integer status;
	private Set comments = new HashSet(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set productPreferences = new HashSet(0);
	private Set friendsForUserId = new HashSet(0);
	private Set friendsForFriendId = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String email, Integer status) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
	}

	/** full constructor */
	public User(String username, String password, String email, Integer status,
			Set comments, Set userRoles, Set productPreferences,
			Set friendsForUserId, Set friendsForFriendId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.status = status;
		this.comments = comments;
		this.userRoles = userRoles;
		this.productPreferences = productPreferences;
		this.friendsForUserId = friendsForUserId;
		this.friendsForFriendId = friendsForFriendId;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	public Set getProductPreferences() {
		return this.productPreferences;
	}

	public void setProductPreferences(Set productPreferences) {
		this.productPreferences = productPreferences;
	}

	public Set getFriendsForUserId() {
		return this.friendsForUserId;
	}

	public void setFriendsForUserId(Set friendsForUserId) {
		this.friendsForUserId = friendsForUserId;
	}

	public Set getFriendsForFriendId() {
		return this.friendsForFriendId;
	}

	public void setFriendsForFriendId(Set friendsForFriendId) {
		this.friendsForFriendId = friendsForFriendId;
	}
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		  List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(userRoles.size());  
		     for(UserRole userRole : userRoles) {  
		         grantedAuthorities.add(new GrantedAuthorityImpl(userRole.getRole().getRoleName()));  
		     }
		         return grantedAuthorities.toArray(new GrantedAuthority[userRoles.size()]);  
	}
	
	public String getAuthoritiesString() {
	    String[] authorities = new String[this.userRoles.size()];
	    for(GrantedAuthority authority : this.getAuthorities()) {
	        for(int i=0; i<authorities.length; i++){
	        	authorities[i] = authority.getAuthority();
	        }
	    }
	    return StringUtils.join(authorities, ",");
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if(this.status.intValue() == 0){
			return false;
		}
		else{
			return true;
		}
	}


}