/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

/**
 * 产品增值服务。
 * 
 * @author fengsheng
 * @since 1.0, Jul 14, 2009
 */
public class VasProduct extends TaobaoModel {

	private static final long serialVersionUID = -5661817033821202828L;

	private Long prodId; // 产品ID
	private String prodName; // 产品名称
	private Long planId; // 套餐ID
	private String planName; // 套餐名称
	private String servCode; // 服务代号
	private String servName; // 服务名称
	private Long status; // 服务状态
	private Date startDate; // 服务开始时间
	private Date endDate; // 服务结束时间

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getServCode() {
		return servCode;
	}

	public void setServCode(String servCode) {
		this.servCode = servCode;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
