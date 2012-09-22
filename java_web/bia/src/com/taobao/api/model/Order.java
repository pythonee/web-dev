/**
 * 
 */
package com.taobao.api.model;

import java.util.Date;

import com.taobao.api.convert.reader.ApiName;

/**
 * @author sulinchong.pt 2009-1-10 下午04:10:21
 *
 */
public class Order extends TaobaoModel{
	
	private static final long serialVersionUID = -1645865632872118189L;

	//公开数据
	/**
	 * 商品iid
	 */
	private String iid;
	
	/**
	 * 商品最小属性单元
	 */
	private String skuId;
	
	/* ------------ add jeck 2009-06-26 TAOBAOAPI-756 ------------ */
	/**
	 * sku的值。如：机身颜色:黑色;手机套餐:官方标配
	 */
	private String skuPropertiesName;
	
	/**
	 * 套餐的值。如：M8原装电池:便携支架:M8专用座充:莫凡保护袋
	 */
	private String itemMealName;
	/* ------------ end ------------ */
	
	/**
	 * 购买数量
	 */
	private Integer num;
	
	
	/**
	 * 商品标题
	 */
	private String title;
	
	
	/**
	 * 商品价格
	 */
	private String price;
	
	
	/**
	 * 商品图片路径
	 */
	private String picPath;
	
	
	
	//买卖家共享数据
	/**
	 * 淘宝交易号，即父订单在淘宝业务订单中的bizOrderId
	 */
	@ApiName("oid")
	private String tid;
	
	
	/**
	 * 退款状态
	 */
	private String refundStatus;

	/**
	 * 卖家昵称
	 */
	private String sellerNick;
	
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	
	/**
	 * 交易类型
	 */
	private String type;
	
	/**
	 * 创建时间
	 */
	private Date created;
	
	private String outerSkuId;
	
	private String outerIid;
	
	/**
	 * 应付金额
	 */
	private String totalFee;
	
	/**
	 * 实付金额
	 */
	private String payment;
	
	/**
	 * 系统优惠金额
	 */
	private String discountFee;
	
	/**
	 * 卖家优惠金额
	 */
	private String  adjustFee;
	
	/**
	 * 订单状态
	 */
	private String status;
	
	/**
	 * 订单快照地址
	 */
	private String snapshotUrl;
	
	/**
	 * 超时到期时间
	 */
	private Date timeoutActionTime;
	
	/**
	 * 订单快照 add by jeck 2009-04-13
	 */
	private String snapshot;

	/**
	 * 商品备注 专用
	 */
	private String itemMemo;
	
	private Date modified;
	
	public String getItemMemo() {
		return itemMemo;
	}

	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	
	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}

	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}


	public String getItemMealName() {
		return itemMealName;
	}


	public void setItemMealName(String itemMealName) {
		this.itemMealName = itemMealName;
	}


	public String getSnapshot() {
		return snapshot;
	}


	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}


	public String getOuterSkuId() {
		return outerSkuId;
	}


	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}


	public String getOuterIid() {
		return outerIid;
	}


	public void setOuterIid(String outerIid) {
		this.outerIid = outerIid;
	}


	/**
	 * @return the iid
	 */
	public String getIid() {
		return iid;
	}


	/**
	 * @param iid the iid to set
	 */
	public void setIid(String iid) {
		this.iid = iid;
	}


	/**
	 * @return the skuId
	 */
	public String getSkuId() {
		return skuId;
	}


	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}


	/**
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}


	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}


	/**
	 * @return the picPath
	 */
	public String getPicPath() {
		return picPath;
	}


	/**
	 * @param picPath the picPath to set
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	/**
	 * @deprecated replaced by getOid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @deprecated replaced by setOid
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOid() {
		return tid;
	}

	public void setOid(String oid) {
		this.tid = oid;
	}

	/**
	 * @return the refundStatus
	 */
	public String getRefundStatus() {
		return refundStatus;
	}


	/**
	 * @param refundStatus the refundStatus to set
	 */
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}


	/**
	 * @return the sellerNick
	 */
	public String getSellerNick() {
		return sellerNick;
	}


	/**
	 * @param sellerNick the sellerNick to set
	 */
	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}


	/**
	 * @return the buyerNick
	 */
	public String getBuyerNick() {
		return buyerNick;
	}


	/**
	 * @param buyerNick the buyerNick to set
	 */
	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}


	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}


	public String getTotalFee() {
		return totalFee;
	}


	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getDiscountFee() {
		return discountFee;
	}


	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}


	public String getAdjustFee() {
		return adjustFee;
	}


	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSnapshotUrl() {
		return snapshotUrl;
	}


	public void setSnapshotUrl(String snapshotUrl) {
		this.snapshotUrl = snapshotUrl;
	}


	public Date getTimeoutActionTime() {
		return timeoutActionTime;
	}


	public void setTimeoutActionTime(Date timeoutActionTime) {
		this.timeoutActionTime = timeoutActionTime;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
}
