/**
 * 
 */
package com.taobao.api.model;

/**
 * @author sulinchong.pt
 *
 */
public class SpuGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 456376407723394042L;
	private String fields;
	private String cid;
	private String props;
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getProps() {
		return props;
	}
	public void setProps(String props) {
		this.props = props;
	}
	public SpuGetRequest withFields(String fields){
		setFields(fields);
		return this;
	}
	public SpuGetRequest withCid(String cid){
		setCid(cid);
		return this;
	}
	public SpuGetRequest withProps(String props){
		setProps(props);
		return this;
	}
}
