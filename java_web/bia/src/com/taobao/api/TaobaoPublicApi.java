/**
 * 
 */
package com.taobao.api;

import java.text.ParseException;

import com.taobao.api.model.AreasGetRequest;
import com.taobao.api.model.AreasGetResponse;
import com.taobao.api.model.FeaturesGetRequest;
import com.taobao.api.model.FeaturesGetResponse;
import com.taobao.api.model.ItemCatsGetRequest;
import com.taobao.api.model.ItemCatsGetV2Request;
import com.taobao.api.model.ItemCatsListGetRequest;
import com.taobao.api.model.ItemCatsListResponse;
import com.taobao.api.model.ItemCatsResponse;
import com.taobao.api.model.ItemGetRequest;
import com.taobao.api.model.ItemGetResponse;
import com.taobao.api.model.ItemPropRequest;
import com.taobao.api.model.ItemPropResponse;
import com.taobao.api.model.ItemPropValuesGetRequest;
import com.taobao.api.model.ItemPropValuesResponse;
import com.taobao.api.model.ItemPropsRequest;
import com.taobao.api.model.ItemPropsResponse;
import com.taobao.api.model.ItemPropsV2Request;
import com.taobao.api.model.ItemPropsVerticalGetRequest;
import com.taobao.api.model.ItemPropsVerticalGetResponse;
import com.taobao.api.model.ItemsGetRequest;
import com.taobao.api.model.ItemsGetResponse;
import com.taobao.api.model.ItemsSearchRequest;
import com.taobao.api.model.ItemsSearchResponse;
import com.taobao.api.model.LogisticCompaniesGetRequest;
import com.taobao.api.model.LogisticCompaniesGetResponse;
import com.taobao.api.model.LogisticsCompaniesGetRequest;
import com.taobao.api.model.LogisticsCompaniesGetResponse;
import com.taobao.api.model.LogisticsPartnersGetRequest;
import com.taobao.api.model.LogisticsPartnersGetResponse;
import com.taobao.api.model.OrdersGetRequest;
import com.taobao.api.model.OrdersGetResponse;
import com.taobao.api.model.PostageGetRequest;
import com.taobao.api.model.PostageGetResponse;
import com.taobao.api.model.ProductGetRequest;
import com.taobao.api.model.ProductGetResponse;
import com.taobao.api.model.ProductPriceStatGetRequest;
import com.taobao.api.model.ProductPriceStatGetResponse;
import com.taobao.api.model.ProductSearchRequest;
import com.taobao.api.model.ProductSearchResponse;
import com.taobao.api.model.ProductStatGetRequest;
import com.taobao.api.model.ProductStatGetResponse;
import com.taobao.api.model.ProductStatSearchRequest;
import com.taobao.api.model.ProductStatSearchResponse;
import com.taobao.api.model.ProductsGetRequest;
import com.taobao.api.model.ProductsGetResponse;
import com.taobao.api.model.SellerCatsListGetRequest;
import com.taobao.api.model.SellerCatsListGetResponse;
import com.taobao.api.model.ShopCatsListGetResponse;
import com.taobao.api.model.ShopGetRequest;
import com.taobao.api.model.ShopGetResponse;
import com.taobao.api.model.SkuGetRequest;
import com.taobao.api.model.SkuGetResponse;
import com.taobao.api.model.SkusGetRequest;
import com.taobao.api.model.SkusGetResponse;
import com.taobao.api.model.SpuGetRequest;
import com.taobao.api.model.SpuGetResponse;
import com.taobao.api.model.TimegetResponse;
import com.taobao.api.model.TradeGetRequest;
import com.taobao.api.model.TradeGetResponse;
import com.taobao.api.model.TradesGetRequest;
import com.taobao.api.model.TradesGetResponse;
import com.taobao.api.model.UserDetailGetRequest;
import com.taobao.api.model.UserDetailGetResponse;
import com.taobao.api.model.UserGetRequest;
import com.taobao.api.model.UserGetResponse;
import com.taobao.api.model.UsersGetRequest;
import com.taobao.api.model.UsersGetResponse;

/**
 * 公开数据接口
 * 
 * 此接口中包括TopClient中所有的客户端API 每个API被调用后均返回一个Response结果，此结果中包括：错误码 code，错误信息
 * msg，重定向URL redirectUrl， 以及Http请求返回的结果的body部分 ，另包括调用这个API成功时返回的真实结果
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 */
public interface TaobaoPublicApi {

