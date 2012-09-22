/**
 * 
 */
package com.taobao.api;

/**
 * 目前提供的API
 * 
 * @author sulinchong.pt 2008-10-6
 */
public enum TaobaoApiMethod {
	// 商品类API
	ITEM_ADD("taobao.item.add"), 
	ITEM_UPDATE("taobao.item.update"), 
	ITEM_GET("taobao.item.get"), 
	ITEMS_GET("taobao.items.get"), 
	ITEMS_SEARCH("taobao.items.search"),
	ITEMS_ONSALE_GET("taobao.items.onsale.get"), 
	ITEMS_ALL_GET("taobao.items.all.get"), 
	ITEMS_INVENTORY_GET("taobao.items.inventory.get"),
	ITEM_UPDATE_LISTING("taobao.item.update.listing"),
	ITEM_UPDATE_DELISTING("taobao.item.update.delisting"),
	ITEM_RECOMMEND_ADD("taobao.item.recommend.add"),
	ITEM_RECOMMEND_DELETE("taobao.item.recommend.delete"),
	ITEM_DOWNLOAD("taobao.items.download"),
	ITEM_IMG_UPLOAD("taobao.item.img.upload"),
	ITEM_IMG_DELETE("taobao.item.img.delete"),
	ITEM_PROPIMG_UPLOAD("taobao.item.propimg.upload"),
	ITEM_PROPIMG_DELETE("taobao.item.propimg.delete"),
	ITEM_SKU_ADD("taobao.item.sku.add"),
	ITEM_SKU_GET("taobao.item.sku.get"),
	ITEM_SKU_UPDATE("taobao.item.sku.update"),
	ITEM_SKUS_GET("taobao.item.skus.get"),
	ITEMS_CUSTOM_GET("taobao.items.custom.get"), 
	ITEM_VIDEO_UPLOAD("taobao.item.video.upload"),
	ITEM_VIDEO_DELETE("taobao.item.video.delete"),
	SKUS_CUSTOM_GET("taobao.skus.custom.get"),
	// 交易类API
	TRADES_GET("taobao.trades.get"), 
	TRADES_SOLD_GET("taobao.trades.sold.get"), 
	TRADES_SOLD_INCREMENT_GET("taobao.trades.sold.increment.get"), 
	TRADES_BOUGHT_GET("taobao.trades.bought.get"), 
	TRADE_GET("taobao.trade.get"),
	POSTAGE_GET("taobao.postage.get"),
	POSTAGES_GET("taobao.postages.get"),
	POSTAGE_ADD("taobao.postage.add"),
	POSTAGE_UPDATE("taobao.postage.update"),
	POSTAGE_DELETE("taobao.postage.delete"),
	ORDERS_GET("taobao.orders.get"),
	TRADE_CLOSE("taobao.trade.close"),
	TRADE_FULLINFO_GET("taobao.trade.fullinfo.get"), //获取交易的所有信息
	TRADE_MEMO_ADD("taobao.trade.memo.add"),//插入交易备注
	TRADE_MEMO_UPDATE("taobao.trade.memo.update"),//更新交易备注
	TRADE_NBSSNAP_GET("taobao.trade.nbssnap.get"), //取到交易的快照信息
	TRADE_SNAPSHOT_GET("taobao.trade.snapshot.get"),
	TRADE_CONFIRMFEE_GET("taobao.trade.confirmfee.get"),
	TRADE_SHIPPING_ADDRESS_UPDATE("taobao.trade.shippingaddress.update"),
	TRADE_ORDER_SKU_UPDATE("taobao.trade.ordersku.update"),

	// 用户类API
	USER_GET("taobao.user.get"), 
	USER_DETAIL_GET("taobao.user.detail.get"),
	USERS_GET("taobao.users.get"),
						
