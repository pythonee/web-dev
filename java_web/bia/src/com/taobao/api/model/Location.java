/**
 * 
 */
package com.taobao.api.model;

/**
 * 描述商品或者用户的地址
 * 
 * @author sulinchong.pt
 * 
 */
public class Location extends TaobaoModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6249548501164105022L;
	private String zip;
	private String address;
	private String city;
	private String state;
	private String country;
	private String district;
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Location withZip(String zip){
		setZip(zip);
		return this;
	}
	public Location withAddress(String address){
		setAddress(address);
		return this;
	}
	public Location withCity(String city){
		setCity(city);
		return this;
	}
	public Location withState(String state){
		setState(state);
		return this;
	}
	public Location withCountry(String country){
		setCountry(country);
		return this;
	}
	public Location withDistrict(String district){
		setDistrict(district);
		return this;
	}
}