	/**
	 * 此接口方法用以得到单个商品信息的公开信息
	 * 
	 * @param itemGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemGetResponse itemGet(ItemGetRequest itemGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索商品信息.支持分页和排序.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param itemsGetRequest
	 * @return ItemsGetResponse 返回搜索到的商品列表 List<Item> ,
	 *         目前只包含如下字段iid,title,nick,type,cid,pic_path,
	 *         delist_time,price,post_fee
	 * @throws TaobaoApiException
	 */
	public ItemsGetResponse itemsGet(ItemsGetRequest itemsGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索交易公开信息.支持分页.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param tradesGetRequest
	 * @return TradesGetResponse
	 * @throws TaobaoApiException
	 */
	public TradesGetResponse tradesGet(TradesGetRequest tradesGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以搜索商品出价记录.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @author sulinchong.pt 2009-1-13 下午09:16:46
	 * @param ordersGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public OrdersGetResponse ordersGet(OrdersGetRequest ordersGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到单个用户资料的公开信息。
	 * 
	 * @param userGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public UserGetResponse userGet(UserGetRequest userGetRequest)
			throws TaobaoApiException;
	
	public UserDetailGetResponse userDetailGet(UserDetailGetRequest request) throws TaobaoApiException ;

	/**
	 * 此接口方法以实现批量得到用户公开资料.apiType=1，需要签名校验(不需用户绑定).
	 * 
	 * @param usersGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public UsersGetResponse usersGet(UsersGetRequest usersGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到前台展示的供买家浏览的商品类目.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param itemCatsGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemCatsListResponse itemCatsListGet(
			ItemCatsListGetRequest itemCatsGetRequest)
			throws TaobaoApiException;
	
	/**
	 * 类目Featrue属性值查询
	 * 
	 * @param req
	 * @return
	 * @throws TaobaoApiException
	 * @author jeck218@gmail.com 2009-9-5
	 */
	public FeaturesGetResponse itemCatFeaturesGet(FeaturesGetRequest req) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到前台展示的店铺类目.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @return
	 * @throws TaobaoApiException
	 */
	public ShopCatsListGetResponse shopCatsListGet() throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到前台展示的店铺内卖家自定义商品类目.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param sellerCatsListGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public SellerCatsListGetResponse sellerCatsListGet(
			SellerCatsListGetRequest sellerCatsListGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以得到spu(标准属性单元，可以理解为一种产品).apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param spuGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public SpuGetResponse spuGet(SpuGetRequest spuGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到前台展示的供买家浏览的商品属性.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param itemPropListGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropResponse itemPropListGet(
			ItemPropRequest itemPropListGetRequest) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到前台展示的供买家浏览的商品属性.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param itemPropsListGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropsResponse itemPropsListGet(
			ItemPropsRequest itemPropsListGetRequest) throws TaobaoApiException;

	/**
	 * 此接口方法用以查询店铺的公开信息。
	 * 
	 * @param shopGetRequest
	 * @return ShopGetResponse shop店铺对象列表
	 * @throws TaobaoApiException
	 */
	public ShopGetResponse shopGet(ShopGetRequest shopGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用以得到单条交易信息的公开信息.apiType=3，需要签名校验(不需用户绑定)
	 * 
	 * @param tradeGetRequest
	 * @return TradeGetResponse trade交易信息
	 * @throws TaobaoApiException
	 */
	public TradeGetResponse tradeGet(TradeGetRequest tradeGetRequest)
			throws TaobaoApiException;

	/**
	 * 获取后台供卖家发布商品的标准商品类目。
	 */
	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest itemCatsGetRequest) throws TaobaoApiException;

	/**
	 * 获取后台供卖家发布商品用的商品标准属性和属性值列表。
	 */
	public ItemPropsResponse itemPropsGet(ItemPropsRequest itemPropsGetRequest) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品的标准商品类目.apiType=1， 部分服务调用，需要签名校验和用户绑定.
	 * @deprecated 此接口已经不推荐使用，请使用itemCatsGet接口
	 * @param itemCatsGetV2Request
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemCatsResponse itemCatsGetV2(ItemCatsGetV2Request itemCatsGetV2Request) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品用的商品标准属性和属性值列表.apiType=1， 部分服务调用，需要签名校验和用户绑定
	 * @deprecated 此接口已经不推荐使用，请使用itemPropsGet接口
	 * @param itemPropsV2Request
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropsResponse itemPropsGetV2(ItemPropsV2Request itemPropsV2Request) throws TaobaoApiException;

	/**
	 * 此接口方法以实现得到后台供卖家发布商品用的商品标准值列表.apiType=1， 部分服务调用，需要签名校验和用户绑定
	 * 
	 * @param itemPropValuesGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ItemPropValuesResponse itemPropValuesGet(
			ItemPropValuesGetRequest itemPropValuesGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用于得到一个SKU对象 apiType=1
	 * 
	 * @param skuGetRequest
	 * @return Sku object
	 * @throws TaobaoApiException
	 */
	public SkuGetResponse skuGet(SkuGetRequest skuGetRequest)
			throws TaobaoApiException;

	/**
	 * 此接口方法用于批量查询SKU对象 apiType=1
	 * 
	 * @param skusGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public SkusGetResponse skusGet(SkusGetRequest skusGetRequest)
			throws TaobaoApiException;

	/**
	 * 取单个PU apiType=1
	 * 
	 * @author liupo
	 * @param productGetRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductGetResponse productGet(ProductGetRequest productGetRequest)
			throws TaobaoApiException;

	/**
	 * 查询SPU apiType=1
	 * 
	 * @author liupo
	 * @param productSearchRequest
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductSearchResponse productSearch(
			ProductSearchRequest productSearchRequest)
			throws TaobaoApiException;
	
	/**
	 * 查询产品列表
	 * @return productsGetRequest
	 * @throws TaobaoApiException
	 */
	public ProductsGetResponse productsGet(ProductsGetRequest productsGetRequest) throws TaobaoApiException;
	
	/**
	 * 查询地址区域信息  apiTpe=1
	 * @param request
	 * @return AreasGetResponse
	 * @throws TaobaoApiException
	 */
	public AreasGetResponse areasGet(AreasGetRequest request) throws TaobaoApiException;
	
	/**
	 * 查询可以物流公司信息
	 * @deprecated 请使用logisticsCompaniesGet代替
	 * @param request
	 * @return LogisticCompaniesGetResponse
	 * @throws TaobaoApiException
	 */
	public LogisticCompaniesGetResponse logisticCompaniesGet(LogisticCompaniesGetRequest request) throws TaobaoApiException;
	
	/**
	 * 此接口方法用以搜索商品信息.支持分页和排序.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @param itemsGetRequest
	 * @return ItemsGetResponse 返回搜索到的商品列表 List<Item> ,
	 *         目前只包含如下字段iid,title,nick,type,cid,pic_path,
	 *         delist_time,price,post_fee
	 * @throws TaobaoApiException
	 */
	public ItemsSearchResponse itemsSearch(ItemsSearchRequest itemsSearchRequest) throws TaobaoApiException;
			
	/**
	 * 查询单个邮费模板 apiType = 1
	 * @param postageGetRequest
	 * @return PostageGetResponse
	 * @throws TaobaoApiException
	 */
	public PostageGetResponse postageGet(PostageGetRequest postageGetRequest) throws TaobaoApiException;
	
	/**
	 * 得到淘宝系统当前时间.apiType=1，需要签名校验(不需用户绑定)
	 * 
	 * @return
	 * @throws TaobaoApiException
	 * @throws ParseException 
	 */
	public TimegetResponse timeget() throws TaobaoApiException, ParseException;
	
	/**
	 * 搜索产品统计结果，专业等级的开放，不需要绑定用户
	 * @param request
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductStatSearchResponse productStatSearch(ProductStatSearchRequest request) throws TaobaoApiException;
	
	/**
	 * 后去产品统计搜索结果，专业等级的开放，不需要绑定用户
	 * @param request
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductStatGetResponse productStatGet(ProductStatGetRequest request) throws TaobaoApiException;

	/**
	 * 获取产品的价格统计数据，专业等级的开放，不需要绑定用户
	 * @param request
	 * @return
	 * @throws TaobaoApiException
	 */
	public ProductPriceStatGetResponse productPriceStatGet(ProductPriceStatGetRequest request) throws TaobaoApiException;
	
	public LogisticsCompaniesGetResponse logisticsCompaniesGet(LogisticsCompaniesGetRequest request)
			throws TaobaoApiException;
	
	public ItemPropsVerticalGetResponse itemPropsVerticalGet(ItemPropsVerticalGetRequest request) throws TaobaoApiException;
	
	
	public LogisticsPartnersGetResponse logisticsPartnersGet(LogisticsPartnersGetRequest request)throws TaobaoApiException ;


}
