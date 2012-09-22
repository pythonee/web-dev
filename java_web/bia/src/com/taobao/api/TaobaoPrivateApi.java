/**
 * 
 */
package com.taobao.api;

import com.taobao.api.model.*;

/**
 * 隐私数据接口
 * 
 * 此接口中包括TopClient中所有的客户端API 每个API被调用后均返回一个Response结果，此结果中包括：错误码 code，错误信息
 * msg，重定向URL redirectUrl， 以及Http请求返回的结果的body部分 ，另包括调用这个API成功时返回的真实结果
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 */
public interface TaobaoPrivateApi {

	/**
	 * 此接口方法用以添加一个商品信息. apiType=2， 部分服务调用，需要签名校验和用户绑定,需要传入sessionid
	 * 
	 * @param itemAddRequest
	 * @param sessionId
	 * @return ItemAddResponse iid 商品id ; created 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemAddResponse itemAdd(ItemAddRequest itemAddRequest,
			String sessionId) throws TaobaoApiException;
	
	public ItemAddResponse itemGameAdd(ItemGameAddRequest req, String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以得到单个商品信息的公开信息和隐私信息。
	 * 
	 * @param itemGetRequest
	 * @return ItemGetResponse 包括Item
	 * @throws TaobaoApiException
	 */
	public ItemGetResponse itemGet(ItemGetRequest itemGetRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以更新商品信息.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @param itemUpdateRequest
	 * @param sessionId
	 * @return ItemUpdateResponse iid 商品;id modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemUpdateResponse itemUpdate(ItemUpdateRequest itemUpdateRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到当前会话用户（必须为卖家）的出售中商品列表.支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 需要传入sessionid
	 * 
	 * @param itemsOnSaleGetRequest
	 * @param sessionId
	 * @return ItemsGetResponse 返回搜索到的商品列表 List<Item> , 不包括desc字段
	 * @throws TaobaoApiException
	 */
	public ItemsGetResponse itemsOnSaleGet(
			ItemsOnSaleGetRequest itemsOnSaleGetRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索当前会话用户（必须为卖家）的库存商品,支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 需要传入sessionid
	 * 
	 * @deprecated 请使用itemsInventoryGet代替
	 * @param itemsInStockGetRequest
	 * @param sessionId
	 * @return ItemsGetResponse 返回搜索到的商品列表 List<Item> , 不包括desc字段
	 * @throws TaobaoApiException
	 */
	public ItemsGetResponse itemsInStockGet(ItemsInStockGetRequest req, String sessionId)
			throws TaobaoApiException;
	/**
	 * 此接口方法用以搜索当前会话用户（必须为卖家）的库存商品,支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 需要传入sessionid
	 * 
	 * @param itemsInStockGetRequest
	 * @param sessionId
	 * @return ItemsGetResponse 返回搜索到的商品列表 List<Item> , 不包括desc字段
	 * @throws TaobaoApiException
	 * 
	 */
	public ItemsGetResponse itemsInventoryGet(ItemsInventoryGetRequest req, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索当前会话用户做为买家达成的交易记录.支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 
	 * @param tradesRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradesGetResponse tradesBoughtGet(
			TradesBoughtGetRequest tradesRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索当前会话用户（做为卖家）已卖出的交易数据.支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 
	 * @param tradesRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradesGetResponse tradesSoldGet(TradesSoldGetRequest tradesRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索当前会话用户（做为卖家）已卖出的交易数据.支持分页.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradesGetResponse tradesSoldIncrementGet(TradesSoldIncrementGetRequest req,
			String sessionId) throws TaobaoApiException;
	
	/**
	 * 此接口方法以实现得到单个用户资料的公开信息和隐私信息。
	 * 
	 * @param userGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public UserGetResponse userGet(UserGetRequest userGetRequest,
			String sessionId) throws TaobaoApiException;
	
	/**
	 * @param userDetailGetRequest
	 * @param sessionId
	 * @return UserDetailGetRequest
	 * @throws TaobaoApiException
	 */
	public UserDetailGetResponse userDetail(UserDetailGetRequest userDetailGetRequest,String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品的标准商品类目.apiType=2， 部分服务调用，需要签名校验和用户绑定.
	 * 请使用不需要用户绑定的itemCatsGet接口
	 * 
	 * @deprecated 请使用不需要绑定session的接口
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest req, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品用的单个商品属性.apiType=2， 部分服务调用，需要签名校验和用户绑定
	 * 
	 * @param itemPropGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropResponse itemPropGet(ItemPropRequest itemPropGetRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品用的商品标准属性和属性值列表.apiType=2， 部分服务调用，需要签名校验和用户绑定
	 * 请使用不需要用户绑定的itemPropsGet接口
	 * 
	 * @deprecated 请使用不需要绑定session的接口
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropsResponse itemPropsGet(ItemPropsRequest req, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以批量查询卖家的物流订单,支持分页. apiType=2， 需要签名校验和用户绑定,需要传入sessionid
	 * 
	 * @deprecated 请使用logisticsOrderGet接口代替
	 * @param req
	 * @param sessionId
	 * @return ShippingsSendGetResponse shipping物流订单详情列表
	 * @throws TaobaoApiException
	 */
	public ShippingsSendGetResponse shippingsSendGet(ShippingsSendGetRequest req, String sessionId)
			throws TaobaoApiException;
	
	/**
	 * 此接口方法用以批量查询卖家的物流订单的详细信息 支持分页. apiType=2， 需要签名校验和用户绑定,需要传入sessionid
	 * @deprecated 请使用logisticsOrderDetailGet接口代替
	 * @param shippingsSendGetRequest
	 * @param sessionId
	 * @return ShippingsSendGetResponse
	 * @throws TaobaoApiException
	 */
	public ShippingsSendFullInfoGetResponse shippingsSendFullInfoGet(
			ShippingsSendFullInfoGetRequest req, String sessionId) throws TaobaoApiException;
	
	/**
	 * 此接口方法用于物流订单发货操作 apiType=2 需要签名校验和用户绑定，需要传入sessionid
	 * @param request
	 * @param sessionId
	 * @return DeliverySendResponse
	 * @throws TaobaoApiException
	 */
	public DeliverySendResponse deliverySend(DeliverySendRequest request,String sessionId) throws TaobaoApiException;



	public DeliveryCodSendResponse deliveryCodSend(DeliveryCodSendRequest request, String sessionId) throws TaobaoApiException ;
	

	/**
	 * 此接口方法用以查询店铺的公开信息。
	 * 
	 * @param shopGetRequest
	 * @return ShopGetResponse shop店铺对象列表
	 * @throws TaobaoApiException
	 */
	public ShopGetResponse shopGet(ShopGetRequest shopGetRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以修改店铺信息. apiType=3， 需要签名校验和用户绑定，需要传入sessionid
	 * 
	 * @param shopUpdateRequest
	 * @param sessionId
	 * @return ShopUpdateResponse 更新的shop店铺对象，包括：sid店铺id，modified修改时间
	 * @throws TaobaoApiException
	 */
	public ShopUpdateResponse shopUpdate(ShopUpdateRequest shopUpdateRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以查询店铺剩余橱窗个数. apiType=2, 需要签名校验和用户绑定，需要传入sessionid
	 * 
	 * @param shopShowcaseRemainCountRequest
	 * @param sessionId
	 * @return shopShowcaseRemainCountResponse remainShowcase剩余橱窗数
	 * @throws TaobaoApiException
	 */
	public ShopShowcaseRemainCountResponse shopShowcaseRemainCount(
			ShopShowcaseRemainCountRequest shopShowcaseRemainCountRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以查询卖家的交易评价列表,支持分页. apiType=1， 需要签名校验和用户绑定,需要传入sessionid
	 * 
	 * @param tradeRatesGetRequest
	 * @param sessionId
	 * @return TradeRatesGetResponse tradeRates评价信息列表
	 * @throws TaobaoApiException
	 */
	public TradeRatesGetResponse tradeRatesGet(
			TradeRatesGetRequest tradeRatesGetRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 交易确认收货费用查询
	 * 
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradeConfirmFeeGetResponse tradeConfirmFeeGet(
			TradeConfirmFeeGetRequest req, String sessionId)
			throws TaobaoApiException;
		
	/**
	 * 此接口方法用以增加交易评价. apiType=2， 需要签名校验和用户绑定，需要传入sessionid
	 * 
	 * @param tradeRateAddRequest
	 * @param sessionId
	 * @return TradeRateAddResponse tid交易id，created评价时间
	 * @throws TaobaoApiException
	 */
	public TradeRateAddResponse tradeRateAdd(
			TradeRateAddRequest tradeRateAddRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以批量增加交易评价. apiType=2， 需要签名校验和用户绑定，需要传入sessionid
	 * 
	 * @author liupo
	 * @param tradeRateListAddRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradeRateListAddResponse tradeRateListAdd(
			TradeRateListAddRequest tradeRateListAddRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以让商品上架.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @param itemUpdateListingRequest
	 * @param sessionId
	 * @return ItemUpdateListingResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemUpdateListingResponse itemUpdateListing(
			ItemUpdateListingRequest itemUpdateListingRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以让商品下架.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @param itemUpdateDelistingRequest
	 * @param sessionId
	 * @return ItemUpdateDelistingResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemUpdateDelistingResponse itemUpdateDelisting(
			ItemUpdateDelistingRequest itemUpdateDelistingRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以修改商品橱窗推荐状态让商品被推荐.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @deprecated 请使用itemRecommendAdd代替
	 * @param req
	 * @param sessionId
	 * @return ItemUpdateShowcaseResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemUpdateShowcaseResponse itemUpdateShowcase(ItemUpdateShowcaseRequest req,
			String sessionId) throws TaobaoApiException;

	/**
	 * 橱窗推荐
	 * 此接口方法用以修改商品橱窗推荐状态让商品被推荐.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @param req
	 * @param sessionId
	 * @return itemRecommendAddResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemRecommendAddResponse itemRecommendAdd(ItemRecommendAddRequest req, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以修改商品橱窗推荐状态让商品不被推荐.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @deprecated 请使用itemRecommendDelete代替
	 * @param req
	 * @param sessionId
	 * @return ItemUpdateRevokeShowcaseResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemUpdateRevokeShowcaseResponse itemUpdateRevokeShowcase(
			ItemUpdateRevokeShowcaseRequest req, String sessionId) throws TaobaoApiException;
	
	/**
	 * 取消橱窗推荐
	 * 此接口方法用以修改商品橱窗推荐状态让商品不被推荐.apiType=2， 部分服务调用，需要签名校验和用户绑定, 需要传入sessionid
	 * 
	 * @param req
	 * @param sessionId
	 * @return ItemRecommendDeleteResponse iid 商品id, modified 修改时间
	 * @throws TaobaoApiException
	 */
	public ItemRecommendDeleteResponse itemRecommendDelete(ItemRecommendDeleteRequest req,
			String sessionId) throws TaobaoApiException;

	/**
	 * 此接口方法用以得到单条交易信息的公开信息和隐私信息.apiType=3，部分服务调用，需要签名校验和用户绑定，需要传入sessionid
	 * 
	 * @param tradeGetRequest
	 * @param sessionId
	 * @return TradeGetResponse trade交易信息
	 * @throws TaobaoApiException
	 */
	public TradeGetResponse tradeGet(TradeGetRequest tradeGetRequest,
			String sessionId) throws TaobaoApiException;
	
	
	/**
	 * 此接口方法用以得到单条交易信息的所有信息
	 * 
	 * @param tradeGetRequest
	 * @return TradeGetResponse trade交易信息
	 * @throws TaobaoApiException
	 */
	public TradeGetResponse tradeFullInfoGet(TradeGetRequest tradeGetRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 
	 * 此接口用以得到卖家的运费模板. apiType=2,部分服务调用,需要签名校验和用户绑定,需要传入sessionid
	 * 
	 * @param postageGetRequest
	 * @param sessionId
	 * @return PostageGetResponse
	 * @throws TaobaoApiException
	 */
	public PostagesGetResponse postagesGet(
			PostagesGetRequest postageGetRequest, String sessionId)
			throws TaobaoApiException;
	
	/**
	 * 此接口用以卖家自己添加邮费模板 apiType=2
	 * @param request
	 * @param sessionId
	 * @return PostagesGetResponse
	 * @throws TaobaoApiException
	 */
	public PostageAddResponse postageAdd(PostageAddRequest request,String sessionId) throws TaobaoApiException;
	
	/**
	 * 此接口用于卖家更新自己的邮费模板 apiType=2
	 * @param request
	 * @param sessionId
	 * @return PostageUpdateResponse
	 * @throws TaobaoApiException
	 */
	public PostageUpdateResponse postageUpdate(PostageUpdateRequest request,String sessionId) throws TaobaoApiException;
	
	/**
	 * 此接口用于卖家删除自己的邮费模板 apiType = 2
	 * @param request
	 * @param sessionId
	 * @return PostageDeleteResponse
	 * @throws TaobaoApiException
	 */
	public PostageDeleteResponse postageDelete(PostageDeleteRequest request,String sessionId) throws TaobaoApiException;
	
	/**
	 * 此接口用于添加SKU apiType=2
	 * 
	 * @param skuAddRequest
	 * @param sessionId
	 * @return iid 商品的id ,create 创建时间
	 * @throws TaobaoApiException
	 */
	public SkuAddResponse skuAdd(SkuAddRequest skuAddRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 此接口用于修改SKU信息 apiType=2
	 * 
	 * @param skuAddRequest
	 * @param sessionId
	 * @return iid 商品id,nick 商品所属用户的昵称,modified sku的修改时间
	 * @throws TaobaoApiException
	 */
	public SkuUpdateResponse skuUpdate(SkuUpdateRequest skuUpdateRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * 添加商品图片 apiType=2
	 * 
	 * @param itemImgUploadRequest
	 * @return ItemImgUploadResponse
	 * @throws TaobaoApiException
	 */
	public ItemImgUploadResponse itemImgUpload(
			ItemImgUploadRequest itemImgUploadRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 删除商品图片 apiType=2
	 * 
	 * @param itemImgDeleteRequest
	 * @param sessionId
	 * @return ItemImgDeleteResponse
	 * @throws TaobaoApiException
	 */
	public ItemImgDeleteResponse itemImgDelete(
			ItemImgDeleteRequest itemImgDeleteRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 添加属性图片
	 * 
	 * @param propimgUploadRequest
	 * @param sessionId
	 * @return PropimgUploadResponse
	 * @throws TaobaoApiException
	 */
	public PropimgUploadResponse propImgUpload(
			PropimgUploadRequest propimgUploadRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 删除属性图片
	 * 
	 * @param propImgDeleteRequest
	 * @param sessionId
	 * @return PropImgDeleteResponse
	 * @throws TaobaoApiException
	 */
	public PropImgDeleteResponse propImgDelete(
			PropImgDeleteRequest propImgDeleteRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 新增店铺内部自定义类目
	 * 
	 * @author liupo
	 * @param sellerCatInsertRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public SellerCatsListAddResponse sellerCatsListAdd(
			SellerCatsListAddRequest sellerCatInsertRequest, String sessionId)
			throws TaobaoApiException;

	/**
	 * 修改店铺内部自定义类目
	 * 
	 * @author liupo
	 * @param sellerCatUpdateRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public SellerCatsListUpdateResponse sellerCatsListUpdate(
			SellerCatsListUpdateRequest sellerCatUpdateRequest, String sessionId)
			throws TaobaoApiException;

	

	/**
	 * 买家收货地址
	 * @param shippingAddressGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ShippingAddressesGetResponse shippingAddressesGet(ShippingAddressesGetRequest shippingAddressGetRequest,String sessionId)
			throws TaobaoApiException;

	/**
	 * 本接口用于获取用户已申请的退款列表，apptype=2，需要用户绑定
	 * @author sulinchong.pt 2009-2-19 上午10:29:40
	 * @param refundsApplyGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException 
	 */
	public RefundsApplyGetResponse refundsApplyGet(RefundsApplyGetRequest refundsApplyGetRequest, String sessionId) throws TaobaoApiException;
	
	/**
	 * 本接口用于获取用户收到的退款列表，apptype=2，需要用户绑定
	 */
	public RefundsReceiveGetResponse refundsReceiveGet(RefundsReceiveGetRequest request, String sessionId) throws TaobaoApiException;

	@Deprecated
	public RefundsRecieveGetResponse refundsRecieveGet(RefundsRecieveGetRequest request, String sessionId) throws TaobaoApiException;

	/**
	 * 本接口用于获取单笔退款的详细信息,apptype=2,需要用户绑定
	 * @author sulinchong.pt 2009-2-19 上午10:32:35
	 * @param refundGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException 
	 */
	public RefundGetResponse refundGet(RefundGetRequest refundGetRequest, String sessionId)throws TaobaoApiException;
	/**
	 * 本接口用于获取退款留言凭证列表的详细信息,apptype=2,需要用户绑定
	 * @param refundMessagesGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public RefundMessagesGetResponse refundMessagesGet(RefundMessagesGetRequest refundMessagesGetRequest, String sessionId)throws TaobaoApiException;
	/**
	 *  本接口用于新增退款留言凭证,apptype=2,需要用户绑定
	 * @param refundMessageAddRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public RefundMessageAddResponse refundMessageAdd(RefundMessageAddRequest refundMessageAddRequest, String sessionId) throws TaobaoApiException;

	/**
	 * 取套餐信息(旧版本) apiType = 2
	 * @param suiteGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public SuitesGetResponse suitesGet(SuiteGetRequest suiteGetRequest, String sessionId)  throws TaobaoApiException;
	
	/**
	 * 取套餐信息(新版本) apiType = 2
	 * @param suitesgGetRequest
	 * @param sessionId
	 * @return VasSuitesgGetResponse
	 * @throws TaobaoApiException
	 */
	public VasSuitesgGetResponse vasSuitesGet(VasSuitesgGetRequest suitesgGetRequest,String sessionId) throws TaobaoApiException;
	
	/**
	 * @param tradeCloseRequest
	 * @param sesssionID
	 * @return
	 * @throws TaobaoApiException
	 */
	public TradeCloseResponse tradeClose(TradeCloseRequest tradeCloseRequest, String sesssionID) throws TaobaoApiException;
	
	/**
	 * 新增一个产品（SPU），apiType=2
	 * 
	 * @author liupo
	 * @param productAddRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductAddResponse productAdd(ProductAddRequest productAddRequest,
			String sessionId) throws TaobaoApiException;
	
	/**
	 * 修改一个产品（SPU），apiType=2
	 * 
	 * @author liupo
	 * @param productUpdateRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductUpdateResponse productUpdate(
			ProductUpdateRequest productUpdateRequest, String sessionId)
			throws TaobaoApiException;
	
	/**
	 * 添加交易备注 apiType=2
	 * @author sulinchong.pt 2009-2-11 下午07:12:36
	 * @param tradeMemoAddRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException 
	 */
	public TradeMemoAddResponse tradeMemoAdd(TradeMemoAddRequest tradeMemoAddRequest, String sessionId)
		throws TaobaoApiException;
	
	/**
	 * 修改交易备注 apiType=2
	 * @author sulinchong.pt 2009-2-11 下午07:12:36
	 * @param tradeMemoUpdateRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException 
	 */
	public TradeMemoUpdateResponse tradeMemoUpdate(TradeMemoUpdateRequest tradeMemoUpdateRequest, String sessionId)
		throws TaobaoApiException;
	
	/**
	 * @deprecated 请使用itemsCustomGet接口代替
	 */
	public ItemsOutGetResponse itemsOutGet(ItemsOutGetRequest itemsOutGetRequest, String sessionId) 
		throws TaobaoApiException ;
	
	public ItemsCustomGetResponse itemsCustomGet(ItemsCustomGetRequest req, String sessionId) 
		throws TaobaoApiException ;
	
	/**
	 * 卖家授权信息 apiType=2
	 * 
	 * @deprecated 请使用itemCatsAuthorizeGet接口代替
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public SellerAuthorizeGetResponse sellerAuthorizeGet(SellerAuthorizeGetRequest req, String sessionId)
			throws TaobaoApiException;
	/**
	 * 卖家授权信息 apiType=2
	 * 
	 * @param req
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemCatsAuthorizeGetResponse itemCatsAuthorizeGet(ItemCatsAuthorizeGetRequest req, String sessionId)
		throws TaobaoApiException;
	
	/**
	 * 新增产品一个子图，apiType=2
	 * 
	 * @author liupo
	 * @param productImgUploadRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductImgUploadResponse productImgUpload(
			ProductImgUploadRequest productImgUploadRequest, String sessionId)
			throws TaobaoApiException;
	
	/**
	 * 新增一个产品属性图，apiType=2
	 * 
	 * @author liupo
	 * @param productPropImgUploadRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductPropImgUploadResponse productPropImgUpload(
			ProductPropImgUploadRequest productPropImgUploadRequest,
			String sessionId) throws TaobaoApiException;
	
	/**
	 * 删除产品子图 apiType=2
	 * 
	 * @author liupo
	 * @param productImgDeleteRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductImgDeleteResponse productImgDelete(
			ProductImgDeleteRequest productImgDeleteRequest,String sessionId)
			throws TaobaoApiException;

	/**
	 * 删除产品属性图 apiType=2
	 * 
	 * @author liupo
	 * @param productPropImgDeleteRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductPropImgDeleteResponse productPropImgDelete(
			ProductPropImgDeleteRequest productPropImgDeleteRequest,String sessionId)
			throws TaobaoApiException;

	/**
	 * 根据产品订购ID获取产品信息
	 * @param ippProductInfoRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public VasProductGetResponse vasProductGet(VasProductGetRequest vasProductGetRequest,String sessionId)throws TaobaoApiException;

	public ItemsGetResponse itemsAllGet(ItemsAllGetRequest itemsAllGetRequest,
			String sessionId) throws TaobaoApiException;

	/**
	 * B商家保证金查询。
	 * 
	 * @param request 保证金查询请求
	 * @param sessionId 会话ID
	 * @return B商家保证金使用记录
	 * @author fengsheng
	 * @throws TaobaoApiException
	 */
	public GuarantyFundsGetResponse guarantyFundsGet(
			GuarantyFundsGetRequest request, String sessionId)
			throws TaobaoApiException;
	
	/**
	 * 根据外部id取商品的sku
	 * @param skusCustomGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException
	 */
	public SkusCustomGetResponse skusCustomGet(SkusCustomGetRequest skusCustomGetRequest,
			String sessionId) throws TaobaoApiException;
	
	/**
	 * 获取交易快照
	 * @param tradeGetRequest
	 * @param sessionId
	 * @return
	 * @throws TaobaoApiException 
	 */
	public TradeGetResponse tradeSnapshotGet(TradeGetRequest tradeGetRequest, String sessionId) throws TaobaoApiException ;

	public LogisticsOrdersGetResponse logisticsOrdersGet(LogisticsOrdersGetRequest request,
			String sessionId) throws TaobaoApiException;

	public LogisticsOrdersDetailGetResponse logisticsOrdersDetailGet(
			LogisticsOrdersDetailGetRequest req, String sessionId) throws TaobaoApiException;
	
	/**
	 * 更改交易的收货地址。
	 * 
	 * @author fengsheng
	 */
	public TradeShippingAddressUpdateResponse tradeShippingaddressUpdate(TradeShippingAddressUpdateRequest req, String sessionId) throws TaobaoApiException;
	
	/**
	 * 更改订单的销售属性。
	 * 
	 * @author fengsheng
	 */
	public TradeOrderSkuUpdateResponse tradeOrderskuUpdate(TradeOrderSkuUpdateRequest req, String sessionId) throws TaobaoApiException;
}
