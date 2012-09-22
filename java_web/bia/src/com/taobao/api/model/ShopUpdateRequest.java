/**
 * 
 */
package com.taobao.api.model;

/**
 * 店铺更新请求对象
 * 
 * Call the taobao.shop.update to update the data of the seller's shop
 * 
 * @author biyi
 */
public class ShopUpdateRequest extends TaobaoRequest {

	private static final long serialVersionUID = 4600159182907165035L;

	/**
	 * 店铺标题
	 */
	private String title;

	/**
	 * 店铺描述
	 */
	private String desc;

	/**
	 * 店铺公告
	 */
	private String bulletin;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBulletin() {
		return bulletin;
	}

	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public ShopUpdateRequest withTitle(String title) {
		setTitle(title);
		return this;
	}

	public ShopUpdateRequest withDesc(String desc) {
		setDesc(desc);
		return this;
	}

	public ShopUpdateRequest withBulletin(String bulletin) {
		setBulletin(bulletin);
		return this;
	}
}