	SHIPPING_ADDRESSES("taobao.shipping.addresses.get"),
	// 类目属性API
	ITEMCATS_GET("taobao.itemcats.get"), 
	ITEMCATS_LIST_GET("taobao.itemcats.list.get"), 
	SHOPCATS_LIST_GET("taobao.shopcats.list.get"), 
	SELLERCATS_LIST_GET("taobao.sellercats.list.get"), 
	ITEMPROPS_GET("taobao.itemprops.get"), 
	ITEMPROPS_LIST_GET("taobao.itemprops.list.get"), 
	SPU_GET("taobao.spu.get"), 
	ITEMPROP_GET("taobao.itemprop.get"), 
	ITEMPROP_LIST_GET("taobao.itemprop.list.get"),
	ITEMPROPVALUES_GET("taobao.itempropvalues.get"),
	ITECAT_FEATURES_GET("taobao.itemcat.features.get"),
	//物流API
	LOGISTICS_ORDERS_GET("taobao.logistics.orders.get"),
	LOGISTICS_ORDERS_DETAIL_GET("taobao.logistics.orders.detail.get"),
	AREAS_GET("taobao.areas.get"),
	//旧的物流公司查询
	LOGISTICS_COMPPANIES_GET_NEW("taobao.logistics.companies.get"),
	DELIVERY_SEND("taobao.delivery.send"),
	//店铺API					 
	SHOP_GET("taobao.shop.get"), 
	SHOP_UPDATE("taobao.shop.update"),
	SHOP_SHOWCASE_REMAINCOUNT("taobao.shop.showcase.remainCount"),
	SHOP_SELLERCAT_INSERT("taobao.sellercats.list.add"),
	SHOP_SELLERCAT_UPDATE("taobao.sellercats.list.update"),
	//评价API
	TRADERATES_GET("taobao.traderates.get"),
	TRADERATE_ADD("taobao.traderate.add"),
	TRADERATE_LIST_ADD("taobao.traderate.list.add"),
	//Product API
	PRODUCT_ADD("taobao.product.add"),
	PRODUCT_UPDATE("taobao.product.update"),
	PRODUCT_GET("taobao.product.get"),
	PRODUCT_IMG_UPLOAD("taobao.product.img.upload"),
	PRODUCT_IMG_DELETE("taobao.product.img.delete"),
	PRODUCT_PROPIMG_UPLOAD("taobao.product.propimg.upload"),
	PRODUCT_PROPIMG_DELETE("taobao.product.propimg.delete"),
	PRODUCTS_SEARCH("taobao.products.search"),
	PRODUCTS_GET("taobao.products.get"),
	TIME_MAIN("taobao.time.get"),
	
	//itemextra API
	ITEMEXTRA_GET("taobao.itemextra.get"),
	ITEMEXTRA_ADD("taobao.itemextra.add"),
	ITEMEXTRA_UPDATE("taobao.itemextra.update"),
	
	// Combo API
	SUITES_GET("taobao.suites.get"),
	VAS_SUITES_GET("taobao.vas.suites.get"),
	// upp 收费
	SUB_PROD_GET("taobao.vas.product.get"),
	
	//refund message api
	REFUND_MESSAGES_GET("taobao.refund.messages.get"),
	REFUND_MESSAGE_ADD("taobao.refund.message.add"),
	
	//refund API
	REFUND_GET("taobao.refund.get"),
	REFUNDS_APPLY_GET("taobao.refunds.apply.get"),
	REFUNDS_RECIEVE_GET("taobao.refunds.receive.get"),
	
	// 卖家授权信息 API
	ITEMCATS_AUTHORIZE_GET("taobao.itemcats.authorize.get"),
	
	//refund message api
	//图片关联
	ITEM_JOINT_IMG("taobao.item.joint.img"),
	ITEM_JOINT_PROPIMG("taobao.item.joint.propimg"),
	
	GUARANTY_FUNDS_GET("taobao.guarantyfunds.get"),
	
	SUBAPP_APPLY("taobao.subapp.apply"),
	
	//Added for ProductStat
	PRODUCTSTAT_SEARCH("taobao.productstat.search"),
	PRODUCTSTAT_GET("taobao.productstat.get"),
	PRODUCT_PRICE_STAT_GET("taobao.productpricestat.get");
	
	private String method;

	private TaobaoApiMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return method;
	}
}
