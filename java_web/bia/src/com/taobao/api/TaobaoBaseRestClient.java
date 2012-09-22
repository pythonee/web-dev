package com.taobao.api;

import com.taobao.api.model.*;

/**
 * 淘宝对外部提供的REST API客户端
 * 
 * 注意，默认是SIP入口。使用淘宝API入口需要设置： 4
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 */
public class TaobaoBaseRestClient extends AbstractTaobaoRestClient implements
		TaobaoRestClient {

	protected AbstractTaobaoRestClient legacyClient;
	protected TaobaoAlmightyClient almightyClient;

	public TaobaoBaseRestClient(String appkey, String secret)
			throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public TaobaoBaseRestClient(String appkey, String secret, boolean isSandbox)
			throws TaobaoApiException {
		this(isSandbox ? ApiConstants.API_SANDBOX_SERVICE_URL
				: ApiConstants.API_SERVICE_URL, appkey, secret);
	}

	public TaobaoBaseRestClient(String serviceUrl, String appkey, String secret)
			throws TaobaoApiException {
		this(serviceUrl, ApiConstants.DEFAULT_SERVICE_VERSION, appkey, secret);
	}

	public TaobaoBaseRestClient(String serviceUrl, String version,
			String appkey, String secret) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
		this.legacyClient = new LegacyTaobaoJsonRestClient(serviceUrl, "1.0",
				appkey, secret);
		almightyClient = new TaobaoAlmightyClient(legacyClient, version);
	}

	protected TaobaoBaseRestClient(String serviceUrl, String version,
			String appkey, String secret, AbstractTaobaoRestClient legacyClient)
			throws TaobaoApiException {
		this(serviceUrl, version, appkey, secret, legacyClient, null);
	}
	
	protected TaobaoBaseRestClient(String serviceUrl, String version,
			String appkey, String secret, AbstractTaobaoRestClient legacyClient, String signMethod) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret, signMethod);
		
		this.legacyClient = legacyClient;
		almightyClient = new TaobaoAlmightyClient(legacyClient, version);
	}

	protected TaobaoBaseRestClient(String appkey, String secret,
			boolean isSandbox, AbstractTaobaoRestClient legacyClient) throws TaobaoApiException {
		this(isSandbox ? ApiConstants.API_SANDBOX_SERVICE_URL
				: ApiConstants.API_SERVICE_URL, "2.0", appkey, secret, legacyClient);
	}

	protected TaobaoAlmightyClient getAlmightyClient() {
		return almightyClient;
	}

	protected String getApiKeyName() {
		return legacyClient.getApiKeyName();
	}

	protected String getMethodName() {
		return legacyClient.getMethodName();
	}

	protected String getSignName() {
		return legacyClient.getSignName();
	}

	protected String getSessionIdName() {
		return legacyClient.getSessionIdName();
	}

	protected String getTimestampName() {
		return legacyClient.getTimestampName();
	}

	public boolean equals(Object obj) {
		return legacyClient.equals(obj);
	}

	public FeaturesGetResponse itemCatFeaturesGet(FeaturesGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemcatFeaturesGet", req,
				FeaturesGetResponse.class);
	}

	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("itemcatsGet", req, ItemCatsResponse.class);
	}

	public ItemCatsResponse itemCatsGetV2(ItemCatsGetV2Request req) throws TaobaoApiException {
		return itemCatsGet(req);
	}

	public ItemCatsListResponse itemCatsListGet(ItemCatsListGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemcatsListGet", req, ItemCatsListResponse.class);
	}

	public ItemGetResponse itemGet(ItemGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemGet", req, ItemGetResponse.class);
	}

	public ItemPropResponse itemPropListGet(ItemPropRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("itempropListGet", req, ItemPropResponse.class);
	}

	public ItemPropValuesResponse itemPropValuesGet(ItemPropValuesGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("itempropvaluesGet", req,
				ItemPropValuesResponse.class);
	}

	public ItemPropsResponse itemPropsGet(ItemPropsRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("itempropsGet", req, ItemPropsResponse.class);
	}

	public ItemPropsResponse itemPropsGetV2(ItemPropsV2Request req) throws TaobaoApiException {
		return itemPropsGet(req);
	}

	public ItemPropsResponse itemPropsListGet(ItemPropsRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("itempropsListGet", req, ItemPropsResponse.class);
	}

	public ItemsGetResponse itemsGet(ItemsGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("itemsGet", req, ItemsGetResponse.class);
	}

	public ItemsSearchResponse itemsSearch(ItemsSearchRequest itemsSearchRequest)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemsSearch", itemsSearchRequest,
				ItemsSearchResponse.class);
	}

	public OrdersGetResponse ordersGet(OrdersGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("ordersGet", req,
				OrdersGetResponse.class);
	}

	public PostageGetResponse postageGet(PostageGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("postageGet", req,
				PostageGetResponse.class);
	}

	public ProductGetResponse productGet(ProductGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("productGet", req,
				ProductGetResponse.class);
	}

	public ProductPriceStatGetResponse productPriceStatGet(
			ProductPriceStatGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("productpricestatGet", req,
				ProductPriceStatGetResponse.class);
	}

	public ProductSearchResponse productSearch(ProductSearchRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("productsSearch", req,
				ProductSearchResponse.class);
	}

	public ProductStatGetResponse productStatGet(ProductStatGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("productstatGet", req,
				ProductStatGetResponse.class);
	}

	// ===============================比比价API====================================

	public ProductStatSearchResponse productStatSearch(
			ProductStatSearchRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("productstatSearch", req,
				ProductStatSearchResponse.class);
	}

	public ProductsGetResponse productsGet(ProductsGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("productsGet", req,
				ProductsGetResponse.class);
	}

	public SellerCatsListGetResponse sellerCatsListGet(
			SellerCatsListGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("sellercatsListGet", req,
				SellerCatsListGetResponse.class);
	}

	public ShopCatsListGetResponse shopCatsListGet() throws TaobaoApiException {
		return almightyClient.doRequest("shopcatsListGet",
				ShopCatsListGetResponse.class);
	}

	public ShopGetResponse shopGet(ShopGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("shopGet", req, ShopGetResponse.class);
	}

	public SkuGetResponse skuGet(SkuGetRequest req) throws TaobaoApiException {
		return almightyClient
				.doRequest("itemSkuGet", req, SkuGetResponse.class);
	}

	public SkusGetResponse skusGet(SkusGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemSkusGet", req,
				SkusGetResponse.class);
	}

	public SpuGetResponse spuGet(SpuGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("spuGet", req, SpuGetResponse.class);
	}

	public TimegetResponse timeget() throws TaobaoApiException {
		return almightyClient.doRequest("timeget", TimegetResponse.class);
	}

	public TradeGetResponse tradeGet(TradeGetRequest req)
			throws TaobaoApiException {
		return almightyClient
				.doRequest("tradeGet", req, TradeGetResponse.class);
	}

	public TradesGetResponse tradesGet(TradesGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("tradesGet", req,
				TradesGetResponse.class);
	}

	public UserGetResponse userGet(UserGetRequest req)
			throws TaobaoApiException {
		return almightyClient.doRequest("userGet", req, UserGetResponse.class);
	}

	public UsersGetResponse usersGet(UsersGetRequest req)
			throws TaobaoApiException {
		return almightyClient
				.doRequest("usersGet", req, UsersGetResponse.class);
	}

	public GuarantyFundsGetResponse guarantyFundsGet(
			GuarantyFundsGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("guarantyfundsGet", req,
				GuarantyFundsGetResponse.class, sessionId);
	}

	public ItemAddResponse itemAdd(ItemAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemAdd", req, ItemAddResponse.class,
				sessionId, req.getImage());
	}
	
	public ItemAddResponse itemGameAdd(ItemGameAddRequest req, String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemGameAdd", req, ItemAddResponse.class,
				sessionId, req.getVerticalImage());
	}

	public ItemCatsAuthorizeGetResponse itemCatsAuthorizeGet(
			ItemCatsAuthorizeGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemcatsAuthorizeGet", req,
				ItemCatsAuthorizeGetResponse.class, sessionId);
	}

	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemCatsGet", req,
				ItemCatsResponse.class, sessionId);
	}

	public ItemGetResponse itemGet(ItemGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemGet", req, ItemGetResponse.class,
				sessionId);
	}

	public ItemImgDeleteResponse itemImgDelete(ItemImgDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemImgDelete", req,
				ItemImgDeleteResponse.class, sessionId);
	}

	public ItemImgUploadResponse itemImgUpload(ItemImgUploadRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemImgUpload", req,
				ItemImgUploadResponse.class, sessionId, req.getImage());
	}

	public ItemPropResponse itemPropGet(ItemPropRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemPropGet", req,
				ItemPropResponse.class, sessionId);
	}

	public ItemPropsResponse itemPropsGet(ItemPropsRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemPropsGet", req,
				ItemPropsResponse.class, sessionId);
	}

	public ItemRecommendAddResponse itemRecommendAdd(
			ItemRecommendAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemRecommendAdd", req,
				ItemRecommendAddResponse.class, sessionId);
	}

	public ItemRecommendDeleteResponse itemRecommendDelete(
			ItemRecommendDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemRecommendDelete", req,
				ItemRecommendDeleteResponse.class, sessionId);
	}

	public ItemUpdateResponse itemUpdate(ItemUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemUpdate", req,
				ItemUpdateResponse.class, sessionId, req.getImage());
	}

	public ItemUpdateDelistingResponse itemUpdateDelisting(
			ItemUpdateDelistingRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemUpdateDelisting", req,
				ItemUpdateDelistingResponse.class, sessionId);
	}

	public ItemUpdateListingResponse itemUpdateListing(
			ItemUpdateListingRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemUpdateListing", req,
				ItemUpdateListingResponse.class, sessionId);
	}

	public ItemUpdateRevokeShowcaseResponse itemUpdateRevokeShowcase(
			ItemUpdateRevokeShowcaseRequest req, String sessionId)
			throws TaobaoApiException {
		return new ItemUpdateRevokeShowcaseResponse(itemRecommendDelete(req, sessionId));
	}

	public ItemUpdateShowcaseResponse itemUpdateShowcase(
			ItemUpdateShowcaseRequest req, String sessionId)
			throws TaobaoApiException {
		return new ItemUpdateShowcaseResponse(itemRecommendAdd(req, sessionId));
	}

	public ItemsGetResponse itemsAllGet(ItemsAllGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemsAllGet", req,
				ItemsGetResponse.class, sessionId);
	}

	public ItemsCustomGetResponse itemsCustomGet(ItemsCustomGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemsCustomGet", req,
				ItemsCustomGetResponse.class, sessionId);
	}

	public FullitemsGetResponse fullitems(FullitemsGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new FullitemsGetResponse(itemsCustomGet(req, sessionId));
	}

	public ItemsGetResponse itemsInStockGet(ItemsInStockGetRequest req,
			String sessionId) throws TaobaoApiException {
		return itemsInventoryGet(req, sessionId);
	}

	public ItemsGetResponse itemsInventoryGet(ItemsInventoryGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemsInventoryGet", req,
				ItemsGetResponse.class, sessionId);
	}

	public ItemsGetResponse itemsOnSaleGet(ItemsOnSaleGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemsOnsaleGet", req,
				ItemsGetResponse.class, sessionId);
	}

	public ItemsOutGetResponse itemsOutGet(ItemsOutGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new ItemsOutGetResponse(itemsCustomGet(req, sessionId));
	}

	public LogisticsOrdersDetailGetResponse logisticsOrdersDetailGet(
			LogisticsOrdersDetailGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("logisticsOrdersDetailGet", req,
				LogisticsOrdersDetailGetResponse.class, sessionId);
	}

	public LogisticsOrdersGetResponse logisticsOrdersGet(
			LogisticsOrdersGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("logisticsOrdersGet", req,
				LogisticsOrdersGetResponse.class, sessionId);
	}

	public LogisticsCompaniesGetResponse logisticsCompaniesGet(
			LogisticsCompaniesGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("logisticsCompaniesGet", req,
				LogisticsCompaniesGetResponse.class);
	}

	public AreasGetResponse areasGet(AreasGetRequest req)
			throws TaobaoApiException {
		return almightyClient
				.doRequest("areasGet", req, AreasGetResponse.class);
	}

	public DeliverySendResponse deliverySend(DeliverySendRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("deliverySend", req,
				DeliverySendResponse.class, sessionId);
	}

	public DeliveryCodSendResponse deliveryCodSend(DeliveryCodSendRequest req, String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("deliveryCodSend", req,
				DeliveryCodSendResponse.class, sessionId);
	}
	public LogisticsPartnersGetResponse logisticsPartnersGet(LogisticsPartnersGetRequest req) throws TaobaoApiException {
		return almightyClient.doRequest("logisticsPartnersGet", req,
				LogisticsPartnersGetResponse.class);
	}
	public LogisticCompaniesGetResponse logisticCompaniesGet(
			LogisticCompaniesGetRequest req) throws TaobaoApiException {
		return new LogisticCompaniesGetResponse(logisticsCompaniesGet(req));
	}

	public ShippingsSendFullInfoGetResponse shippingsSendFullInfoGet(
			ShippingsSendFullInfoGetRequest req, String sessionId) throws TaobaoApiException {
		return new ShippingsSendFullInfoGetResponse(logisticsOrdersDetailGet(req, sessionId));
	}

	public ShippingsSendGetResponse shippingsSendGet(ShippingsSendGetRequest req, String sessionId)
			throws TaobaoApiException {
		return new ShippingsSendGetResponse(logisticsOrdersGet(req, sessionId));
	}

	public PostageAddResponse postageAdd(PostageAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("postageAdd", req,
				PostageAddResponse.class, sessionId);
	}

	public PostageDeleteResponse postageDelete(PostageDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("postageDelete", req,
				PostageDeleteResponse.class, sessionId);
	}

	public PostageUpdateResponse postageUpdate(PostageUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("postageUpdate", req,
				PostageUpdateResponse.class, sessionId);
	}

	public PostagesGetResponse postagesGet(PostagesGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("postagesGet", req,
				PostagesGetResponse.class, sessionId);
	}

	public ProductAddResponse productAdd(ProductAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("productAdd", req,
				ProductAddResponse.class, sessionId, req.getImage());
	}

	public ProductImgDeleteResponse productImgDelete(
			ProductImgDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("productImgDelete", req,
				ProductImgDeleteResponse.class, sessionId);
	}

	public ProductImgUploadResponse productImgUpload(
			ProductImgUploadRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("productImgUpload", req,
				ProductImgUploadResponse.class, sessionId, req.getImage());
	}

	public ProductPropImgDeleteResponse productPropImgDelete(
			ProductPropImgDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("productPropimgDelete", req,
				ProductPropImgDeleteResponse.class, sessionId);
	}

	public ProductPropImgUploadResponse productPropImgUpload(
			ProductPropImgUploadRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("productPropimgUpload", req,
				ProductPropImgUploadResponse.class, sessionId, req.getImage());
	}

	public ProductUpdateResponse productUpdate(ProductUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("productUpdate", req,
				ProductUpdateResponse.class, sessionId, req.getImage());
	}

	public PropImgDeleteResponse propImgDelete(PropImgDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemPropimgDelete", req,
				PropImgDeleteResponse.class, sessionId);
	}

	public PropimgUploadResponse propImgUpload(PropimgUploadRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("itemPropimgUpload", req,
				PropimgUploadResponse.class, sessionId, req.getImage());
	}

	public RefundGetResponse refundGet(RefundGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("refundGet", req,
				RefundGetResponse.class, sessionId);
	}

	public RefundMessageAddResponse refundMessageAdd(RefundMessageAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("refundMessageAdd", req, RefundMessageAddResponse.class,
				sessionId, req.getImage());
	}

	public RefundMessagesGetResponse refundMessagesGet(
			RefundMessagesGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("refundMessagesGet", req,
				RefundMessagesGetResponse.class, sessionId);
	}

	public RefundsApplyGetResponse refundsApplyGet(RefundsApplyGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("refundsApplyGet", req,
				RefundsApplyGetResponse.class, sessionId);
	}

	public RefundsReceiveGetResponse refundsReceiveGet(RefundsReceiveGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("refundsReceiveGet", req, RefundsReceiveGetResponse.class,
				sessionId);
	}

	public RefundsRecieveGetResponse refundsRecieveGet(RefundsRecieveGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new RefundsRecieveGetResponse(refundsReceiveGet(req, sessionId));
	}

	public SellerAuthorizeGetResponse sellerAuthorizeGet(SellerAuthorizeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new SellerAuthorizeGetResponse(itemCatsAuthorizeGet(req, sessionId));
	}

	public SellerCatsListAddResponse sellerCatsListAdd(
			SellerCatsListAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("sellercatsListAdd", req,
				SellerCatsListAddResponse.class, sessionId);
	}

	public SellerCatsListUpdateResponse sellerCatsListUpdate(
			SellerCatsListUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("sellercatsListUpdate", req,
				SellerCatsListUpdateResponse.class, sessionId);
	}

	public ShippingAddressesGetResponse shippingAddressesGet(
			ShippingAddressesGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("shippingAddressesGet", req,
				ShippingAddressesGetResponse.class, sessionId);
	}

	public ShopGetResponse shopGet(ShopGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("shopGet", req, ShopGetResponse.class,
				sessionId);
	}

	public ShopShowcaseRemainCountResponse shopShowcaseRemainCount(
			ShopShowcaseRemainCountRequest req, String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("shopShowcaseRemainCount", req,
				ShopShowcaseRemainCountResponse.class, sessionId);
	}

	public ShopUpdateResponse shopUpdate(ShopUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("shopUpdate", req,
				ShopUpdateResponse.class, sessionId);
	}

	public SkuAddResponse skuAdd(SkuAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemSkuAdd", req,
				SkuAddResponse.class, sessionId);
	}

	public SkuUpdateResponse skuUpdate(SkuUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("itemSkuUpdate", req,
				SkuUpdateResponse.class, sessionId);
	}

	public SkusCustomGetResponse skusCustomGet(SkusCustomGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("skusCustomGet", req,
				SkusCustomGetResponse.class, sessionId);
	}

	public SuitesGetResponse suitesGet(SuiteGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("suitesGet", req,
				SuitesGetResponse.class, sessionId);
	}

	public TradeCloseResponse tradeClose(TradeCloseRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("tradeClose", req,
				TradeCloseResponse.class, sessionId);
	}

	public TradeConfirmFeeGetResponse tradeConfirmFeeGet(
			TradeConfirmFeeGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("tradeConfirmfeeGet", req,
				TradeConfirmFeeGetResponse.class, sessionId);
	}

	public TradeGetResponse tradeFullInfoGet(TradeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeFullinfoGet", req,
				TradeGetResponse.class, sessionId);
	}

	public TradeGetResponse tradeGet(TradeGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("tradeGet", req,
				TradeGetResponse.class, sessionId);
	}

	public TradeMemoAddResponse tradeMemoAdd(TradeMemoAddRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeMemoAdd", req,
				TradeMemoAddResponse.class, sessionId);
	}

	public TradeMemoUpdateResponse tradeMemoUpdate(TradeMemoUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeMemoUpdate", req,
				TradeMemoUpdateResponse.class, sessionId);
	}

	public TradeRateAddResponse tradeRateAdd(TradeRateAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("traderateAdd", req, TradeRateAddResponse.class, sessionId);
	}

	public TradeRateListAddResponse tradeRateListAdd(
			TradeRateListAddRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("traderateListAdd", req, TradeRateListAddResponse.class, sessionId);
	}

	public TradeRatesGetResponse tradeRatesGet(TradeRatesGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("traderatesGet", req, TradeRatesGetResponse.class, sessionId);
	}

	public TradeGetResponse tradeSnapshotGet(TradeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeSnapshotGet", req,
				TradeGetResponse.class, sessionId);
	}

	public TradesGetResponse tradesBoughtGet(TradesBoughtGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradesBoughtGet", req,
				TradesGetResponse.class, sessionId);
	}

	public TradesGetResponse tradesSoldGet(TradesSoldGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradesSoldGet", req,
				TradesGetResponse.class, sessionId);
	}

	public TradesGetResponse tradesSoldIncrementGet(
			TradesSoldIncrementGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("tradesSoldIncrementGet", req,
				TradesGetResponse.class, sessionId);
	}

	public UserDetailGetResponse userDetail(UserDetailGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("userDetailGet", req,
				UserDetailGetResponse.class, sessionId);
	}
	
	public UserDetailGetResponse userDetailGet(UserDetailGetRequest request)
			throws TaobaoApiException {
		return userDetail(request, null);
	}

	public UserGetResponse userGet(UserGetRequest req, String sessionId)
			throws TaobaoApiException {
		return almightyClient.doRequest("userGet", req, UserGetResponse.class,
				sessionId);
	}

	public VasProductGetResponse vasProductGet(VasProductGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("vasProductGet", req,
				VasProductGetResponse.class, sessionId);
	}

	public VasSuitesgGetResponse vasSuitesGet(VasSuitesgGetRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("vasSuitesGet", req,
				VasSuitesgGetResponse.class, sessionId);
	}

	public TradeShippingAddressUpdateResponse tradeShippingaddressUpdate(
			TradeShippingAddressUpdateRequest req, String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeShippingaddressUpdate", req,
				TradeShippingAddressUpdateResponse.class, sessionId);
	}

	public TradeOrderSkuUpdateResponse tradeOrderskuUpdate(TradeOrderSkuUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		return almightyClient.doRequest("tradeOrderskuUpdate", req,
				TradeOrderSkuUpdateResponse.class, sessionId);
	}
	
	public ItemPropsVerticalGetResponse itemPropsVerticalGet(
			ItemPropsVerticalGetRequest request) throws TaobaoApiException {
		return almightyClient.doRequest("itempropsVerticalGet", request,ItemPropsVerticalGetResponse.class);
	}

	public String getVersion() {
		return almightyClient.getVersion();
	}

	public void setVersion(String version) {
		almightyClient.setVersion(version);
	}

	public String getFormat() {
		return almightyClient.getFormat();
	}

	public void setFormat(String format) {
		almightyClient.setFormat(format);
	}

	public void setUrlFetch(UrlFetch fetch) {
		super.setUrlFetch(fetch);
		legacyClient.setUrlFetch(fetch);
	}
	
}
