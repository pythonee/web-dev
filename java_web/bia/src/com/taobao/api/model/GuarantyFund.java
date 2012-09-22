package com.taobao.api.model;

import java.util.Date;

/**
 * B商家保证金使用记录。
 * 
 * @author fengsheng
 * @since 1.0, Jun 30, 2009
 */
public class GuarantyFund extends TaobaoModel {

	private static final long serialVersionUID = 9012197994329277997L;

	private Long id; // 保证金编号
	private Integer type; // 保证金类型 （冻结保证金1，解冻保证金2，转移保证金3）
	private Long amount; // 保证金金额
	private Long balance; // 保证金余额
	private Long sellerId; // 商家编号
	private String sellerNick; // 商家昵称
	private Long buyerId; // 买家编号
	private String buyerNick; // 买家昵称
	private Long orderId; // 淘宝交易号（主订单的编号）
	private Long accuseId; // 投诉编号 （退款ID）
	private Integer operationType; // 保证金操作类型 (1~9)
	private String operationAction; // 保证金操作动作
	private String memo; // 备注（一般为操作类型加上操作动作）
	private String creator; // 保证金创建者
	private String operator; // 保证金操作者
	private Date created; // 保证金创建时间
	private Date modified; // 保证金修改时间
	private Integer status; // 状态（成功1，失败0）

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getBalance() {
		return this.balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerNick() {
		return this.sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Long getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerNick() {
		return this.buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getAccuseId() {
		return this.accuseId;
	}

	public void setAccuseId(Long accuseId) {
		this.accuseId = accuseId;
	}

	public Integer getOperationType() {
		return this.operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getOperationAction() {
		return this.operationAction;
	}

	public void setOperationAction(String operationAction) {
		this.operationAction = operationAction;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
