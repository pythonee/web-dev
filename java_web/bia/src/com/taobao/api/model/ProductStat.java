package com.taobao.api.model;

import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;

/**
 * 代表一个产品统计信息
 * 
 * @author hukui
 * 
 */
public class ProductStat extends TaobaoModel {

	private static final long serialVersionUID = -941538383160471874L;

	private static final String PRODUCT_ID = "product_id"; // 产品id 对应属性名。

	private static final String NAME = "name"; // 产品名称对应属性名。

	private static final String DESC = "desc"; // 产品描述对应属性名。

	private static final String PIC_PATH = "pic_path"; // 产品的主图片地址对应属性名。

	private static final String CID = "cid"; // 商品类目ID对应属性名。

	private static final String PROPS = "props"; // 产品的关键属性列表对应属性名。

	private static final String BINDS = "binds"; // 产品的非关键属性列表对应属性名。

	private static final String PRICE = "price"; // 产品的市场价对应属性名。

	private static final String SHOP_PRICE = "shop_price"; // 店铺保修价对应属性名。

	private static final String STANDARD_PRICE = "standard_price"; // 全国联保价对应属性名。

	private static final String RATE_NUM = "rate_num"; // 评论数对应属性名。

	private static final String COLLECT_NUM = "collect_num"; // 收藏数对应属性名。

	private static final String SALE_NUM = "sale_num"; // 销量对应属性名。

	private static final String LEVEL = "level"; // 星级对应属性名。

	private String productId; // 产品id。

	private String name; // 产品名称。

	private String desc; // 产品描述。

	private String picPath; // 产品的主图片地址。(绝对地址,格式:http://host/image_path)。

	private String cid; // 商品类目ID。必须是叶子类目ID。

	private String props; // 产品的关键属性列表.格式：pid:vid;pid:vid。

	private String binds; // 产品的非关键属性列表.格式:pid:vid;pid:vid。

	private String price; // 产品的市场价,单位为元。精确到2位小数;如:200.07。

	private String shopPrice; // 店铺保修价。

	private String standarPrice; // 全国联保价。

	private long rateNumber; // 评论数。

	private long collectNumber; // 收藏数。

	private long saleNumber; // 销量。

	private int level; // 星级。

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
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

	public String getBinds() {
		return binds;
	}

	public void setBinds(String binds) {
		this.binds = binds;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(String shopPrice) {
		this.shopPrice = shopPrice;
	}

	public String getStandarPrice() {
		return standarPrice;
	}

	public void setStandarPrice(String standarPrice) {
		this.standarPrice = standarPrice;
	}

	public long getRateNumber() {
		return rateNumber;
	}

	public void setRateNumber(long rateNumber) {
		this.rateNumber = rateNumber;
	}

	public long getCollectNumber() {
		return collectNumber;
	}

	public void setCollectNumber(long collectNumber) {
		this.collectNumber = collectNumber;
	}

	public long getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(long saleNumber) {
		this.saleNumber = saleNumber;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 把JSON格式转成ProductStat对象
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static ProductStat convertJsonToObject(JSONObject json) throws JSONException {

		ProductStat product = new ProductStat();
		if (json == null) {
			return product;
		}

		if (json.has(ProductStat.BINDS)) {
			product.setBinds(json.getString(ProductStat.BINDS));
		}

		if (json.has(ProductStat.CID)) {
			product.setCid(json.getString(ProductStat.CID));
		}

		if (json.has(ProductStat.COLLECT_NUM)) {
			product.setCollectNumber(json.getLong(ProductStat.COLLECT_NUM));
		}

		if (json.has(ProductStat.DESC)) {
			product.setDesc(json.getString(ProductStat.DESC));
		}

		if (json.has(ProductStat.LEVEL)) {
			product.setLevel(json.getInt(ProductStat.LEVEL));
		}

		if (json.has(ProductStat.NAME)) {
			product.setName(json.getString(ProductStat.NAME));
		}

		if (json.has(ProductStat.PIC_PATH)) {
			product.setPicPath(json.getString(ProductStat.PIC_PATH));
		}

		if (json.has(ProductStat.PRICE)) {
			product.setPrice(json.getString(ProductStat.PRICE));
		}

		if (json.has(ProductStat.PRODUCT_ID)) {
			product.setProductId(json.getString(ProductStat.PRODUCT_ID));
		}

		if (json.has(ProductStat.PROPS)) {
			product.setProps(json.getString(ProductStat.PROPS));
		}

		if (json.has(ProductStat.RATE_NUM)) {
			product.setRateNumber(json.getLong(ProductStat.RATE_NUM));
		}

		if (json.has(ProductStat.SALE_NUM)) {
			product.setSaleNumber(json.getLong(ProductStat.SALE_NUM));
		}

		if (json.has(ProductStat.SHOP_PRICE)) {
			product.setShopPrice(json.getString(ProductStat.SHOP_PRICE));
		}

		if (json.has(ProductStat.STANDARD_PRICE)) {
			product.setStandarPrice(json.getString(ProductStat.STANDARD_PRICE));
		}

		return product;
	}

}
