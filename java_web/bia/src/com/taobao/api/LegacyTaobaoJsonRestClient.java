package com.taobao.api;

import static com.taobao.api.ApiConstants.BANNER;
import static com.taobao.api.ApiConstants.CID;
import static com.taobao.api.ApiConstants.FIELDS;
import static com.taobao.api.ApiConstants.HAS_DISCOUNT;
import static com.taobao.api.ApiConstants.HAS_SHOWCASE;
import static com.taobao.api.ApiConstants.ORDER_BY;
import static com.taobao.api.ApiConstants.PAGE_NO;
import static com.taobao.api.ApiConstants.PAGE_SIZE;
import static com.taobao.api.ApiConstants.Q;
import static com.taobao.api.ApiConstants.SELLER_CIDS;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taobao.api.convert.TaobaoBrandJSONConvert;
import com.taobao.api.convert.TaobaoItemJSONConvert;
import com.taobao.api.convert.TaobaoPostageJSONConvert;
import com.taobao.api.convert.TaobaoProductJSONConvert;
import com.taobao.api.convert.TaobaoRefundJSONConvert;
import com.taobao.api.convert.TaobaoRefundMessageJSONConvert;
import com.taobao.api.convert.TaobaoSellerJSONConvert;
import com.taobao.api.convert.TaobaoShippingJSONConvert;
import com.taobao.api.convert.TaobaoShopJSONConvert;
import com.taobao.api.convert.TaobaoSkuJSONConvert;
import com.taobao.api.convert.TaobaoSpuJSONConvert;
import com.taobao.api.convert.TaobaoSuiteJSONConvert;
import com.taobao.api.convert.TaobaoTradeJSONConvert;
import com.taobao.api.convert.TaobaoUserJSONConvert;
import com.taobao.api.convert.TaobaoVasJSONConvert;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.*;
import com.taobao.api.util.DateUtil;

/**
 * 淘宝对外部提供的REST API客户端
 * 
 * 注意，默认是SIP入口。使用淘宝API入口需要设置：
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 */
public class LegacyTaobaoJsonRestClient extends AbstractTaobaoRestClient implements
		TaobaoRestClient {

	public LegacyTaobaoJsonRestClient(String appkey, String secret)
			throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public LegacyTaobaoJsonRestClient(String appkey, String secret, boolean isSandbox)
			throws TaobaoApiException {
		this(isSandbox ? ApiConstants.API_SANDBOX_SERVICE_URL
				: ApiConstants.API_SERVICE_URL, appkey, secret);
	}

	public LegacyTaobaoJsonRestClient(String serviceUrl, String appkey, String secret)
			throws TaobaoApiException {
		this(serviceUrl, "1.0", appkey, secret);
	}

	public LegacyTaobaoJsonRestClient(String serviceUrl, String version,
			String appkey, String secret) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
	}

	/**
	 * @see getAppKeyName
	 */
	@Deprecated
	protected String getApiKeyName() {
		return ApiConstants.APIKEY;
	}
	
	@Override
	protected String getAppKeyName() {
		return ApiConstants.APPKEY;
	}

	protected String getMethodName() {
		return ApiConstants.METHOD;
	}

	protected String getSignName() {
		return ApiConstants.SIGN;
	}

	protected String getSessionIdName() {
		return ApiConstants.SESSION;
	}

	protected String getTimestampName() {
		return ApiConstants.TIMESTAMP;
	}

	/**
	 * 通过系统平台（sip）的检查（签名、绑定用户）后，检测淘宝的接口是否调用正常
	 * 
	 * @param response
	 * @param json
	 * @throws JSONException
	 */
	protected void parseError(TaobaoResponse response, JSONObject json)
			throws JSONException {
		if (json.has(ApiConstants.ERROR_RSP)) {
			JSONObject errorRsp = json.getJSONObject(ApiConstants.ERROR_RSP);
			response.setErrorCode(errorRsp.getString(ApiConstants.ERROR_CODE));
			response.setMsg(errorRsp.getString(ApiConstants.ERROR_MSG));
		}
	}

	/**
	 * 设置返回的response中totalResults(取得到商品、交易等的 总数)对应字段的值
	 * 
	 * @param rsp
	 *            JSONObject
	 * @param listUrlResponse
	 *            返回的response对象
	 * @throws NumberFormatException
	 * @throws JSONException
	 */
	protected void setTotalResults(JSONObject rsp,
			TaobaoListResponse listUrlResponse) throws NumberFormatException,
			TaobaoApiException {
		if (rsp.has(ApiConstants.TOTALRESULTS)) {
			try {
				listUrlResponse.setTotalResults(rsp.getInt(ApiConstants.TOTALRESULTS));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
	}

	protected JSONObject getRsp(JSONObject json) throws JSONException {
		return json.getJSONObject(ApiConstants.RSP);
	}

	// 商品部分
	public ItemAddResponse itemAdd(ItemAddRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemAdd(req);
		return sendItemAdd(params, req, sessionId);
	}
	
	public ItemAddResponse itemGameAdd(ItemGameAddRequest req, String sessionId) throws TaobaoApiException {
		return null;
	}
	
	public Map<String, Object> prepareItemGameAdd(ItemGameAddRequest req) {
		Map<String, Object> map = prepareItemAdd(req);
		map.put(ApiConstants.GAME_TYPE, req.getGameType());
		map.put(ApiConstants.VERTICAL_IDS, req.getVerticalIds());
		map.put(ApiConstants.VERTICAL_VALUES, req.getVerticalValues());
		map.put(ApiConstants.VERTICAL_IMAGE_ID, req.getVerticalImageId());
		return map;
	}

	public Map<String, Object> prepareItemAdd(ItemAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.VALID_THRU, req.getValidThru());
		params.put(ApiConstants.HAS_INVOICE, req.getHasInvoice());
		params.put(ApiConstants.HAS_WARRANTY, req.getHasWarranty());
		params.put(ApiConstants.SELLER_CIDS, req.getSellerCids());
		params.put(ApiConstants.HAS_DISCOUNT, req.getHasDiscount());
		params.put(ApiConstants.POST_FEE, req.getPostFee());
		params.put(ApiConstants.EXPRESS_FEE, req.getExpressFee());
		params.put(ApiConstants.LIST_TIME, req.getListTime());
		params.put(ApiConstants.INCREMENT, req.getIncrement());
		params.put(ApiConstants.AUTO_REPOST, req.getAutoRepost());
		params.put(ApiConstants.HAS_SHOWCASE, req.getHasShowcase());
		params.put(ApiConstants.EMS_FEE, req.getEmsFee());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PROPS, req.getProps());
		params.put(ApiConstants.NUM, req.getNum());
		params.put(ApiConstants.PRICE, req.getPrice());
		params.put(ApiConstants.TITLE, req.getTitle());
		params.put(ApiConstants.DESC, req.getDesc());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.STUFF_STATUS, req.getStuffStatus());
		params.put(ApiConstants.APPROVE_STATUS, req.getApproveStatus());
		params.put(ApiConstants.FREIGHT_PAYER, req.getFreightPayer());
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		if (req.getLocation() != null) {
			params.put(ApiConstants.LOCATION_STATE, (req.getLocation().getState()));
			params.put(ApiConstants.LOCATION_CITY, (req.getLocation().getCity()));
		}
		params.put(ApiConstants.AUCTION_POINT, req.getAuctionPoint());
		params.put(ApiConstants.PROPERTY_ALIAS, req.getPropertyAlias());
		params.put(ApiConstants.SKU_PROPERTIES, req.getSkuProperties());
		params.put(ApiConstants.SKU_QUANTITIES, req.getSkuQuantities());
		params.put(ApiConstants.SKU_PRICES, req.getSkuPrices());
		params.put(ApiConstants.SKU_OUTER_IDS, req.getSkuOuterIds());
		params.put(ApiConstants.POSTAGE_ID, req.getPostageId());
		params.put(ApiConstants.INPUT_PIDS, req.getInputPids());
		params.put(ApiConstants.INPUT_STR, req.getInputStr());
		params.put(ApiConstants.LANG, req.getLang());
		params.put(ApiConstants.IS_TAOBAO, req.getIsTaobao());
		params.put(ApiConstants.IS_EX, req.getIsEx());
		params.put(ApiConstants.PRODUCT_ID, req.getProductId());
		params.put(ApiConstants.PIC_PATH, req.getPicPath());
		params.put(ApiConstants.IS_3D, req.getIs3D());
		return params;
	}

	public ItemAddResponse sendItemAdd(Map<String, Object> params, ItemAddRequest erq,
			String sessionId) throws TaobaoApiException {
		ItemAddResponse itemAddResponse = getItemAddResponse(erq, sessionId, params);
		// 平台成功访问
		if (itemAddResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemAddResponse.getBody());
				parseError(itemAddResponse, json);
				// 访问淘宝成功
				if (itemAddResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						Item addedItem = new Item();
						try {
							addedItem.setCreated(DateUtil.strToDate(item
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						addedItem.setIid(item.getString(ApiConstants.IID));
						itemAddResponse.setItem(addedItem);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemAddResponse;
	}

	private ItemAddResponse getItemAddResponse(ItemAddRequest req, String sessionId,
			Map<String, Object> paramsMap) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加商品是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_ADD.getMethod(), sessionId, paramsMap),
					req.getImage());
		} else {
			rsp = this.getFetch().fetch(this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_ADD.getMethod(), sessionId, paramsMap));
		}
		return new ItemAddResponse(rsp);
	}

	public ItemGetResponse itemGet(ItemGetRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemGet(req);
		return sendItemGet(params, sessionId);
	}

	public Map<String, Object> prepareItemGet(ItemGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.NICK, req.getNick());
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.NUM_IID, req.getNumIid());
		return params;
	}

	public ItemGetResponse sendItemGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_GET.getMethod(), sessionId, params));
		ItemGetResponse itemGetResponse = new ItemGetResponse(response);
		// 平台成功访问
		if (itemGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemGetResponse.getBody());
				parseError(itemGetResponse, json);
				// 访问淘宝成功
				if (itemGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						itemGetResponse.setItem(TaobaoItemJSONConvert.convertJsonToItem(item));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemGetResponse;
	}

	public ItemGetResponse itemGet(ItemGetRequest req) throws TaobaoApiException {
		return this.itemGet(req, null);
	}

	public ItemUpdateResponse itemUpdate(ItemUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemUpdate(req);
		return sendItemUpdate(req, params, sessionId);
	}

	public Map<String, Object> prepareItemUpdate(ItemUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PROPS, req.getProps());
		params.put(ApiConstants.NUM, req.getNum());
		params.put(ApiConstants.PRICE, req.getPrice());
		params.put(ApiConstants.STUFF_STATUS, req.getStuffStatus());
		params.put(ApiConstants.TITLE, req.getTitle());
		params.put(ApiConstants.DESC, req.getDesc());
		params.put(ApiConstants.VALID_THRU, req.getValidThru());
		params.put(ApiConstants.HAS_INVOICE, req.getHasInvoice());
		params.put(ApiConstants.HAS_WARRANTY, req.getHasWarranty());
		params.put(ApiConstants.SELLER_CIDS, req.getSellerCids());
		params.put(ApiConstants.HAS_DISCOUNT, req.getHasDiscount());
		params.put(ApiConstants.POST_FEE, req.getPostFee());
		params.put(ApiConstants.EXPRESS_FEE, req.getExpressFee());
		params.put(ApiConstants.LIST_TIME, req.getListTime());
		params.put(ApiConstants.INCREMENT, req.getIncrement());
		params.put(ApiConstants.AUTO_REPOST, req.getAutoRepost());
		params.put(ApiConstants.HAS_SHOWCASE, req.getHasShowcase());
		params.put(ApiConstants.EMS_FEE, req.getEmsFee());
		params.put(ApiConstants.APPROVE_STATUS, req.getApproveStatus());
		params.put(ApiConstants.FREIGHT_PAYER, req.getFreightPayer());
		if (req.getLocation() != null) {
			params.put(ApiConstants.LOCATION_STATE, (req.getLocation().getState()));
			params.put(ApiConstants.LOCATION_CITY, (req.getLocation().getCity()));
		}
		params.put(ApiConstants.AUCTION_POINT, req.getAuctionPoint());
		params.put(ApiConstants.PROPERTY_ALIAS, req.getPropertyAlias());
		params.put(ApiConstants.SKU_IDS, req.getSkuIds());
		params.put(ApiConstants.SKU_PROPERTIES, req.getSkuProperties());
		params.put(ApiConstants.SKU_QUANTITIES, req.getSkuQuantities());
		params.put(ApiConstants.SKU_PRICES, req.getSkuPrices());
		params.put(ApiConstants.SKU_OUTER_IDS, req.getSkuOuterIds());
		params.put(ApiConstants.POSTAGE_ID, req.getPostageId());
		params.put(ApiConstants.INPUT_PIDS, req.getInputPids());
		params.put(ApiConstants.INPUT_STR, req.getInputStr());
		params.put(ApiConstants.LANG, req.getLang());
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		params.put(ApiConstants.IS_TAOBAO, req.getIsTaobao());
		params.put(ApiConstants.IS_EX, req.getIsEx());
		params.put(ApiConstants.PRODUCT_ID, req.getProductId());
		params.put(ApiConstants.PIC_PATH, req.getPicPath());
		params.put(ApiConstants.IS_3D, req.getIs3D());
		return params;
	}

	public ItemUpdateResponse sendItemUpdate(ItemUpdateRequest req, Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		// 请求调用更新商品API，返回 响应的response
		ItemUpdateResponse itemUpdateResponse = getUpdateResponse(req, sessionId, params);
		// 平台成功访问
		if (itemUpdateResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemUpdateResponse.getBody());
				parseError(itemUpdateResponse, json);
				// 访问淘宝成功
				if (itemUpdateResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						Item updatedItem = new Item();
						try {
							updatedItem.setModified(DateUtil.strToDate(item
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						updatedItem.setIid(item.getString(ApiConstants.IID));
						itemUpdateResponse.setItem(updatedItem);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemUpdateResponse;
	}

	private ItemUpdateResponse getUpdateResponse(ItemUpdateRequest req, String sessionId,
			Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加商品是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_UPDATE.getMethod(), sessionId, params),
					req.getImage());
		} else {
			rsp = this.getFetch().fetch(this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_UPDATE.getMethod(), sessionId, params));
		}
		return new ItemUpdateResponse(rsp);
	}

	public ItemsGetResponse itemsGet(ItemsGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemsGet(req);
		return sendItemsGet(params);
	}

	public Map<String, Object> prepareItemsGet(ItemsGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.Q, req.getQ());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.START_PRICE, req.getStartPrice());
		params.put(ApiConstants.END_PRICE, req.getEndPrice());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.ORDER_BY, req.getOrderBy());
		params.put(ApiConstants.NICKS, req.getNicks());
		params.put(ApiConstants.PROPS, req.getProps());
		params.put(ApiConstants.PRODUCT_ID, req.getProductId());
		params.put(ApiConstants.WW_STATUS, req.getWwStatus());
		params.put(ApiConstants.POST_FREE, req.getPostFree());
		params.put(ApiConstants.LOCATION_CITY, req.getCity());
		params.put(ApiConstants.LOCATION_STATE, req.getState());
		params.put(ApiConstants.ITEM_TYPE, req.getItemType());
		params.put(ApiConstants.IS_3D, req.getIs3D());
		params.put(ApiConstants.START_SCORE, req.getStartScore());
		params.put(ApiConstants.END_SCORE, req.getEndScore());
		params.put(ApiConstants.START_VOLUME, req.getStartVolume());
		params.put(ApiConstants.END_VOLUME, req.getEndVolume());
		params.put(ApiConstants.ONE_STATION, req.getOneStation());
		params.put(ApiConstants.IS_COD, req.getIsCod());
		params.put(ApiConstants.IS_MALL, req.getIsMall());
		params.put(ApiConstants.IS_PREPAY, req.getIsPrepay());
		params.put(ApiConstants.GENUINE_SECURITY, req.getGenuineSecurity());
		params.put(ApiConstants.PROMOTED_SERVICE, req.getPromotedService());
		params.put(ApiConstants.STUFF_STATUS, req.getStuffStatus());
		return params;
	}

	public ItemsGetResponse sendItemsGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMS_GET.getMethod(), params));
		ItemsGetResponse itemsGetResponse = new ItemsGetResponse(response);
		// 平台访问成功
		if (itemsGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsGetResponse.getBody());
				parseError(itemsGetResponse, json);
				// 访问淘宝成功
				if (itemsGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						itemsGetResponse.setItems(TaobaoItemJSONConvert
								.convertJsonArrayToItemList(items));
					}
					setTotalResults(rsp, itemsGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsGetResponse;
	}

	public ItemsSearchResponse itemsSearch(ItemsSearchRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemsSearch(req);
		return sendItemsSearch(params);
	}

	public Map<String, Object> prepareItemsSearch(ItemsSearchRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.Q, req.getQ());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.START_PRICE, req.getStartPrice());
		params.put(ApiConstants.END_PRICE, req.getEndPrice());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.ORDER_BY, req.getOrderBy());
		params.put(ApiConstants.NICKS, req.getNicks());
		params.put(ApiConstants.PROPS, req.getProps());
		params.put(ApiConstants.PRODUCT_ID, req.getProductId());
		params.put(ApiConstants.WW_STATUS, req.getWwStatus());
		params.put(ApiConstants.POST_FREE, req.getPostFree());
		params.put(ApiConstants.LOCATION_CITY, req.getCity());
		params.put(ApiConstants.LOCATION_STATE, req.getState());
		params.put(ApiConstants.ITEM_TYPE, req.getItemType());
		params.put(ApiConstants.IS_3D, req.getIs3D());
		params.put(ApiConstants.START_SCORE, req.getStartScore());
		params.put(ApiConstants.END_SCORE, req.getEndScore());
		params.put(ApiConstants.START_VOLUME, req.getStartVolume());
		params.put(ApiConstants.END_VOLUME, req.getEndVolume());
		params.put(ApiConstants.ONE_STATION, req.getOneStation());
		params.put(ApiConstants.IS_COD, req.getIsCod());
		params.put(ApiConstants.IS_MALL, req.getIsMall());
		params.put(ApiConstants.IS_PREPAY, req.getIsPrepay());
		params.put(ApiConstants.GENUINE_SECURITY, req.getGenuineSecurity());
		params.put(ApiConstants.PROMOTED_SERVICE, req.getPromotedService());
		params.put(ApiConstants.STUFF_STATUS, req.getStuffStatus());
		return params;
	}

	public ItemsSearchResponse sendItemsSearch(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMS_SEARCH.getMethod(), params));
		ItemsSearchResponse itemsSearchResponse = new ItemsSearchResponse(response);
		// 平台访问成功
		if (itemsSearchResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsSearchResponse.getBody());
				parseError(itemsSearchResponse, json);
				// 访问淘宝成功
				if (itemsSearchResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_SEARCHS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEM_SEARCHS);
						JSONObject item = items.getJSONObject(0);
						itemsSearchResponse.setSearch(TaobaoItemJSONConvert
								.convertJsonToItemSearch(item));
					}
					setTotalResults(rsp, itemsSearchResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsSearchResponse;
	}
	
	public ItemsGetResponse itemsOnsaleGet(ItemsOnSaleGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemsOnsaleGet(req);
		return sendItemsOnsaleGet(params, sessionId);
	}
	
	//为了自动化映射，改一下名称的大小写
	public ItemsGetResponse itemsOnSaleGet(ItemsOnSaleGetRequest req, String sessionId)
			throws TaobaoApiException {
		return itemsOnsaleGet(req, sessionId);
	}

	public ItemsGetResponse itemsAllGet(ItemsAllGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemsAllGet(req);
		return sendItemsAllGet(params, sessionId);
	}

	public Map<String, Object> prepareItemsOnsaleGet(ItemsOnSaleGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FIELDS, req.getFields());
		params.put(HAS_DISCOUNT, req.getHasDiscount());
		params.put(HAS_SHOWCASE, req.getHasShowcase());
		params.put(Q, req.getQ());
		params.put(CID, req.getCid());
		params.put(SELLER_CIDS, req.getSellerCids());
		params.put(PAGE_NO, req.getPageNo());
		params.put(PAGE_SIZE, req.getPageSize());
		params.put(ORDER_BY, req.getOrderBy());
		params.put(ApiConstants.IS_TAOBAO, req.getIsTaobao());
		params.put(ApiConstants.IS_EX, req.getIsEx());
		return params;
	}

	public Map<String, Object> prepareItemsAllGet(ItemsAllGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FIELDS, req.getFields());
		params.put(Q, req.getQ());
		params.put(CID, req.getCid());
		params.put(SELLER_CIDS, req.getSellerCids());
		params.put(PAGE_NO, req.getPageNo());
		params.put(PAGE_SIZE, req.getPageSize());
		params.put(ORDER_BY, req.getOrderBy());
		return params;
	}

	public ItemsGetResponse sendItemsOnsaleGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch()
				.fetch(
						this.getServiceUrl(),
						getTemplateRequest(TaobaoApiMethod.ITEMS_ONSALE_GET.getMethod(), sessionId,
								params));
		ItemsGetResponse itemsOnSaleGetResponse = new ItemsGetResponse(response);
		// 平台访问成功
		if (itemsOnSaleGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsOnSaleGetResponse.getBody());
				parseError(itemsOnSaleGetResponse, json);
				// 访问淘宝成功
				if (itemsOnSaleGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						itemsOnSaleGetResponse.setItems(TaobaoItemJSONConvert
								.convertJsonArrayToItemList(items));
					}
					setTotalResults(rsp, itemsOnSaleGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsOnSaleGetResponse;
	}

	public ItemsGetResponse sendItemsAllGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMS_ALL_GET.getMethod(), sessionId, params));
		ItemsGetResponse itemsAllGetResponse = new ItemsGetResponse(response);
		// 平台访问成功
		if (itemsAllGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsAllGetResponse.getBody());
				parseError(itemsAllGetResponse, json);
				// 访问淘宝成功
				if (itemsAllGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						itemsAllGetResponse.setItems(TaobaoItemJSONConvert
								.convertJsonArrayToItemList(items));
					}
					setTotalResults(rsp, itemsAllGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsAllGetResponse;
	}

	public Map<String, Object> prepareItemsInventoryGet(ItemsInventoryGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FIELDS, req.getFields());
		params.put(BANNER, req.getBanner());
		params.put(Q, req.getQ());
		params.put(PAGE_NO, req.getPageNo());
		params.put(PAGE_SIZE, req.getPageSize());
		params.put(ORDER_BY, req.getOrderBy());
		params.put(ApiConstants.IS_TAOBAO, req.getIsTaobao());
		params.put(ApiConstants.IS_EX, req.getIsEx());
		return params;
	}

	public ItemsGetResponse sendItemsInventoryGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMS_INVENTORY_GET.getMethod(), sessionId,
						params));
		ItemsGetResponse itemsInStockGetResponse = new ItemsGetResponse(response);
		// 平台访问成功
		if (itemsInStockGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsInStockGetResponse.getBody());
				parseError(itemsInStockGetResponse, json);
				// 淘宝访问成功
				if (itemsInStockGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						itemsInStockGetResponse.setItems(TaobaoItemJSONConvert
								.convertJsonArrayToItemList(items));
					}
					setTotalResults(rsp, itemsInStockGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsInStockGetResponse;
	}

	public ItemsGetResponse itemsInStockGet(ItemsInStockGetRequest req, String sessionId)
			throws TaobaoApiException {
		return this.itemsInventoryGet(req, sessionId);
	}

	public ItemsGetResponse itemsInventoryGet(ItemsInventoryGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemsInventoryGet(req);
		return sendItemsInventoryGet(params, sessionId);
	}

	public ItemUpdateDelistingResponse itemUpdateDelisting(ItemUpdateDelistingRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemUpdateDelisting(req);
		return sendItemUpdateDelisting(params, sessionId);
	}

	public Map<String, Object> prepareItemUpdateDelisting(ItemUpdateDelistingRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		return params;
	}

	public ItemUpdateDelistingResponse sendItemUpdateDelisting(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_UPDATE_DELISTING.getMethod(), sessionId,
						params));
		ItemUpdateDelistingResponse itemUpdateDelistingResponse = new ItemUpdateDelistingResponse(
				response);
		// 平台成功访问
		if (itemUpdateDelistingResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemUpdateDelistingResponse.getBody());
				parseError(itemUpdateDelistingResponse, json);
				// 访问淘宝成功
				if (itemUpdateDelistingResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						itemUpdateDelistingResponse.setIid(item.getString(ApiConstants.IID));
						try {
							itemUpdateDelistingResponse.setModified(DateUtil.strToDate(item
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemUpdateDelistingResponse;
	}

	public ItemUpdateListingResponse itemUpdateListing(ItemUpdateListingRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemUpdateListing(req);
		return sendItemUpdateListing(params, sessionId);
	}

	public Map<String, Object> prepareItemUpdateListing(ItemUpdateListingRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.NUM, req.getNum());
		return params;
	}

	public ItemUpdateListingResponse sendItemUpdateListing(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_UPDATE_LISTING.getMethod(), sessionId,
						params));
		ItemUpdateListingResponse itemUpdateListingResponse = new ItemUpdateListingResponse(
				response);
		// 平台成功访问
		if (itemUpdateListingResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemUpdateListingResponse.getBody());
				parseError(itemUpdateListingResponse, json);
				// 访问淘宝成功
				if (itemUpdateListingResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						itemUpdateListingResponse.setIid(item.getString(ApiConstants.IID));
						try {
							itemUpdateListingResponse.setModified(DateUtil.strToDate(item
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemUpdateListingResponse;
	}

	@Deprecated
	public ItemUpdateRevokeShowcaseResponse itemUpdateRevokeShowcase(
			ItemUpdateRevokeShowcaseRequest req, String sessionId) throws TaobaoApiException {
		return new ItemUpdateRevokeShowcaseResponse(this.itemRecommendDelete(req, sessionId));
	}

	@Deprecated
	public ItemUpdateShowcaseResponse itemUpdateShowcase(ItemUpdateShowcaseRequest req,
			String sessionId) throws TaobaoApiException {
		return new ItemUpdateShowcaseResponse(this.itemRecommendAdd(req, sessionId));
	}

	@Deprecated
	public ItemCatsResponse itemCatsGetV2(ItemCatsGetV2Request itemCatsGetV2Request)
			throws TaobaoApiException {
		return itemCatsGet(itemCatsGetV2Request);
	}

	@Deprecated
	public ItemPropsResponse itemPropsGetV2(ItemPropsV2Request itemPropsV2Request)
			throws TaobaoApiException {
		return itemPropsGet(itemPropsV2Request);
	}

	public ItemPropValuesResponse itemPropValuesGet(ItemPropValuesGetRequest req)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItempropvaluesGet(req);
		return sendItemPropValuesGet(params);
	}

	public ItemPropValuesResponse itempropvaluesGet(ItemPropValuesGetRequest req)
			throws TaobaoApiException {
		return itemPropValuesGet(req);
	}

	public Map<String, Object> prepareItempropvaluesGet(ItemPropValuesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PVS, req.getPvs());
		params.put(ApiConstants.DATETIME, req.getDatetime());
		return params;
	}

	public ItemPropValuesResponse sendItemPropValuesGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMPROPVALUES_GET.getMethod(), params));
		ItemPropValuesResponse itemPropValuesResponse = new ItemPropValuesResponse(response);
		// 平台访问成功
		if (itemPropValuesResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(itemPropValuesResponse.getBody());
				parseError(itemPropValuesResponse, json);
				// 淘宝访问成功
				if (itemPropValuesResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.PROP_VALUES)) {
						JSONArray prop_values = rsp.getJSONArray(ApiConstants.PROP_VALUES);
						itemPropValuesResponse.setPropValues(TaobaoProductJSONConvert
								.convertJsonArrayToPropValueList(prop_values));
					}
					setTotalResults(rsp, itemPropValuesResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemPropValuesResponse;
	}

	// *********************类目属性部分************************

	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemcatsGet(req);
		return sendItemCatsGet(params, null);
	}

	public ItemCatsResponse itemcatsGet(ItemCatsGetRequest req) throws TaobaoApiException {
		return itemCatsGet(req);
	}

	@Deprecated
	public ItemCatsResponse itemCatsGet(ItemCatsGetRequest req, String sessionId)
			throws TaobaoApiException {
		return itemCatsGet(req);
	}

	public Map<String, Object> prepareItemcatsGet(ItemCatsGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PARENT_CID, req.getParentCid());
		params.put(ApiConstants.CIDS, req.getCids());
		params.put(ApiConstants.DATETIME, req.getDatetime());
		return params;
	}

	protected ItemCatsResponse sendItemCatsGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMCATS_GET.getMethod(), sessionId, params));
		ItemCatsResponse itemCatsGetResponse = new ItemCatsResponse(response);
		// 平台访问成功
		if (itemCatsGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemCatsGetResponse.getBody());
				parseError(itemCatsGetResponse, json);
				// 淘宝访问成功
				if (itemCatsGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_CATS)) {
						JSONArray itemcats = rsp.getJSONArray(ApiConstants.ITEM_CATS);
						itemCatsGetResponse.setItemCats(TaobaoItemJSONConvert
								.convertJsonArrayToItemCatList(itemcats));
					}
					setTotalResults(rsp, itemCatsGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemCatsGetResponse;
	}

	public ItemCatsListResponse itemCatsListGet(ItemCatsListGetRequest req)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemcatsListGet(req);
		return sendItemCatsListGet(params);
	}

	public ItemCatsListResponse itemcatsListGet(ItemCatsListGetRequest req)
			throws TaobaoApiException {
		return itemCatsListGet(req);
	}

	public Map<String, Object> prepareItemcatsListGet(ItemCatsListGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.PARENT_CID, req.getParentCid());
		params.put(ApiConstants.CIDS, req.getCids());
		return params;
	}

	public ItemCatsListResponse sendItemCatsListGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMCATS_LIST_GET.getMethod(), params));
		ItemCatsListResponse itemCatsListGetResponse = new ItemCatsListResponse(response);
		// 平台访问成功
		if (itemCatsListGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemCatsListGetResponse.getBody());
				parseError(itemCatsListGetResponse, json);
				// 淘宝访问成功
				if (itemCatsListGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_CATS)) {
						JSONArray itemcats = rsp.getJSONArray(ApiConstants.ITEM_CATS);
						itemCatsListGetResponse.setItemCats(TaobaoItemJSONConvert
								.convertJsonArrayToItemCatList(itemcats));
					}
					setTotalResults(rsp, itemCatsListGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemCatsListGetResponse;
	}
	
	public FeaturesGetResponse itemcatFeaturesGet(FeaturesGetRequest req) throws TaobaoApiException {
		return itemCatFeaturesGet(req);
	}

	public FeaturesGetResponse itemCatFeaturesGet(FeaturesGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemCatFeaturesGet(req);
		return sendItemCatFeaturesGet(params);
	}
	
	public Map<String, Object> prepareItemcatFeaturesGet(FeaturesGetRequest req) {
		return prepareItemCatFeaturesGet(req);
	}

	public Map<String, Object> prepareItemCatFeaturesGet(FeaturesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.ATTR_KEYS, req.getAttrKeys());
		return params;
	}

	public FeaturesGetResponse sendItemCatFeaturesGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITECAT_FEATURES_GET.getMethod(), params));
		FeaturesGetResponse featuresGetResponse = new FeaturesGetResponse(response);

		// 平台访问成功
		if (featuresGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(featuresGetResponse.getBody());
				parseError(featuresGetResponse, json);
				// 淘宝访问成功
				if (featuresGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.Features)) {
						JSONArray features = rsp.getJSONArray(ApiConstants.Features);
						featuresGetResponse.setFeatures(TaobaoItemJSONConvert
								.convertJsonArrayToFeatureList(features));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return featuresGetResponse;
	}

	public ItemPropsResponse itemPropsGet(ItemPropsRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItempropsGet(req);
		return sendItemPropsGet(params);
	}

	public ItemPropsResponse itempropsGet(ItemPropsRequest req) throws TaobaoApiException {
		return itemPropsGet(req);
	}

	public ItemPropsResponse sendItemPropsGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMPROPS_GET.getMethod(), params));
		ItemPropsResponse itemPropsGetResponse = new ItemPropsResponse(response);
		// 平台访问成功
		if (itemPropsGetResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(itemPropsGetResponse.getBody());
				parseError(itemPropsGetResponse, json);
				// 淘宝访问成功
				if (itemPropsGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_PROPS)) {
						JSONArray item_props = rsp.getJSONArray(ApiConstants.ITEM_PROPS);
						itemPropsGetResponse.setItemProps(TaobaoItemJSONConvert
								.convertJSONArrayToItemPropList(item_props));
						if (rsp.has(ApiConstants.LAST_MODIFIELD)) {
							String lastModifled = rsp.getString(ApiConstants.LAST_MODIFIELD);
							if (null != lastModifled) {
								try {
									itemPropsGetResponse.setLastModifled(DateUtil
											.strToDate(lastModifled));
								} catch (ParseException e) {
									throw new TaobaoApiException(e);
								}
							}
						}
					}
					setTotalResults(rsp, itemPropsGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemPropsGetResponse;
	}

	public Map<String, Object> prepareItempropsGet(ItemPropsRequest req) {
		Map<String, Object> params = prepareItempropsListGet(req);
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PID, req.getPid());
		params.put(ApiConstants.PARENT_PID, req.getParentPid());
		params.put(ApiConstants.IS_KEY_PROP, req.getIsKeyProp());
		params.put(ApiConstants.IS_SALE_PROP, req.getIsSaleProp());
		params.put(ApiConstants.IS_COLOR_PROP, req.getIsColorProp());
		params.put(ApiConstants.IS_ENUM_PROP, req.getIsEnumProp());
		params.put(ApiConstants.IS_INPUT_PROP, req.getIsInputProp());
		params.put(ApiConstants.IS_ITEM_PROP, req.getIsItemProp());
		params.put(ApiConstants.DATETIME, req.getDatetime());
		return params;
	}

	@Deprecated
	public ItemPropsResponse itemPropsGet(ItemPropsRequest req, String sessionId)
			throws TaobaoApiException {
		return itemPropsGet(req);
	}

	public ItemPropsResponse itemPropsListGet(ItemPropsRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItempropsListGet(req);
		return sendItemPropsListGet(params);
	}

	public ItemPropsResponse itempropsListGet(ItemPropsRequest req) throws TaobaoApiException {
		return itemPropsListGet(req);
	}

	public ItemPropsResponse sendItemPropsListGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMPROPS_LIST_GET.getMethod(), params));
		ItemPropsResponse itemPropsListGetResponse = new ItemPropsResponse(response);
		// 平台访问成功
		if (itemPropsListGetResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(itemPropsListGetResponse.getBody());
				parseError(itemPropsListGetResponse, json);
				// 淘宝访问成功
				if (itemPropsListGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_PROPS)) {
						JSONArray item_props = rsp.getJSONArray(ApiConstants.ITEM_PROPS);
						itemPropsListGetResponse.setItemProps(TaobaoItemJSONConvert
								.convertJSONArrayToItemPropList(item_props));
					}
					setTotalResults(rsp, itemPropsListGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemPropsListGetResponse;
	}

	public Map<String, Object> prepareItempropsListGet(ItemPropsRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CID, req.getCid());
		return params;
	}

	public SpuGetResponse spuGet(SpuGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareSpuGet(req);
		return sendSpuGet(params);
	}

	protected SpuGetResponse sendSpuGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SPU_GET.getMethod(), params));
		SpuGetResponse spuGetResponse = new SpuGetResponse(response);
		// 平台访问成功
		if (spuGetResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(spuGetResponse.getBody());
				parseError(spuGetResponse, json);
				// 淘宝访问成功
				if (spuGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SPUS)) {
						JSONArray spus = rsp.getJSONArray(ApiConstants.SPUS);
						spuGetResponse
								.setSpus(TaobaoSpuJSONConvert.convertJsonArrayToSpuList(spus));
					}
					setTotalResults(rsp, spuGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return spuGetResponse;
	}

	public Map<String, Object> prepareSpuGet(SpuGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PROPS, req.getProps());
		return params;
	}

	public ItemPropResponse itemPropGet(ItemPropRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItempropListGet(req);
		return sendItemPropListGet(sessionId, params);
	}

	public ItemPropResponse sendItemPropListGet(String sessionId, Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMPROP_GET.getMethod(), sessionId, params));
		ItemPropResponse itemPropGetResponse = new ItemPropResponse(response);
		// 平台访问成功
		if (itemPropGetResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(itemPropGetResponse.getBody());
				parseError(itemPropGetResponse, json);
				// 淘宝访问成功
				if (itemPropGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_PROPS)) {
						JSONObject item_prop = rsp.getJSONArray(ApiConstants.ITEM_PROPS)
								.getJSONObject(0);
						itemPropGetResponse.setItemProp(TaobaoItemJSONConvert
								.convertJsonObjectToItemProp(item_prop));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemPropGetResponse;
	}

	public ItemPropResponse itemPropListGet(ItemPropRequest req) throws TaobaoApiException {
		return sendItemPropListGet(prepareItempropListGet(req));
	}

	public ItemPropResponse itempropListGet(ItemPropRequest req) throws TaobaoApiException {
		return itemPropListGet(req);
	}

	public ItemPropResponse sendItemPropListGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEMPROP_LIST_GET.getMethod(), params));
		ItemPropResponse itemPropGetResponse = new ItemPropResponse(response);
		// 平台访问成功
		if (itemPropGetResponse.isSuccess()) {
			JSONObject json = null;
			try {
				json = new JSONObject(itemPropGetResponse.getBody());
				parseError(itemPropGetResponse, json);
				// 淘宝访问成功
				if (itemPropGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEM_PROPS)) {
						JSONObject item_prop = rsp.getJSONArray(ApiConstants.ITEM_PROPS)
								.getJSONObject(0);
						itemPropGetResponse.setItemProp(TaobaoItemJSONConvert
								.convertJsonObjectToItemProp(item_prop));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemPropGetResponse;
	}

	public Map<String, Object> prepareItempropListGet(ItemPropRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PID, req.getPid());
		params.put(ApiConstants.CHILD_PATH, req.getChildPath());
		return params;
	}

	public ShopCatsListGetResponse shopCatsListGet() throws TaobaoApiException {
		Map<String, Object> params = new HashMap<String, Object>();
		return sendShopcatsListGet(params);
	}

	public ShopCatsListGetResponse shopcatsListGet() throws TaobaoApiException {
		return shopCatsListGet();
	}

	public ShopCatsListGetResponse sendShopcatsListGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOPCATS_LIST_GET.getMethod(), params));
		ShopCatsListGetResponse shopCatsListGetResponse = new ShopCatsListGetResponse(response);
		// 平台访问成功
		if (shopCatsListGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(shopCatsListGetResponse.getBody());
				parseError(shopCatsListGetResponse, json);
				// 淘宝访问成功
				if (shopCatsListGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHOP_CATS)) {
						JSONArray shopcats = rsp.getJSONArray(ApiConstants.SHOP_CATS);
						shopCatsListGetResponse.setShopCats(TaobaoShopJSONConvert
								.convertJsonArrayToShopCatList(shopcats));
					}
					setTotalResults(rsp, shopCatsListGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return shopCatsListGetResponse;
	}

	public Map<String, Object> prepareSellercatsListGet(
			SellerCatsListGetRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.NICK, request.getNick());
		return params;
	}

	public SellerCatsListGetResponse sellerCatsListGet(SellerCatsListGetRequest req)
			throws TaobaoApiException {
		Map<String, Object> params = prepareSellercatsListGet(req);
		return sendSellerCatsListGet(params);
	}

	public SellerCatsListGetResponse sellercatsListGet(SellerCatsListGetRequest req)
			throws TaobaoApiException {
		return sellerCatsListGet(req);
	}

	public SellerCatsListGetResponse sendSellerCatsListGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SELLERCATS_LIST_GET.getMethod(), params));
		SellerCatsListGetResponse sellerCatsListGetResponse = new SellerCatsListGetResponse(
				response);
		// 平台访问成功
		if (sellerCatsListGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(sellerCatsListGetResponse.getBody());
				parseError(sellerCatsListGetResponse, json);
				// 淘宝访问成功
				if (sellerCatsListGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SELLER_CATS)) {
						JSONArray sellercats = rsp.getJSONArray(ApiConstants.SELLER_CATS);
						sellerCatsListGetResponse.setSellerCats(TaobaoSellerJSONConvert
								.convertJsonArrayToSellerCatList(sellercats));
					}
					setTotalResults(rsp, sellerCatsListGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return sellerCatsListGetResponse;
	}

	// 产品部分

	public ProductGetResponse productGet(ProductGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareProductGet(req);
		return sendProductGet(params);
	}

	public Map<String, Object> prepareProductGet(ProductGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductGetRequest.FIELDS, req.getFields());
		params.put(ProductGetRequest.PRODUCTID, req.getProductId());
		params.put(ProductGetRequest.CID, req.getCid());
		params.put(ProductGetRequest.PROPS, req.getProps());
		return params;
	}

	public ProductGetResponse sendProductGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCT_GET.getMethod(), params));
		ProductGetResponse productGetResponse = new ProductGetResponse(response);
		// 平台访问成功
		if (productGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(productGetResponse.getBody());
				parseError(productGetResponse, json);
				// 访问淘宝成功
				if (productGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductGetRequest.PRODUCT)) {
						JSONArray products = rsp.getJSONArray(ProductGetRequest.PRODUCT);
						JSONObject product = products.getJSONObject(0);
						productGetResponse.setProduct(TaobaoProductJSONConvert
								.convertJsonToProduct(product));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productGetResponse;
	}

	public ProductSearchResponse productsSearch(ProductSearchRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareProductsSearch(req);
		return sendProductsSearch(params);
	}
	//自动转换method的重命名
	public ProductSearchResponse productSearch(ProductSearchRequest req) throws TaobaoApiException {
		return productsSearch(req);
	}

	public Map<String, Object> prepareProductsSearch(ProductSearchRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductSearchRequest.FIELDS, req.getFields());
		params.put(ProductSearchRequest.Q, req.getQ());
		params.put(ProductSearchRequest.CID, req.getCid());
		params.put(ProductSearchRequest.PROPS, req.getProps());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		return params;
	}

	public ProductSearchResponse sendProductsSearch(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCTS_SEARCH.getMethod(), params));
		ProductSearchResponse productSearchResponse = new ProductSearchResponse(response);
		// 平台访问成功
		if (productSearchResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(productSearchResponse.getBody());
				parseError(productSearchResponse, json);
				// 访问淘宝成功
				if (productSearchResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductSearchRequest.PRODUCTS)) {
						JSONArray products = rsp.getJSONArray(ProductSearchRequest.PRODUCTS);
						productSearchResponse.setProduct(TaobaoProductJSONConvert
								.convertJsonArrayToProductList(products));
					}
					setTotalResults(rsp, productSearchResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productSearchResponse;
	}

	// 交易部分

	public TradeGetResponse tradeGet(TradeGetRequest req) throws TaobaoApiException {
		return tradeGet(req, null);
	}

	public TradeGetResponse tradeGet(TradeGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeGet(req);
		return sendTradeGet(params, sessionId);
	}

	protected TradeGetResponse sendTradeGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_GET.getMethod(), sessionId, params));
		TradeGetResponse tradeGetResponse = new TradeGetResponse(response);
		// 平台成功访问
		if (tradeGetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(tradeGetResponse.getBody());
				parseError(tradeGetResponse, json);
				// 访问淘宝成功
				if (tradeGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						JSONObject trade = trades.getJSONObject(0);
						tradeGetResponse.setTrade(TaobaoTradeJSONConvert.convertJsonToTrade(trade));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeGetResponse;
	}

	public Map<String, Object> prepareTradeGet(TradeGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.TID, req.getTid());
		return params;
	}

	public TradesGetResponse tradesGet(TradesGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareTradesGet(req);
		return sendTradesGet(params, null, TaobaoApiMethod.TRADES_GET);
	}

	public Map<String, Object> prepareTradesGet(TradesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.SELLER_NICK, req.getSellerNick());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.TYPE, req.getType());
		return params;
	}

	public TradesGetResponse tradesBoughtGet(TradesBoughtGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradesBoughtGet(req);
		return sendTradesGet(params, sessionId, TaobaoApiMethod.TRADES_BOUGHT_GET);
	}

	public Map<String, Object> prepareTradesBoughtGet(TradesBoughtGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.START_CREATED, req.getStartCreated());
		params.put(ApiConstants.END_CREATED, req.getEndCreated());
		params.put(ApiConstants.TITLE, req.getTitle());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.SELLER_NICK, req.getSellerNick());
		params.put(ApiConstants.TYPE, req.getType());
		return params;
	}

	public TradesGetResponse tradesSoldGet(TradesSoldGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTradesSoldGet(req);
		return sendTradesGet(params, sessionId, TaobaoApiMethod.TRADES_SOLD_GET);
	}

	public Map<String, Object> prepareTradesSoldGet(TradesSoldGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.START_CREATED, req.getStartCreated());
		params.put(ApiConstants.END_CREATED, req.getEndCreated());
		params.put(ApiConstants.TITLE, req.getTitle());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.BUYER_NICK, req.getBuyerNick());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.RATE_STATUS, req.getRateStatus());
		return params;
	}

	public TradesGetResponse tradesSoldIncrementGet(TradesSoldIncrementGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTradesSoldIncrementGet(req);
		return sendTradesGet(params, sessionId, TaobaoApiMethod.TRADES_SOLD_INCREMENT_GET);
	}

	public Map<String, Object> prepareTradesSoldIncrementGet(TradesSoldIncrementGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.START_MODIFIED, req.getStartModified());
		params.put(ApiConstants.END_MODIFIED, req.getEndModified());
		return params;
	}

	/**
	 * Support: 1、taobao.trades.get 2、taobao.trades.bought.get
	 * 3、taobao.trades.sold.get 4、taobao.trades.sold.increment.get
	 */
	protected TradesGetResponse sendTradesGet(Map<String, Object> params, String sessionId,
			TaobaoApiMethod apiMethod) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(apiMethod.getMethod(), sessionId, params));
		TradesGetResponse tradesGetResponse = new TradesGetResponse(response);
		// 平台访问成功
		if (tradesGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(tradesGetResponse.getBody());
				parseError(tradesGetResponse, json);
				// 淘宝访问成功
				if (tradesGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						tradesGetResponse.setTrades(TaobaoTradeJSONConvert
								.convertJsonArrayToTradeList(trades));
					}
					setTotalResults(rsp, tradesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradesGetResponse;
	}

	// --------------------------评价---------------------------------

	public Map<String, Object> prepareTraderateAdd(TradeRateAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.ORDER_ID, req.getOrderId());
		params.put(ApiConstants.OID, req.getOrderId());
		params.put(ApiConstants.ROLE, req.getRole());
		params.put(ApiConstants.ANONY, req.getAnony());
		params.put(ApiConstants.CONTENT, req.getContent());
		params.put(ApiConstants.RESULT, req.getResult());
		return params;
	}

	public TradeRateAddResponse sendTradeRateAdd(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADERATE_ADD.getMethod(), sessionId, params));
		TradeRateAddResponse tradeRateAddResponse = new TradeRateAddResponse(response);
		if (tradeRateAddResponse.isSuccess()) {// 平台成功访问
			try {
				JSONObject json = new JSONObject(tradeRateAddResponse.getBody());
				parseError(tradeRateAddResponse, json);
				if (tradeRateAddResponse.isSuccess()) {// 访问淘宝成功
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);// 对返回结果进行处理
					if (rsp.has(ApiConstants.TRADERATES)) {
						JSONArray tradeRates = rsp.getJSONArray(ApiConstants.TRADERATES);
						JSONObject tradeRate = tradeRates.getJSONObject(0);
						TradeRate rate = new TradeRate();
						try {
							rate.setCreated(DateUtil.strToDate(tradeRate
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						if (tradeRate.has(ApiConstants.TID)) {
							rate.setTid(tradeRate.getString(ApiConstants.TID));
						}
						if (tradeRate.has(ApiConstants.ORDER_ID)) {
							rate.setOrderId(tradeRate.getString(ApiConstants.ORDER_ID));
						}
						tradeRateAddResponse.setRate(rate);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeRateAddResponse;
	}
	
	public TradeRateAddResponse traderateAdd(TradeRateAddRequest req, String sessionId) throws TaobaoApiException {
		return tradeRateAdd(req, sessionId);
	}
	
	public TradeRateAddResponse tradeRateAdd(TradeRateAddRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTraderateAdd(req);
		return sendTradeRateAdd(params, sessionId);
	}

	public Map<String, Object> prepareTraderateListAdd(TradeRateListAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.ROLE, req.getRole());
		params.put(ApiConstants.ANONY, req.getAnony());
		params.put(ApiConstants.CONTENT, req.getContent());
		params.put(ApiConstants.RESULT, req.getResult());
		return params;
	}

	public TradeRateListAddResponse sendTradeRateListAdd(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADERATE_LIST_ADD.getMethod(), sessionId,
						params));
		TradeRateListAddResponse tradeRateListAddResponse = new TradeRateListAddResponse(response);
		if (tradeRateListAddResponse.isSuccess()) {// 平台成功访问
			try {
				JSONObject json = new JSONObject(tradeRateListAddResponse.getBody());
				parseError(tradeRateListAddResponse, json);
				if (tradeRateListAddResponse.isSuccess()) {// 访问淘宝成功
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP); // 对返回结果进行处理
					if (rsp.has(ApiConstants.TRADERATES)) {
						JSONArray tradeRates = rsp.getJSONArray(ApiConstants.TRADERATES);
						JSONObject tradeRate = tradeRates.getJSONObject(0);
						TradeRate rate = new TradeRate();
						try {
							rate.setCreated(DateUtil.strToDate(tradeRate
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						rate.setTid(tradeRate.getString(ApiConstants.TID));
						tradeRateListAddResponse.setRate(rate);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeRateListAddResponse;
	}
	
	public TradeRateListAddResponse traderateListAdd(TradeRateListAddRequest req, String sessionId) throws TaobaoApiException {
		return tradeRateListAdd(req, sessionId);
	}
	
	public TradeRateListAddResponse tradeRateListAdd(TradeRateListAddRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTraderateListAdd(req);
		return sendTradeRateListAdd(params, sessionId);
	}

	public Map<String, Object> prepareTraderatesGet(TradeRatesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.ROLE, req.getRole());
		params.put(ApiConstants.RATE_TYPE, req.getRateType());
		params.put(ApiConstants.RESULT, req.getResult());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		return params;
	}

	public TradeRatesGetResponse sendTraderatesGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADERATES_GET.getMethod(), sessionId, params));
		TradeRatesGetResponse tradeRatesGetResponse = new TradeRatesGetResponse(response);
		if (tradeRatesGetResponse.isSuccess()) {// 平台访问成功
			JSONObject json;
			try {
				json = new JSONObject(tradeRatesGetResponse.getBody());
				parseError(tradeRatesGetResponse, json);
				if (tradeRatesGetResponse.isSuccess()) {// 访问淘宝成功
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);// 对返回结果进行处理
					if (rsp.has(ApiConstants.TRADERATES)) {
						JSONArray tradeRates = rsp.getJSONArray(ApiConstants.TRADERATES);
						tradeRatesGetResponse.setTradeRates(TaobaoTradeJSONConvert
								.convertJsonArrayToTradeRateList(tradeRates));
					}
					setTotalResults(rsp, tradeRatesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeRatesGetResponse;
	}
	
	public TradeRatesGetResponse traderatesGet(
			TradeRatesGetRequest tradeRatesGetRequest, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTraderatesGet(tradeRatesGetRequest);
		return sendTraderatesGet(params, sessionId);
	}

	public TradeRatesGetResponse tradeRatesGet(TradeRatesGetRequest req, String sessionId)
			throws TaobaoApiException {
		return traderatesGet(req, sessionId);
	}

	public TradeConfirmFeeGetResponse tradeConfirmfeeGet(TradeConfirmFeeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return tradeConfirmFeeGet(req, sessionId);
	}
	
	public TradeConfirmFeeGetResponse tradeConfirmFeeGet(TradeConfirmFeeGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTradeConfirmFeeGet(req);
		return sendTradeConfirmFeeGet(params, sessionId);
	}
	
	public Map<String, Object> prepareTradeConfirmfeeGet(TradeConfirmFeeGetRequest req) {
		return prepareTradeConfirmFeeGet(req);
	}

	public Map<String, Object> prepareTradeConfirmFeeGet(TradeConfirmFeeGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.IS_DETAIL, req.getIsDetail());
		return params;
	}

	protected TradeConfirmFeeGetResponse sendTradeConfirmFeeGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse taobaoResponse = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_CONFIRMFEE_GET.getMethod(), sessionId,
						params));

		TradeConfirmFeeGetResponse response = new TradeConfirmFeeGetResponse(taobaoResponse);

		// 平台访问成功
		if (response.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(response.getBody());
				parseError(response, json);
				// 访问淘宝成功
				if (response.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.CONFIRM_FEES)) {
						json = rsp.getJSONArray(ApiConstants.CONFIRM_FEES).getJSONObject(0);
						TradeConfirmFee confirmFee = new TradeConfirmFee();
						if (json.has(ApiConstants.CONFIRM_FEE))
							confirmFee.setConfirmFee(json.getString(ApiConstants.CONFIRM_FEE));
						if (json.has(ApiConstants.CONFIRM_POST_FEE))
							confirmFee.setConfirmPostFee(json
									.getString(ApiConstants.CONFIRM_POST_FEE));
						if (json.has(ApiConstants.IS_LAST_DETAIL_ORDER))
							confirmFee.setIsLastDetailOrder(json
									.getString(ApiConstants.IS_LAST_DETAIL_ORDER));

						response.setConfirmFee(confirmFee);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return response;
	}

	// 用户部分

	public UserGetResponse userGet(UserGetRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareUserGet(req);
		return sendUserGet(params, sessionId);
	}

	public Map<String, Object> prepareUserGet(UserGetRequest userGetRequest) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, userGetRequest.getFields());
		params.put(ApiConstants.NICK, userGetRequest.getNick());
		return params;
	}

	protected UserGetResponse sendUserGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.USER_GET.getMethod(), sessionId, params));
		UserGetResponse userGetResponse = new UserGetResponse(response);
		// 平台访问成功
		if (userGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(userGetResponse.getBody());
				parseError(userGetResponse, json);
				// 淘宝访问成功
				if (userGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.USERS)) {
						JSONArray users = rsp.getJSONArray(ApiConstants.USERS);
						JSONObject user = users.getJSONObject(0);
						userGetResponse.setUser(TaobaoUserJSONConvert.convertJsonToUser(user));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return userGetResponse;
	}

	public UserGetResponse userGet(UserGetRequest req) throws TaobaoApiException {
		return this.userGet(req, null);
	}

	public UserDetailGetResponse userDetailGet(UserDetailGetRequest req, String sessionId)
		throws TaobaoApiException {
		return userDetail(req, sessionId);
	}
	
	public UserDetailGetResponse userDetailGet(UserDetailGetRequest request) throws TaobaoApiException {
		return userDetailGet(request,null);
	}
	
	public UserDetailGetResponse userDetail(UserDetailGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareUserDetail(req);
		return sendUserDetail(params, sessionId);
	}
	
	public Map<String, Object> prepareUserDetailGet(UserDetailGetRequest req) {
		return prepareUserDetail(req);
	}

	public Map<String, Object> prepareUserDetail(UserDetailGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.NICK, req.getNick());
		params.put(ApiConstants.ALIPAYNO, req.getAlipayNo());
		return params;
	}

	protected UserDetailGetResponse sendUserDetail(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.USER_DETAIL_GET.getMethod(), sessionId, params));
		UserDetailGetResponse userDetailGetResponse = new UserDetailGetResponse(response);
		// 平台访问成功
		if (userDetailGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(userDetailGetResponse.getBody());
				parseError(userDetailGetResponse, json);
				// 淘宝访问成功
				if (userDetailGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.USERS)) {
						JSONArray userDetails = rsp.getJSONArray(ApiConstants.USERS);
						JSONObject userDetail = userDetails.getJSONObject(0);
						userDetailGetResponse.setUserDetail(TaobaoUserJSONConvert
								.convertJsonToUser(userDetail));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return userDetailGetResponse;
	}

	public UsersGetResponse usersGet(UsersGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareUsersGet(req);
		return sendUsersGet(params);
	}

	public UsersGetResponse sendUsersGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.USERS_GET.getMethod(), params));
		UsersGetResponse usersGetResponse = new UsersGetResponse(response);
		// 平台访问成功
		if (usersGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(usersGetResponse.getBody());
				parseError(usersGetResponse, json);
				// 淘宝访问成功
				if (usersGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.USERS)) {
						JSONArray users = rsp.getJSONArray(ApiConstants.USERS);
						usersGetResponse.setUsers(TaobaoUserJSONConvert
								.convertJsonArrayToUserList(users));
					}
					setTotalResults(rsp, usersGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return usersGetResponse;
	}

	public Map<String, Object> prepareUsersGet(UsersGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.NICKS, req.getNicks());
		return params;
	}

	// 店铺部分

	public ShopGetResponse shopGet(ShopGetRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareShopGet(req);
		return sendShopGet(params, sessionId);
	}

	public ShopGetResponse shopGet(ShopGetRequest req) throws TaobaoApiException {
		return this.shopGet(req, null);
	}

	public Map<String, Object> prepareShopGet(ShopGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.NICK, req.getNick());
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	protected ShopGetResponse sendShopGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOP_GET.getMethod(), sessionId, params));
		ShopGetResponse shopGetResponse = new ShopGetResponse(response);
		// 平台访问成功
		if (shopGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(shopGetResponse.getBody());
				parseError(shopGetResponse, json);
				// 淘宝访问成功
				if (shopGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHOPS)) {
						JSONArray shops = rsp.getJSONArray(ApiConstants.SHOPS);
						JSONObject shop = shops.getJSONObject(0);
						shopGetResponse.setShop(TaobaoShopJSONConvert.convertJsonToShop(shop));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return shopGetResponse;
	}

	public Map<String, Object> prepareShopUpdate(ShopUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TITLE, req.getTitle());
		params.put(ApiConstants.DESC, req.getDesc());
		params.put(ApiConstants.BULLETIN, req.getBulletin());
		return params;
	}

	public ShopUpdateResponse shopUpdate(ShopUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareShopUpdate(req);
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOP_UPDATE.getMethod(), sessionId, params));
		ShopUpdateResponse shopUpdateResponse = new ShopUpdateResponse(response);
		// 平台成功访问
		if (shopUpdateResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(shopUpdateResponse.getBody());
				parseError(shopUpdateResponse, json);
				// 访问淘宝成功
				if (shopUpdateResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHOPS)) {
						JSONArray shops = rsp.getJSONArray(ApiConstants.SHOPS);
						JSONObject shop = shops.getJSONObject(0);
						shopUpdateResponse.setShop(TaobaoShopJSONConvert.convertJsonToShop(shop));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return shopUpdateResponse;
	}

	public ShopShowcaseRemainCountResponse shopShowcaseRemainCount(
			ShopShowcaseRemainCountRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareShopShowcaseRemainCount(req);
		return sendShopShowcaseRemainCount(params, sessionId);
	}

	public Map<String, Object> prepareShopShowcaseRemainCount(ShopShowcaseRemainCountRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		return params;
	}

	protected ShopShowcaseRemainCountResponse sendShopShowcaseRemainCount(
			Map<String, Object> params, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOP_SHOWCASE_REMAINCOUNT.getMethod(),
						sessionId, params));
		ShopShowcaseRemainCountResponse ssrcr = new ShopShowcaseRemainCountResponse(response);
		// 平台成功访问
		if (ssrcr.isSuccess()) {
			try {
				JSONObject json = new JSONObject(ssrcr.getBody());
				parseError(ssrcr, json);
				// 访问淘宝成功
				if (ssrcr.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHOPS)) {
						JSONArray shops = rsp.getJSONArray(ApiConstants.SHOPS);
						JSONObject shop = shops.getJSONObject(0);
						try {
							ssrcr.setRemainShowcase(Integer.parseInt(shop
									.getString(ApiConstants.REMAIN_COUNT)));
						} catch (JSONException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}

		return ssrcr;
	}

	// ===================================物流部分===============================

	public Map<String, Object> prepareLogisticsOrdersGet(LogisticsOrdersGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.BUYER_NICK, req.getBuyerNick());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.RECEIVER_NAME, req.getReceiverName());
		params.put(ApiConstants.START_CREATED, req.getStartCreated());
		params.put(ApiConstants.END_CREATED, req.getEndCreated());
		params.put(ApiConstants.ITEM_TITLE, req.getItemTitle());
		params.put(ApiConstants.FREIGHT_PAYER, req.getFreightPayer());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.SELLER_CONFIRM, req.getSellerConfirm());
		return params;
	}
	public Map<String, Object> prepareLogisticsOrdersDetailGet(LogisticsOrdersDetailGetRequest req) {
		return this.prepareLogisticsOrdersGet(req);
	}
	public Map<String, Object> prepareShippingsSendGet(ShippingsSendGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.BUYER_NICK, req.getBuyerNick());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.RECEIVER_NAME, req.getReceiverName());
		params.put(ApiConstants.START_CREATED, req.getStartCreated());
		params.put(ApiConstants.END_CREATED, req.getEndCreated());
		params.put(ApiConstants.ITEM_TITLE, req.getItemTitle());
		params.put(ApiConstants.FREIGHT_PAYER, req.getFreightPayer());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.SELLER_CONFIRM, req.getSellerConfirm());
		return params;
	}
	public Map<String, Object> prepareShippingsSendFullInfoGet(ShippingsSendFullInfoGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.BUYER_NICK, req.getBuyerNick());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.RECEIVER_NAME, req.getReceiverName());
		params.put(ApiConstants.START_CREATED, req.getStartCreated());
		params.put(ApiConstants.END_CREATED, req.getEndCreated());
		params.put(ApiConstants.ITEM_TITLE, req.getItemTitle());
		params.put(ApiConstants.FREIGHT_PAYER, req.getFreightPayer());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.SELLER_CONFIRM, req.getSellerConfirm());
		return params;
	}
	public ShippingsSendGetResponse shippingsSendGet(ShippingsSendGetRequest req, String sessionId)
			throws TaobaoApiException {
		return new ShippingsSendGetResponse(this.logisticsOrdersGet(req, sessionId));
	}

	public LogisticsOrdersGetResponse logisticsOrdersGet(LogisticsOrdersGetRequest request,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareLogisticsOrdersGet(request);
		return sendLogisticsOrdersGet(params, sessionId);
	}

	public LogisticsOrdersGetResponse sendLogisticsOrdersGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.LOGISTICS_ORDERS_GET.getMethod(), sessionId,
						params));
		LogisticsOrdersGetResponse logr = new LogisticsOrdersGetResponse(response);
		// 平台访问成功
		if (logr.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(logr.getBody());
				parseError(logr, json);
				// 访问淘宝成功
				if (logr.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHIPPINGS)) {
						JSONArray shippings = rsp.getJSONArray(ApiConstants.SHIPPINGS);
						logr.setShippings(TaobaoShippingJSONConvert
								.convertJsonArrayToShippingList(shippings));
					}
					setTotalResults(rsp, logr);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return logr;
	}

	@Deprecated
	public ShippingsSendFullInfoGetResponse shippingsSendFullInfoGet(
			ShippingsSendFullInfoGetRequest req, String sessionId) throws TaobaoApiException {
		return new ShippingsSendFullInfoGetResponse(this.logisticsOrdersDetailGet(req, sessionId));
	}

	public LogisticsOrdersDetailGetResponse logisticsOrdersDetailGet(
			LogisticsOrdersDetailGetRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareLogisticsOrdersGet(req);
		return sendLogisticsOrdersDetailGet(params, sessionId);
	}

	protected LogisticsOrdersDetailGetResponse sendLogisticsOrdersDetailGet(
			Map<String, Object> params, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.LOGISTICS_ORDERS_DETAIL_GET.getMethod(),
						sessionId, params));
		LogisticsOrdersDetailGetResponse lodgr = new LogisticsOrdersDetailGetResponse(response);
		// 平台访问成功
		if (lodgr.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(lodgr.getBody());
				parseError(lodgr, json);
				// 访问淘宝成功
				if (lodgr.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHIPPINGS)) {
						JSONArray shippings = rsp.getJSONArray(ApiConstants.SHIPPINGS);
						lodgr.setShippings(TaobaoShippingJSONConvert
								.convertJsonArrayToShippingList(shippings));
					}
					setTotalResults(rsp, lodgr);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return lodgr;
	}

	public AreasGetResponse areasGet(AreasGetRequest request) throws TaobaoApiException {
		Map<String, Object> params = prepareAreasGet(request);
		return sendAreasGet(params);
	}

	public AreasGetResponse sendAreasGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.AREAS_GET.getMethod(), params));
		AreasGetResponse areasGetResponse = new AreasGetResponse(response);
		// 平台访问成功
		if (areasGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(areasGetResponse.getBody());
				parseError(areasGetResponse, json);
				// 访问淘宝成功
				if (areasGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.AREAS)) {
						JSONArray areas = rsp.getJSONArray(ApiConstants.AREAS);
						areasGetResponse.setAreas(TaobaoShippingJSONConvert
								.convertJsonArrayToAreaList(areas));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return areasGetResponse;
	}

	public Map<String, Object> prepareAreasGet(AreasGetRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, request.getFields());
		return params;
	}

	@Deprecated
	public LogisticCompaniesGetResponse logisticCompaniesGet(LogisticCompaniesGetRequest request)
			throws TaobaoApiException {
		return new LogisticCompaniesGetResponse(this.logisticsCompaniesGet(request));
	}

	public LogisticsCompaniesGetResponse logisticsCompaniesGet(LogisticsCompaniesGetRequest request)
			throws TaobaoApiException {
		Map<String, Object> params = prepareLogisticsCompaniesGet(request);
		return sendLogisticsCompaniesGet(params);
	}

	protected LogisticsCompaniesGetResponse sendLogisticsCompaniesGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch()
				.fetch(
						this.getServiceUrl(),
						getTemplateRequest(
								TaobaoApiMethod.LOGISTICS_COMPPANIES_GET_NEW.getMethod(), params));
		LogisticsCompaniesGetResponse lcgr = new LogisticsCompaniesGetResponse(response);
		// 平台访问成功
		if (lcgr.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(lcgr.getBody());
				parseError(lcgr, json);
				// 访问淘宝成功
				if (lcgr.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.LOGISTICS_COMPANY)) {
						JSONArray jsonArray = rsp.getJSONArray(ApiConstants.LOGISTICS_COMPANY);
						lcgr.setLogisticCompanies(TaobaoShippingJSONConvert
								.convertJsonArrayToLogisticCompanyList(jsonArray));
					}
					setTotalResults(rsp, lcgr);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return lcgr;
	}

	public Map<String, Object> prepareLogisticsCompaniesGet(LogisticsCompaniesGetRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, request.getFields());
		params.put(ApiConstants.IS_RECOMMENTED, request.getIsRecommended());
		params.put(ApiConstants.ORDER_MODE, request.getOrderMode());
		return params;
	}                                    
	public Map<String, Object> prepareLogisticCompaniesGet(LogisticCompaniesGetRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, request.getFields());
		params.put(ApiConstants.IS_RECOMMENTED, request.getIsRecommended());
		params.put(ApiConstants.ORDER_MODE, request.getOrderMode());
		return params;
	}

	public DeliverySendResponse deliverySend(DeliverySendRequest request, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareDeliverySend(request);
		return sendDeliverySend(params, sessionId);
	}

	protected DeliverySendResponse sendDeliverySend(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.DELIVERY_SEND.getMethod(), sessionId, params));
		DeliverySendResponse deliverySendResponse = new DeliverySendResponse(response);
		if (deliverySendResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(deliverySendResponse.getBody());
				parseError(deliverySendResponse, json);
				// 访问淘宝成功
				if (deliverySendResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHIPPINGS)) {
						JSONArray shipping = rsp.getJSONArray(ApiConstants.SHIPPINGS);
						Shipping deliverySens = TaobaoShippingJSONConvert
								.convertJsonToShippingDeliverySend(shipping.getJSONObject(0));
						Shipping ship = new Shipping();
						ship.setDeliverSuccess(deliverySens.isSuccess());
						deliverySendResponse.setShipping(ship);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return deliverySendResponse;
	}

	public Map<String, Object> prepareDeliverySend(DeliverySendRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.COMPANY_CODE, req.getCompanyCode());
		params.put(ApiConstants.OUT_SID, req.getOutSid());
		params.put(ApiConstants.SELLER_NAME, req.getSellerName());
		params.put(ApiConstants.SELLER_AREA_ID, req.getSellerAreaId());
		params.put(ApiConstants.SELLER_ADDRESS, req.getSellerAddress());
		params.put(ApiConstants.SELLER_ZIP, req.getSellerZip());
		params.put(ApiConstants.SELLER_PHONE, req.getSellerPhone());
		params.put(ApiConstants.SELLER_MOBILE, req.getSellerMobile());
		params.put(ApiConstants.MEMO, req.getMemo());
		params.put(ApiConstants.DELIVERY_SEND_TYPE, req.getOrderType());
		return params;
	}

	public Map<String, Object> prepareDeliveryCodSend(DeliveryCodSendRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.COMPANY_CODE, req.getCompanyCode());
		params.put(ApiConstants.SELLER_NAME, req.getSellerName());
		params.put(ApiConstants.SELLER_AREA_ID, req.getSellerAreaId());
		params.put(ApiConstants.SELLER_ADDRESS, req.getSellerAddress());
		params.put(ApiConstants.SELLER_ZIP, req.getSellerZip());
		params.put(ApiConstants.SELLER_PHONE, req.getSellerPhone());
		params.put(ApiConstants.SELLER_MOBILE, req.getSellerMobile());
		params.put(ApiConstants.DELIVERY_SEND_TYPE, req.getOrderType());
		
		params.put(ApiConstants.FETCHER_NAME, req.getFetcherName());
		params.put(ApiConstants.FETCHER_AREA_ID, req.getFetcherAreaId());
		params.put(ApiConstants.FETCHER_ADDRESS, req.getFetcherAddress());
		params.put(ApiConstants.FETCHER_ZIP, req.getFetcherZip());
		params.put(ApiConstants.FETCHER_PHONE, req.getFetcherPhone());
		params.put(ApiConstants.FETCHER_MOBILE, req.getFetcherMobile());
		
		
		
		return params;
	}
	
	public Map<String, Object> prepareLogisticsPartnersGet(LogisticsPartnersGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.SERVICE_TYPE, req.getServiceType());
		params.put(ApiConstants.SOURCE_ID, req.getSourceId());
		params.put(ApiConstants.TARGET_ID, req.getTargetId());
		params.put(ApiConstants.GOODS_VALUE, req.getGoodsValue());
		
		return params;
	}

	// ======================================邮费模板=========================================

	public PostageGetResponse postageGet(PostageGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = preparePostageGet(req);
		return sendPostageGet(params);
	}

	public Map<String, Object> preparePostageGet(PostageGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.POSTAGE_ID, req.getPostageId());
		params.put(ApiConstants.NICK, req.getNick());
		return params;
	}

	public PostageGetResponse sendPostageGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.POSTAGE_GET.getMethod(), params));
		PostageGetResponse postageGetResponse = new PostageGetResponse(response);
		if (postageGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(postageGetResponse.getBody());
				parseError(postageGetResponse, json);
				// 访问淘宝成功
				if (postageGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.POSTAGES)) {
						JSONArray postages = rsp.getJSONArray(ApiConstants.POSTAGES);
						JSONObject postage = postages.getJSONObject(0);
						postageGetResponse.setPostage(TaobaoPostageJSONConvert
								.convertJsonToPostage(postage));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return postageGetResponse;
	}

	public PostagesGetResponse postagesGet(PostagesGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = preparePostagesGet(req);
		return sendPostagesGet(params, sessionId);
	}

	public Map<String, Object> preparePostagesGet(PostagesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	public PostagesGetResponse sendPostagesGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.POSTAGES_GET.getMethod(), sessionId, params));
		PostagesGetResponse postagesGetResponse = new PostagesGetResponse(response);
		if (postagesGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(postagesGetResponse.getBody());
				parseError(postagesGetResponse, json);
				// 访问淘宝成功
				if (postagesGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.POSTAGES)) {
						JSONArray postages = rsp.getJSONArray(ApiConstants.POSTAGES);
						postagesGetResponse.setPostages(TaobaoPostageJSONConvert
								.convertJsonArrayToPostageList(postages));
					}
					setTotalResults(rsp, postagesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return postagesGetResponse;
	}

	public PostageAddResponse postageAdd(PostageAddRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = preparePostageAdd(req);
		return sendPostageAdd(params, sessionId);
	}

	public PostageAddResponse sendPostageAdd(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.POSTAGE_ADD.getMethod(), sessionId, params));
		PostageAddResponse postageAddResonse = new PostageAddResponse(response);
		if (postageAddResonse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(postageAddResonse.getBody());
				parseError(postageAddResonse, json);
				// 访问淘宝成功
				if (postageAddResonse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.POSTAGES)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.POSTAGES);
						JSONObject postageJson = items.getJSONObject(0);
						Postage postage = new Postage();
						postage.setPostageId(postageJson.getString(ApiConstants.POSTAGE_ID));
						try {
							postage.setCreated(DateUtil.strToDate(postageJson
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						postageAddResonse.setPostage(postage);
					}
				}

			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return postageAddResonse;
	}

	public Map<String, Object> preparePostageAdd(PostageAddRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.NAME, request.getName());
		params.put(ApiConstants.MEMO, request.getMemo());
		params.put(ApiConstants.POST_PRICE, request.getPostPrice());
		params.put(ApiConstants.POST_INCREASE, request.getPostIncrease());
		params.put(ApiConstants.EXPRESS_PRICE, request.getExpressPrice());
		params.put(ApiConstants.EXPRESS_INCREASE, request.getExpressIncrease());
		params.put(ApiConstants.EMS_PRICE, request.getEmsPrice());
		params.put(ApiConstants.EMS_INCREASE, request.getEmsIncrease());
		params.put(ApiConstants.POSTAGE_MODE_TYPES, request.getPostageModeType());
		params.put(ApiConstants.POSTAGE_MODE_DESTS, request.getPostageModeDest());
		params.put(ApiConstants.POSTAGE_MODE_PRICES, request.getPostageModePrice());
		params.put(ApiConstants.POSTAGE_MODE_INCREASES, request.getPostageModeIncrease());
		return params;
	}

	public PostageUpdateResponse postageUpdate(PostageUpdateRequest request, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = preparePostageUpdate(request);
		return sendPostageUpdate(params, sessionId);
	}

	public PostageUpdateResponse sendPostageUpdate(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.POSTAGE_UPDATE.getMethod(), sessionId, params));
		PostageUpdateResponse postageUpdateResonse = new PostageUpdateResponse(response);
		if (postageUpdateResonse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(postageUpdateResonse.getBody());
				parseError(postageUpdateResonse, json);
				// 访问淘宝成功
				if (postageUpdateResonse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.POSTAGES)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.POSTAGES);
						JSONObject postageJson = items.getJSONObject(0);
						Postage postage = new Postage();
						postage.setPostageId(postageJson.getString(ApiConstants.POSTAGE_ID));
						try {
							postage.setModified(DateUtil.strToDate(postageJson
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						postageUpdateResonse.setPostage(postage);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return postageUpdateResonse;
	}

	public Map<String, Object> preparePostageUpdate(PostageUpdateRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.POSTAGE_ID, request.getPostage_id());
		params.put(ApiConstants.NAME, request.getName());
		params.put(ApiConstants.MEMO, request.getMemo());
		params.put(ApiConstants.POST_PRICE, request.getPostPrice());
		params.put(ApiConstants.POST_INCREASE, request.getPostIncrease());
		params.put(ApiConstants.EXPRESS_PRICE, request.getExpressPrice());
		params.put(ApiConstants.EXPRESS_INCREASE, request.getExpressIncrease());
		params.put(ApiConstants.EMS_PRICE, request.getEmsPrice());
		params.put(ApiConstants.EMS_INCREASE, request.getEmsIncrease());
		params.put(ApiConstants.POSTAGE_MODE_TYPES, request.getPostageModeType());
		params.put(ApiConstants.POSTAGE_MODE_DESTS, request.getPostageModeDest());
		params.put(ApiConstants.POSTAGE_MODE_PRICES, request.getPostageModePrice());
		params.put(ApiConstants.POSTAGE_MODE_INCREASES, request.getPostageModeIncrease());
		params.put(ApiConstants.POSTAGE_MODE_IDS, request.getPostageModeId());
		params.put(ApiConstants.POSTAGE_MODE_OPTTYPE, request.getPostageModeOpttype());
		return params;
	}

	public PostageDeleteResponse postageDelete(PostageDeleteRequest request, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = preparePostageDelete(request);
		return sendPostageDelete(params, sessionId);
	}

	public Map<String, Object> preparePostageDelete(PostageDeleteRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.POSTAGE_ID, request.getPostageId());
		return params;
	}

	public PostageDeleteResponse sendPostageDelete(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.POSTAGE_DELETE.getMethod(), sessionId, params));
		PostageDeleteResponse postageDeleteResponse = new PostageDeleteResponse(response);
		if (postageDeleteResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(postageDeleteResponse.getBody());
				parseError(postageDeleteResponse, json);
				// 访问淘宝成功
				if (postageDeleteResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.POSTAGES)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.POSTAGES);
						JSONObject postageJson = items.getJSONObject(0);
						Postage postage = new Postage();
						postage.setPostageId(postageJson.getString(ApiConstants.POSTAGE_ID));
						try {
							postage.setCreated(DateUtil.strToDate(postageJson
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						postageDeleteResponse.setPostage(postage);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return postageDeleteResponse;
	}

	// *******************库存部分*******************************

	public SkuAddResponse itemSkuAdd(SkuAddRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemSkuAdd(req);
		return sendItemSkuAdd(params, sessionId);
	}
	//自动转换method的重命名
	public SkuAddResponse skuAdd(SkuAddRequest req, String sessionId) throws TaobaoApiException {
		return itemSkuAdd(req, sessionId);
	}

	public Map<String, Object> prepareItemSkuAdd(SkuAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.PROPERTIES, req.getProperties());
		params.put(ApiConstants.QUANTITY, req.getQuantity());
		params.put(ApiConstants.PRICE, req.getPrice());
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		params.put(ApiConstants.LANG, req.getLang());
		return params;
	}

	public SkuAddResponse sendItemSkuAdd(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_SKU_ADD.getMethod(), sessionId, params));
		SkuAddResponse skuAddResponse = new SkuAddResponse(response);
		if (skuAddResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skuAddResponse.getBody());
				parseError(skuAddResponse, json);
				if (skuAddResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						JSONObject sku = skus.getJSONObject(0);
						Sku addSku = new Sku();
						addSku.setIid(sku.getString(ApiConstants.IID));
						try {
							addSku.setCreated(DateUtil.strToDate(sku
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						skuAddResponse.setSku(addSku);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skuAddResponse;
	}

	public SkuGetResponse itemSkuGet(SkuGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemSkuGet(req);
		return sendItemSkuGet(params);
	}
	//自动转换method的重命名
	public SkuGetResponse skuGet(SkuGetRequest req) throws TaobaoApiException {
		return itemSkuGet(req);
	}

	public Map<String, Object> prepareItemSkuGet(SkuGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.SKU_ID, req.getSkuId());
		params.put(ApiConstants.NICK, req.getNick());
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	public SkuGetResponse sendItemSkuGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_SKU_GET.getMethod(), params));
		SkuGetResponse skuGetResponse = new SkuGetResponse(response);
		if (skuGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skuGetResponse.getBody());
				parseError(skuGetResponse, json);
				if (skuGetResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						JSONObject sku = skus.getJSONObject(0);
						skuGetResponse.setSku(TaobaoSkuJSONConvert.convertJsonToSku(sku));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skuGetResponse;
	}

	public SkuUpdateResponse itemSkuUpdate(SkuUpdateRequest req, String sessionId)
		throws TaobaoApiException {
		Map<String, Object> params = prepareItemSkuUpdate(req);
		return sendItemSkuUpdate(params, sessionId);
	}
	//自动转换method的重命名
	public SkuUpdateResponse skuUpdate(SkuUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		return itemSkuUpdate(req, sessionId);
	}

	public Map<String, Object> prepareItemSkuUpdate(SkuUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.PROPERTIES, req.getProperties());
		params.put(ApiConstants.QUANTITY, req.getQuantity());
		params.put(ApiConstants.PRICE, req.getPrice());
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		params.put(ApiConstants.LANG, req.getLang());
		params.put(ApiConstants.SKU_ID, req.getSkuId());
		return params;
	}

	public SkuUpdateResponse sendItemSkuUpdate(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_SKU_UPDATE.getMethod(), sessionId, params));
		SkuUpdateResponse skuUpdateResponse = new SkuUpdateResponse(response);
		if (skuUpdateResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skuUpdateResponse.getBody());
				parseError(skuUpdateResponse, json);
				if (skuUpdateResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						JSONObject sku = skus.getJSONObject(0);
						Sku updatedSku = new Sku();
						updatedSku.setIid(sku.getString(ApiConstants.IID));
						try {
							updatedSku.setModified(DateUtil.strToDate(sku
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						skuUpdateResponse.setSku(updatedSku);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skuUpdateResponse;
	}

	public SkusGetResponse itemSkusGet(SkusGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareItemSkusGet(req);
		return sendItemSkusGet(params);
	}
	//为了2.0自动转换重命名方法
	public SkusGetResponse skusGet(SkusGetRequest req) throws TaobaoApiException {
		return itemSkusGet(req);
	}

	public Map<String, Object> prepareItemSkusGet(SkusGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IIDS, req.getIids());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.NICK, req.getNick());
		return params;
	}

	public SkusGetResponse sendItemSkusGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_SKUS_GET.getMethod(), params));
		SkusGetResponse skusGetResponse = new SkusGetResponse(response);
		if (skusGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skusGetResponse.getBody());
				parseError(skusGetResponse, json);
				if (skusGetResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						skusGetResponse.setSkus(TaobaoSkuJSONConvert
								.convertJsonArrayToSkuList(skus));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skusGetResponse;
	}

	public ItemJointPropimgResponse itemJointPropimg(ItemJointPropimgRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemJointPropimg(req);
		return sendItemJointPropimg(sessionId, params);
	}

	protected ItemJointPropimgResponse sendItemJointPropimg(String sessionId,
			Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse taobaoResponse = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_JOINT_PROPIMG.getMethod(), sessionId,
						params));
		ItemJointPropimgResponse ijpir = new ItemJointPropimgResponse(taobaoResponse);
		if (ijpir.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(ijpir.getBody());
				parseError(ijpir, json);
				if (ijpir.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.PROPIMGS)) {
						JSONArray propimgs = rsp.getJSONArray(ApiConstants.PROPIMGS);
						JSONObject propimg = propimgs.getJSONObject(0);
						PropImg uploadPropImg = new PropImg();
						uploadPropImg.setPropimgId(propimg.getString(ApiConstants.PROPIMG_ID));
						uploadPropImg.setUrl(propimg.getString(ApiConstants.URL));
						uploadPropImg.setCreated(propimg.getString(ApiConstants.CREATED));
						ijpir.setPropImg(uploadPropImg);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return ijpir;
	}

	public Map<String, Object> prepareItemJointPropimg(ItemJointPropimgRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.PROPIMG_ID, req.getPropImgId());
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.PROPERTIES, req.getProperties());
		params.put(ApiConstants.POSITION, req.getPosition());
		params.put(ApiConstants.URL, req.getUrl());
		return params;
	}

	public ItemImgUploadResponse itemImgUpload(ItemImgUploadRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemImgUpload(req);
		return sendItemImgUpload(req, params, sessionId);
	}

	public Map<String, Object> prepareItemImgUpload(ItemImgUploadRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.ID, req.getId());
		params.put(ApiConstants.ITEMIMG_ID, req.getItemImgId());
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.POSITION, req.getPosition());
		params.put(ApiConstants.ISMAJOR, req.isMajor());
		return params;
	}

	public ItemImgUploadResponse sendItemImgUpload(ItemImgUploadRequest itemImgUploadRequest,
			Map<String, Object> params, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = null;
		ItemImgUploadResponse itemImgUploadResponse = null;
		if (itemImgUploadRequest.getImage() != null) {
			response = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_IMG_UPLOAD.getMethod(), sessionId,
							params), itemImgUploadRequest.getImage());
		} else {
			response = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_IMG_UPLOAD.getMethod(), sessionId,
							params));
		}
		itemImgUploadResponse = new ItemImgUploadResponse(response);
		if (itemImgUploadResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemImgUploadResponse.getBody());
				parseError(itemImgUploadResponse, json);
				if (itemImgUploadResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMIMGS)) {
						JSONArray itemImgs = rsp.getJSONArray(ApiConstants.ITEMIMGS);
						JSONObject itemImg = itemImgs.getJSONObject(0);
						ItemImg uploadItemImg = new ItemImg();
						uploadItemImg.setItemimgId(itemImg.getString(ApiConstants.ITEMIMG_ID));
						uploadItemImg.setUrl(itemImg.getString(ApiConstants.URL));
						uploadItemImg.setCreated(itemImg.getString(ApiConstants.CREATED));
						itemImgUploadResponse.setItemImg(uploadItemImg);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemImgUploadResponse;
	}

	public ItemImgDeleteResponse itemImgDelete(ItemImgDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemImgDelete(req);
		return sendItemImgDelete(params, sessionId);
	}

	public Map<String, Object> prepareItemImgDelete(ItemImgDeleteRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.ID, req.getId());
		params.put(ApiConstants.ITEMIMG_ID, req.getItemImgId());
		params.put(ApiConstants.IID, req.getIid());
		return params;
	}

	public ItemImgDeleteResponse sendItemImgDelete(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_IMG_DELETE.getMethod(), sessionId, params));
		ItemImgDeleteResponse itemImgDeleteResponse = new ItemImgDeleteResponse(response);
		if (itemImgDeleteResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemImgDeleteResponse.getBody());
				parseError(itemImgDeleteResponse, json);
				if (itemImgDeleteResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMIMGS)) {
						JSONArray itemImgs = rsp.getJSONArray(ApiConstants.ITEMIMGS);
						JSONObject itemImg = itemImgs.getJSONObject(0);
						ItemImg deleteItemImg = new ItemImg();
						deleteItemImg.setItemimgId(itemImg.getString(ApiConstants.ITEMIMG_ID));
						deleteItemImg.setCreated(itemImg.getString(ApiConstants.CREATED));
						itemImgDeleteResponse.setItemImg(deleteItemImg);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemImgDeleteResponse;
	}

	public PropimgUploadResponse itemPropimgUpload(PropimgUploadRequest req, String sessionId)
		throws TaobaoApiException {
		Map<String, Object> params = prepareItemPropimgUpload(req);
		return sendItemPropimgUpload(req, params, sessionId);
	}
	//自动转换method的重命名
	public PropimgUploadResponse propImgUpload(PropimgUploadRequest req, String sessionId)
			throws TaobaoApiException {
		return itemPropimgUpload(req, sessionId);
	}

	public Map<String, Object> prepareItemPropimgUpload(PropimgUploadRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.ID, req.getId());
		params.put(ApiConstants.PROPIMG_ID, req.getPropImgId());
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.PROPERTIES, req.getProperties());
		params.put(ApiConstants.POSITION, req.getPosition());
		return params;
	}

	public PropimgUploadResponse sendItemPropimgUpload(PropimgUploadRequest propimgUploadRequest,
			Map<String, Object> params, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = null;
		PropimgUploadResponse propimgUploadResponse = null;
		if (propimgUploadRequest.getImage() != null) {
			response = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_PROPIMG_UPLOAD.getMethod(), sessionId,
							params), propimgUploadRequest.getImage());
		} else {
			response = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.ITEM_PROPIMG_UPLOAD.getMethod(), sessionId,
							params));
		}
		propimgUploadResponse = new PropimgUploadResponse(response);
		if (propimgUploadResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(propimgUploadResponse.getBody());
				parseError(propimgUploadResponse, json);
				if (propimgUploadResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.PROPIMGS)) {
						JSONArray propimgs = rsp.getJSONArray(ApiConstants.PROPIMGS);
						JSONObject propimg = propimgs.getJSONObject(0);
						PropImg uploadPropImg = new PropImg();
						uploadPropImg.setPropimgId(propimg.getString(ApiConstants.PROPIMG_ID));
						uploadPropImg.setUrl(propimg.getString(ApiConstants.URL));
						uploadPropImg.setCreated(propimg.getString(ApiConstants.CREATED));
						propimgUploadResponse.setPropImg(uploadPropImg);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return propimgUploadResponse;
	}

	public PropImgDeleteResponse itemPropimgDelete(PropImgDeleteRequest req, String sessionId)
		throws TaobaoApiException {
		Map<String, Object> params = prepareItemPropimgDelete(req);
		return sendItemPropimgDelete(params, sessionId);
	}
	//自动转换method的重命名
	public PropImgDeleteResponse propImgDelete(PropImgDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		return itemPropimgDelete(req, sessionId);
	}

	public Map<String, Object> prepareItemPropimgDelete(PropImgDeleteRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.ID, req.getId());
		params.put(ApiConstants.PROPIMG_ID, req.getPropimgId());
		params.put(ApiConstants.IID, req.getIid());
		return params;
	}

	public PropImgDeleteResponse sendItemPropimgDelete(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_PROPIMG_DELETE.getMethod(), sessionId,
						params));
		PropImgDeleteResponse propimgDeleteResponse = new PropImgDeleteResponse(response);
		if (propimgDeleteResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(propimgDeleteResponse.getBody());
				parseError(propimgDeleteResponse, json);
				if (propimgDeleteResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.PROPIMGS)) {
						JSONArray propimgs = rsp.getJSONArray(ApiConstants.PROPIMGS);
						JSONObject propimg = propimgs.getJSONObject(0);
						PropImg deletePropimg = new PropImg();
						deletePropimg.setPropimgId(propimg.getString(ApiConstants.PROPIMG_ID));
						deletePropimg.setCreated(propimg.getString(ApiConstants.CREATED));
						propimgDeleteResponse.setPropImg(deletePropimg);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return propimgDeleteResponse;
	}

	public OrdersGetResponse ordersGet(OrdersGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareOrdersGet(req);
		return sendOrdersGet(params);
	}

	protected OrdersGetResponse sendOrdersGet(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ORDERS_GET.getMethod(), params));
		OrdersGetResponse ordersGetResponse = new OrdersGetResponse(response);
		// 平台访问成功
		if (ordersGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(ordersGetResponse.getBody());
				parseError(ordersGetResponse, json);
				// 淘宝访问成功
				if (ordersGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ORDERS)) {
						JSONArray orders = rsp.getJSONArray(ApiConstants.ORDERS);
						ordersGetResponse.setOrders(TaobaoTradeJSONConvert
								.convertJsonToOrderList(orders));
					}
					setTotalResults(rsp, ordersGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return ordersGetResponse;
	}

	public Map<String, Object> prepareOrdersGet(OrdersGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.SELLER_NICK, req.getSellerNick());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.TYPE, req.getType());
		return params;
	}

	public SellerCatsListAddResponse sellerCatsListAdd(SellerCatsListAddRequest req,
			String sessionId) throws TaobaoApiException {
		return sellercatsListAdd(req, sessionId);
	}
	
	public SellerCatsListAddResponse sellercatsListAdd(SellerCatsListAddRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareSellerCatsListAdd(req);
		return sendSellerCatsListAdd(params, req, sessionId);
	}
	
	public Map<String, Object> prepareSellercatsListAdd(
			SellerCatsListAddRequest sellerCatInsertRequest) {
		return prepareSellerCatsListAdd(sellerCatInsertRequest);
	}

	public Map<String, Object> prepareSellerCatsListAdd(
			SellerCatsListAddRequest sellerCatInsertRequest) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.NAME, sellerCatInsertRequest.getName());
		params.put(ApiConstants.PARENT_CID, sellerCatInsertRequest.getParentCid());
		params.put(ApiConstants.PICT_URL, sellerCatInsertRequest.getPictUrl());
		params.put(ApiConstants.SORT_ORDER, sellerCatInsertRequest.getSortOrder());
		return params;
	}

	protected SellerCatsListAddResponse sendSellerCatsListAdd(Map<String, Object> params,
			SellerCatsListAddRequest req, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOP_SELLERCAT_INSERT.getMethod(), sessionId,
						params));
		SellerCatsListAddResponse sellerCatInsertResponse = new SellerCatsListAddResponse(response);
		// 平台访问成功
		if (sellerCatInsertResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(sellerCatInsertResponse.getBody());
				parseError(sellerCatInsertResponse, json);
				// 淘宝访问成功
				if (sellerCatInsertResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SELLER_CATS)) {
						JSONArray catjs = rsp.getJSONArray(ApiConstants.SELLER_CATS);
						JSONObject catj = catjs.getJSONObject(0);
						SellerCat cat = new SellerCat();
						try {
							cat
									.setCreated(DateUtil.strToDate(catj
											.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						cat.setCid(catj.getString(ApiConstants.CID));
						sellerCatInsertResponse.setSellerCat(cat);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return sellerCatInsertResponse;
	}

	public SellerCatsListUpdateResponse sellercatsListUpdate(SellerCatsListUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		return sellerCatsListUpdate(req, sessionId);
	}
	
	public SellerCatsListUpdateResponse sellerCatsListUpdate(SellerCatsListUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareSellerCatsListUpdate(req);
		return sendSellerCatsListUpdate(params, req, sessionId);
	}
	
	public Map<String, Object> prepareSellercatsListUpdate(SellerCatsListUpdateRequest req) {
		return prepareSellerCatsListUpdate(req);
	}

	public Map<String, Object> prepareSellerCatsListUpdate(SellerCatsListUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.NAME, req.getName());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.PICT_URL, req.getPictUrl());
		params.put(ApiConstants.SORT_ORDER, req.getSortOrder());
		return params;
	}

	protected SellerCatsListUpdateResponse sendSellerCatsListUpdate(Map<String, Object> params,
			SellerCatsListUpdateRequest req, String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHOP_SELLERCAT_UPDATE.getMethod(), sessionId,
						params));
		SellerCatsListUpdateResponse sellerCatUpdateResponse = new SellerCatsListUpdateResponse(
				response);
		// 平台访问成功
		if (sellerCatUpdateResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(sellerCatUpdateResponse.getBody());
				parseError(sellerCatUpdateResponse, json);
				// 淘宝访问成功
				if (sellerCatUpdateResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SELLER_CATS)) {
						JSONArray catjs = rsp.getJSONArray(ApiConstants.SELLER_CATS);
						JSONObject catj = catjs.getJSONObject(0);
						SellerCat cat = new SellerCat();
						try {
							cat.setModified(DateUtil.strToDate(catj
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						cat.setCid(catj.getString(ApiConstants.CID));
						sellerCatUpdateResponse.setSellerCat(cat);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return sellerCatUpdateResponse;
	}

	public ShippingAddressesGetResponse shippingAddressesGet(ShippingAddressesGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareShippingAddressesGet(req);
		return sendShippingAddressesGet(sessionId, params);
	}

	public Map<String, Object> prepareShippingAddressesGet(ShippingAddressesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FIELDS, req.getFields());
		return params;
	}

	protected ShippingAddressesGetResponse sendShippingAddressesGet(String sessionId,
			Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SHIPPING_ADDRESSES.getMethod(), sessionId,
						params));
		ShippingAddressesGetResponse sagr = new ShippingAddressesGetResponse(response);
		if (sagr.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(sagr.getBody());
				parseError(sagr, json);
				if (sagr.isSuccess()) {
					// 处理返回结果
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SHIPPING_ADDRESS)) {
						JSONArray shippingAddressList = rsp
								.getJSONArray(ApiConstants.SHIPPING_ADDRESS);
						sagr.setShippingAddressList(TaobaoUserJSONConvert
								.convertJsonArrayToShippingAddressList(shippingAddressList));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return sagr;
	}

	public ProductsGetResponse productsGet(ProductsGetRequest req) throws TaobaoApiException {
		Map<String, Object> params = prepareProductsGet(req);
		return sendProductsGet(params);
	}

	public Map<String, Object> prepareProductsGet(ProductsGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.NICK, req.getNick());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		return params;
	}

	public ProductsGetResponse sendProductsGet(Map<String, Object> params)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCTS_GET.getMethod(), params));
		ProductsGetResponse productsGetResponse = new ProductsGetResponse(response);
		if (productsGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(productsGetResponse.getBody());
				parseError(productsGetResponse, json);
				if (productsGetResponse.isSuccess()) {
					// 处理返回结果
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.PRODUCTS)) {
						JSONArray products = rsp.getJSONArray(ProductSearchRequest.PRODUCTS);
						productsGetResponse.setProductList(TaobaoProductJSONConvert
								.convertJsonArrayToProductList(products));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productsGetResponse;
	}

	public Map<String, Object> prepareRefundGet(RefundGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.REFUND_ID, req.getRefundId());
		params.put(ApiConstants.BIZ_ORDER_ID, req.getBizOrderId());
		return params;
	}

	protected RefundGetResponse sendRefundGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.REFUND_GET.getMethod(), sessionId, params));
		RefundGetResponse refundGetResponse = new RefundGetResponse(response);
		// 平台成功访问
		if (refundGetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(refundGetResponse.getBody());
				parseError(refundGetResponse, json);
				// 访问淘宝成功
				if (refundGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.REFUNDS)) {
						JSONArray refunds = rsp.getJSONArray(ApiConstants.REFUNDS);
						JSONObject refund = refunds.getJSONObject(0);
						refundGetResponse.setRefund(TaobaoRefundJSONConvert
								.convertJsonToRefund(refund));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return refundGetResponse;
	}

	public RefundGetResponse refundGet(RefundGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareRefundGet(req);
		return sendRefundGet(params, sessionId);
	}

	public RefundMessagesGetResponse refundMessagesGet(RefundMessagesGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareRefundMessagesGet(req);
		return sendRefundMessagesGet(params, sessionId);
	}

	public Map<String, Object> prepareRefundMessagesGet(RefundMessagesGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.REFUND_ID, req.getRefundId());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		return params;
	}

	protected RefundMessagesGetResponse sendRefundMessagesGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.REFUND_MESSAGES_GET.getMethod(), sessionId,
						params));
		RefundMessagesGetResponse refundMessagesGetResponse = new RefundMessagesGetResponse(
				response);
		// 平台访问成功
		if (refundMessagesGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(refundMessagesGetResponse.getBody());
				parseError(refundMessagesGetResponse, json);
				// 淘宝访问成功
				if (refundMessagesGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.REFUND_MESSAGES)) {
						JSONArray refundMessages = rsp.getJSONArray(ApiConstants.REFUND_MESSAGES);
						refundMessagesGetResponse
								.setRefundMessageList(TaobaoRefundMessageJSONConvert
										.convertJsonArrayToRefundMessageList(refundMessages));
					}
					setTotalResults(rsp, refundMessagesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return refundMessagesGetResponse;
	}

	public RefundMessageAddResponse refundMessageAdd(RefundMessageAddRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareRefundMessageAdd(req);
		return sendRefundMessageAdd(params, req, sessionId);
	}

	public Map<String, Object> prepareRefundMessageAdd(RefundMessageAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.CONTENT, req.getContent());
		params.put(ApiConstants.OWNER_NICK, req.getOwnerNick());
		params.put(ApiConstants.REFUND_ID, req.getRefundId());
		return params;
	}

	protected RefundMessageAddResponse sendRefundMessageAdd(Map<String, Object> params,
			RefundMessageAddRequest req, String sessionId) throws TaobaoApiException {
		RefundMessageAddResponse refundMessageAddResponse = getRefundMessageAddResponse(req,
				sessionId, params);
		// 平台成功访问
		if (refundMessageAddResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(refundMessageAddResponse.getBody());
				parseError(refundMessageAddResponse, json);
				// 访问淘宝成功
				if (refundMessageAddResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.REFUND_MESSAGES)) {
						JSONArray refundMessages = rsp.getJSONArray(ApiConstants.REFUND_MESSAGES);
						JSONObject refundMessage = refundMessages.getJSONObject(0);
						RefundMessage addedRefundMessage = new RefundMessage();
						try {
							addedRefundMessage.setCreated(DateUtil.strToDate(refundMessage
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						addedRefundMessage.setMessageId(refundMessage
								.getString(ApiConstants.MESSAGE_ID));
						refundMessageAddResponse.setRefundMessage(addedRefundMessage);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return refundMessageAddResponse;
	}

	private RefundMessageAddResponse getRefundMessageAddResponse(RefundMessageAddRequest req,
			String sessionId, Map<String, Object> paramsMap) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加留言凭证是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.REFUND_MESSAGE_ADD.getMethod(), sessionId,
							paramsMap), req.getImage());
		} else {
			rsp = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.REFUND_MESSAGE_ADD.getMethod(), sessionId,
							paramsMap));
		}
		return new RefundMessageAddResponse(rsp);
	}

	public Map<String, Object> prepareRefundsApplyGet(RefundsApplyGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.SELLER_NICK, req.getSellerNick());
		return params;
	}

	protected RefundsApplyGetResponse sendRefundsApplyGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch()
				.fetch(
						this.getServiceUrl(),
						getTemplateRequest(TaobaoApiMethod.REFUNDS_APPLY_GET.getMethod(),
								sessionId, params));
		RefundsApplyGetResponse refundsApplyGetResponse = new RefundsApplyGetResponse(response);
		// 平台访问成功
		if (refundsApplyGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(refundsApplyGetResponse.getBody());
				parseError(refundsApplyGetResponse, json);
				// 淘宝访问成功
				if (refundsApplyGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.REFUNDS)) {
						JSONArray refunds = rsp.getJSONArray(ApiConstants.REFUNDS);
						refundsApplyGetResponse.setRefundList(TaobaoRefundJSONConvert
								.convertJsonArrayToRefundList(refunds));
					}
					setTotalResults(rsp, refundsApplyGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return refundsApplyGetResponse;
	}

	public RefundsApplyGetResponse refundsApplyGet(RefundsApplyGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareRefundsApplyGet(req);
		return sendRefundsApplyGet(params, sessionId);
	}

	public Map<String, Object> prepareRefundsReceiveGet(RefundsReceiveGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.PAGE_NO, req.getPageNo());
		params.put(ApiConstants.PAGE_SIZE, req.getPageSize());
		params.put(ApiConstants.STATUS, req.getStatus());
		params.put(ApiConstants.TYPE, req.getType());
		params.put(ApiConstants.BUYER_NICK, req.getBuyerNick());
		params.put(ApiConstants.START_MODIFIED, req.getStartModified());
		params.put(ApiConstants.END_MODIFIED, req.getEndModified());
		return params;
	}

	protected RefundsReceiveGetResponse sendRefundsReceiveGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.REFUNDS_RECIEVE_GET.getMethod(), sessionId,
						params));
		RefundsReceiveGetResponse refundsRecieveGetResponse = new RefundsReceiveGetResponse(
				response);
		// 平台访问成功
		if (refundsRecieveGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(refundsRecieveGetResponse.getBody());
				parseError(refundsRecieveGetResponse, json);
				// 淘宝访问成功
				if (refundsRecieveGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.REFUNDS)) {
						JSONArray refunds = rsp.getJSONArray(ApiConstants.REFUNDS);
						refundsRecieveGetResponse.setRefundList(TaobaoRefundJSONConvert
								.convertJsonArrayToRefundList(refunds));
					}
					setTotalResults(rsp, refundsRecieveGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return refundsRecieveGetResponse;
	}

	public RefundsReceiveGetResponse refundsReceiveGet(RefundsReceiveGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareRefundsReceiveGet(req);
		return sendRefundsReceiveGet(params, sessionId);
	}

	public RefundsRecieveGetResponse refundsRecieveGet(RefundsRecieveGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new RefundsRecieveGetResponse(refundsReceiveGet(req, sessionId));
	}

	public SuitesGetResponse suitesGet(SuiteGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareSuitesGet(req);
		return sendSuitesGet(params, sessionId);
	}

	public Map<String, Object> prepareSuitesGet(SuiteGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.SERVICE_CODE, req.getServiceCode());
		return params;
	}

	protected SuitesGetResponse sendSuitesGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SUITES_GET.getMethod(), sessionId, params));
		SuitesGetResponse suitesGetResponse = new SuitesGetResponse(response);

		// 平台成功访问
		if (suitesGetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(suitesGetResponse.getBody());
				parseError(suitesGetResponse, json);
				// 访问淘宝成功
				if (suitesGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SUITES)) {
						JSONArray suites = rsp.getJSONArray(ApiConstants.SUITES);
						suitesGetResponse.setSuites(TaobaoSuiteJSONConvert
								.convertJsonArrayToSuiteList(suites));
					}
					setTotalResults(rsp, suitesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return suitesGetResponse;
	}

	public VasSuitesgGetResponse vasSuitesGet(VasSuitesgGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareVasSuitesGet(req);
		return sendVasSuitesGet(params, sessionId);
	}

	public Map<String, Object> prepareVasSuitesGet(VasSuitesgGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.SERVICE_CODE, req.getServiceCode());
		return params;
	}

	protected VasSuitesgGetResponse sendVasSuitesGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.VAS_SUITES_GET.getMethod(), sessionId, params));
		VasSuitesgGetResponse suitesGetResponse = new VasSuitesgGetResponse(response);
		// 平台成功访问
		if (suitesGetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(suitesGetResponse.getBody());
				parseError(suitesGetResponse, json);
				// 访问淘宝成功
				if (suitesGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SUITES)) {
						JSONArray suites = rsp.getJSONArray(ApiConstants.SUITES);
						suitesGetResponse.setSuites(TaobaoSuiteJSONConvert
								.convertJsonArrayToSuiteList(suites));
					}
					setTotalResults(rsp, suitesGetResponse);
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return suitesGetResponse;
	}

	public TradeCloseResponse tradeClose(TradeCloseRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeClose(req);
		return sendTradeClose(params, sessionId);
	}

	protected TradeCloseResponse sendTradeClose(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_CLOSE.getMethod(), sessionId, params));
		TradeCloseResponse tradeCloseResponse = new TradeCloseResponse(response);

		// 平台成功访问
		if (tradeCloseResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(tradeCloseResponse.getBody());
				parseError(tradeCloseResponse, json);
				// 访问淘宝成功
				if (tradeCloseResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						JSONObject trade = trades.getJSONObject(0);
						Trade closeTrade = new Trade();
						try {
							closeTrade.setModified(DateUtil.strToDate(trade
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						closeTrade.setTid(trade.getString(ApiConstants.TID));
						tradeCloseResponse.setTrade(closeTrade);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeCloseResponse;
	}

	public Map<String, Object> prepareTradeClose(TradeCloseRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.CLOSE_REASON, req.getCloseReason());
		return params;
	}

	public Map<String, Object> prepareProductAdd(ProductAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductAddRequest.CID, req.getCid());
		params.put(ProductAddRequest.BINDS, req.getBinds());
		params.put(ProductAddRequest.DESC, req.getDesc());
		params.put(ProductAddRequest.NAME, req.getName());
		params.put(ProductAddRequest.PRICE, req.getPrice());
		params.put(ProductAddRequest.PROPS, req.getProps());
		params.put(ProductAddRequest.SALEPROPS, req.getSaleProps());
		params.put(ProductAddRequest.OUTER_ID, req.getOuterId());
		params.put(ProductAddRequest.CUSETOMER_PROPS, req.getCustomerProps());
		return params;
	}

	private ProductAddResponse getProductAddResponse(ProductAddRequest req, String sessionId,
			Map<String, Object> paramsMap) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加商品是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_ADD.getMethod(), sessionId,
							paramsMap), req.getImage());
		} else {
			rsp = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_ADD.getMethod(), sessionId,
							paramsMap));
		}
		return new ProductAddResponse(rsp);
	}

	public ProductAddResponse sendProductAdd(Map<String, Object> params, ProductAddRequest req,
			String sessionId) throws TaobaoApiException {
		ProductAddResponse productAddResponse = getProductAddResponse(req, sessionId, params);
		// 平台成功访问
		if (productAddResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(productAddResponse.getBody());
				parseError(productAddResponse, json);
				// 访问淘宝成功
				if (productAddResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductAddRequest.PRODUCT)) {
						JSONArray products = rsp.getJSONArray(ProductAddRequest.PRODUCT);
						JSONObject product = products.getJSONObject(0);
						Product addedProduct = new Product();
						try {
							addedProduct.setCreated(DateUtil.strToDate(product
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						addedProduct.setProductId(product.getString(ApiConstants.PRODUCT_ID));
						productAddResponse.setProduct(addedProduct);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productAddResponse;
	}

	public ProductAddResponse productAdd(ProductAddRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareProductAdd(req);
		return sendProductAdd(params, req, sessionId);
	}

	public Map<String, Object> prepareProductUpdate(ProductUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductUpdateRequest.PRODUCTID, req.getProductId());
		params.put(ProductUpdateRequest.BINDS, req.getBinds());
		params.put(ProductUpdateRequest.DESC, req.getDesc());
		params.put(ProductUpdateRequest.NAME, req.getName());
		params.put(ProductUpdateRequest.PRICE, req.getPrice());
		params.put(ProductUpdateRequest.SALEPROPS, req.getSaleProps());
		// params.put(ProductUpdateRequest.TSC, productUpdateRequest.getTsc());
		params.put(ProductUpdateRequest.OUTER_ID, req.getOuterId());
		params.put(ProductAddRequest.CUSETOMER_PROPS, req.getCustomerProps());
		return params;
	}

	public ProductUpdateResponse sendProductUpdate(Map<String, Object> params,
			ProductUpdateRequest req, String sessionId) throws TaobaoApiException {
		ProductUpdateResponse productUpdateResponse = getProductUpdateResponse(req, sessionId,
				params);
		// 平台成功访问
		if (productUpdateResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(productUpdateResponse.getBody());
				parseError(productUpdateResponse, json);
				// 访问淘宝成功
				if (productUpdateResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductAddRequest.PRODUCT)) {
						JSONArray products = rsp.getJSONArray(ProductAddRequest.PRODUCT);
						JSONObject product = products.getJSONObject(0);
						Product addedProduct = new Product();
						try {
							addedProduct.setModified(DateUtil.strToDate(product
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						addedProduct.setProductId(product.getString(ApiConstants.PRODUCT_ID));
						productUpdateResponse.setProduct(addedProduct);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productUpdateResponse;
	}

	private ProductUpdateResponse getProductUpdateResponse(ProductUpdateRequest req,
			String sessionId, Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加SPU是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_UPDATE.getMethod(), sessionId,
							params), req.getImage());
		} else {
			rsp = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_UPDATE.getMethod(), sessionId,
							params));
		}
		return new ProductUpdateResponse(rsp);
	}

	public ProductUpdateResponse productUpdate(ProductUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareProductUpdate(req);
		return sendProductUpdate(params, req, sessionId);
	}

	protected TradeGetResponse sendTradeFullInfoGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_FULLINFO_GET.getMethod(), sessionId,
						params));
		TradeGetResponse tradeGetResponse = new TradeGetResponse(response);
		// 平台成功访问
		if (tradeGetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(tradeGetResponse.getBody());
				parseError(tradeGetResponse, json);
				// 访问淘宝成功
				if (tradeGetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						JSONObject trade = trades.getJSONObject(0);
						tradeGetResponse.setTrade(TaobaoTradeJSONConvert.convertJsonToTrade(trade));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeGetResponse;
	}

	public TradeGetResponse tradeFullInfoGet(TradeGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeGet(req);
		return sendTradeFullInfoGet(params, sessionId);
	}

	public TradeGetResponse tradeFullinfoGet(TradeGetRequest req, String sessionId)
			throws TaobaoApiException {
		return tradeFullInfoGet(req, sessionId);
	}

	public Map<String, Object> prepareTradeFullinfoGet(TradeGetRequest req) {
		return prepareTradeGet(req);
	}

	public Map<String, Object> prepareTradeMemoAdd(TradeMemoAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.MEMO, req.getMemo());
		params.put(ApiConstants.TID, req.getTid());
		return params;
	}

	protected TradeMemoAddResponse sendTradeMemoAdd(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_MEMO_ADD.getMethod(), sessionId, params));
		TradeMemoAddResponse tradeMemoAddResponse = new TradeMemoAddResponse(response);
		// 平台成功访问
		if (tradeMemoAddResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(tradeMemoAddResponse.getBody());
				parseError(tradeMemoAddResponse, json);
				// 访问淘宝成功
				if (tradeMemoAddResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						JSONObject trade = trades.getJSONObject(0);
						Trade newTradeMemo = new Trade();
						if (trade.has(ApiConstants.CREATED)) {
							try {
								newTradeMemo.setCreated(DateUtil.strToDate(trade
										.getString(ApiConstants.CREATED)));
							} catch (ParseException e) {
								throw new TaobaoApiException();
							}
						}
						if (trade.has(ApiConstants.TID)) {
							newTradeMemo.setTid(trade.getString(ApiConstants.TID));
						}
						tradeMemoAddResponse.setTrade(newTradeMemo);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeMemoAddResponse;
	}

	public TradeMemoAddResponse tradeMemoAdd(TradeMemoAddRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeMemoAdd(req);
		return sendTradeMemoAdd(params, sessionId);
	}

	public Map<String, Object> prepareTradeMemoUpdate(TradeMemoUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.MEMO, req.getMemo());
		params.put(ApiConstants.TID, req.getTid());
		return params;
	}

	protected TradeMemoUpdateResponse sendTradeMemoUpdate(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch()
				.fetch(
						this.getServiceUrl(),
						getTemplateRequest(TaobaoApiMethod.TRADE_MEMO_UPDATE.getMethod(),
								sessionId, params));
		TradeMemoUpdateResponse tradeMemoUpdateResponse = new TradeMemoUpdateResponse(response);
		// 平台成功访问
		if (tradeMemoUpdateResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(tradeMemoUpdateResponse.getBody());
				parseError(tradeMemoUpdateResponse, json);
				// 访问淘宝成功
				if (tradeMemoUpdateResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.TRADES)) {
						JSONArray trades = rsp.getJSONArray(ApiConstants.TRADES);
						JSONObject trade = trades.getJSONObject(0);
						Trade newTradeMemo = new Trade();
						if (trade.has(ApiConstants.MODIFIED)) {
							try {
								newTradeMemo.setModified(DateUtil.strToDate(trade
										.getString(ApiConstants.MODIFIED)));
							} catch (ParseException e) {
								throw new TaobaoApiException();
							}
						}
						if (trade.has(ApiConstants.TID)) {
							newTradeMemo.setTid(trade.getString(ApiConstants.TID));
						}
						tradeMemoUpdateResponse.setTrade(newTradeMemo);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return tradeMemoUpdateResponse;
	}

	public TradeMemoUpdateResponse tradeMemoUpdate(TradeMemoUpdateRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeMemoUpdate(req);
		return sendTradeMemoUpdate(params, sessionId);
	}

	public FullitemsGetResponse fullitems(FullitemsGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new FullitemsGetResponse(this.itemsCustomGet(req, sessionId));
	}

	public ItemsOutGetResponse itemsOutGet(ItemsOutGetRequest req, String sessionId)
			throws TaobaoApiException {
		return new ItemsOutGetResponse(this.itemsCustomGet(req, sessionId));
	}

	public ItemsCustomGetResponse itemsCustomGet(ItemsCustomGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemsCustomGet(req);
		return sendItemsCustomGet(params, sessionId);
	}

	public ItemsCustomGetResponse sendItemsCustomGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
						getTemplateRequest(TaobaoApiMethod.ITEMS_CUSTOM_GET.getMethod(), sessionId, params));
		ItemsCustomGetResponse itemsOutGetResponse = new ItemsCustomGetResponse(response);
		if (itemsOutGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(itemsOutGetResponse.getBody());
				parseError(itemsOutGetResponse, json);
				if (itemsOutGetResponse.isSuccess()) {
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						itemsOutGetResponse.setItem(TaobaoItemJSONConvert
								.convertJsonArrayToItemList(items));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemsOutGetResponse;
	}

	public Map<String, Object> prepareItemsCustomGet(ItemsCustomGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	public SellerAuthorizeGetResponse sellerAuthorizeGet(SellerAuthorizeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return new SellerAuthorizeGetResponse(itemCatsAuthorizeGet(req, sessionId));
	}

	public ItemCatsAuthorizeGetResponse itemCatsAuthorizeGet(ItemCatsAuthorizeGetRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemcatsAuthorizeGet(req);
		return sendItemCatsAuthorizeGet(params, sessionId);
	}

	public ItemCatsAuthorizeGetResponse itemcatsAuthorizeGet(ItemCatsAuthorizeGetRequest req,
			String sessionId) throws TaobaoApiException {
		return itemCatsAuthorizeGet(req, sessionId);
	}

	public Map<String, Object> prepareItemcatsAuthorizeGet(ItemCatsAuthorizeGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	protected ItemCatsAuthorizeGetResponse sendItemCatsAuthorizeGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse rsp = getFetch().fetch(getServiceUrl(),getTemplateRequest(
				TaobaoApiMethod.ITEMCATS_AUTHORIZE_GET.getMethod(), sessionId, params));

		ItemCatsAuthorizeGetResponse result = new ItemCatsAuthorizeGetResponse(rsp);
		SellerAuthorize sellerAuthorize = null;

		// 访问淘宝成功
		if (result.isSuccess()) {
			try {
				JSONObject jsonRoot = new JSONObject(result.getBody());
				parseError(result, jsonRoot); // 容错
				if (result.isSuccess()) { // 容错之后是否还成功
					jsonRoot = jsonRoot.getJSONObject(ApiConstants.RSP);

					// 授权信息
					if (jsonRoot.has(ApiConstants.SELLER_AUTHORIZE)) {
						JSONObject auth = jsonRoot.getJSONArray(ApiConstants.SELLER_AUTHORIZE)
								.getJSONObject(0);
						sellerAuthorize = new SellerAuthorize();

						if (auth.has(ApiConstants.ITEM_CATS)) {// 类目
							JSONArray itemCats = auth.getJSONArray(ApiConstants.ITEM_CATS);
							sellerAuthorize.setItemCats(TaobaoItemJSONConvert
									.convertJsonArrayToItemCatList(itemCats));
						}

						if (auth.has(ApiConstants.BRANDS)) {// 品牌
							JSONArray brands = auth.getJSONArray(ApiConstants.BRANDS);
							sellerAuthorize.setBrands(TaobaoBrandJSONConvert
									.convertJsonArray2BrandList(brands));
						}

						setTotalResults(auth, result);
					}
				}
			} catch (Exception e) {
				throw new TaobaoApiException(e);
			}
		}

		result.setAuthorizes(sellerAuthorize);
		return result;
	}

	public ProductImgUploadResponse productImgUpload(ProductImgUploadRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareProductImgUpload(req);
		return sendProductImgUpload(req, sessionId, params);
	}

	public Map<String, Object> prepareProductImgUpload(ProductImgUploadRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductImgUploadRequest.ID, req.getId());
		params.put(ProductImgUploadRequest.PICID, req.getPicId());
		params.put(ProductImgUploadRequest.PRODUCTID, req.getProductId());
		params.put(ProductImgUploadRequest.MAJOR, req.getMajor());
		params.put(ProductImgUploadRequest.POSITION, req.getPosition());
		return params;
	}

	public ProductImgUploadResponse sendProductImgUpload(ProductImgUploadRequest req,
			String sessionId, Map<String, Object> params) throws TaobaoApiException {
		ProductImgUploadResponse productImgUploadResponse = getProductImgUploadResponse(req,
				sessionId, params);
		// 平台成功访问
		if (productImgUploadResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(productImgUploadResponse.getBody());
				parseError(productImgUploadResponse, json);
				// 访问淘宝成功
				if (productImgUploadResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductImgUploadRequest.PRODUCTIMG)) {
						JSONArray products = rsp.getJSONArray(ProductImgUploadRequest.PRODUCTIMG);
						JSONObject product = products.getJSONObject(0);
						ProductImg addedProduct = new ProductImg();
						try {
							addedProduct.setCreated(DateUtil.strToDate(product
									.getString(ApiConstants.CREATED)));
							addedProduct.setModified(DateUtil.strToDate(product
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						addedProduct.setPicId(product.getString(ApiConstants.PICID));
						addedProduct.setUrl(product.getString(ApiConstants.URL));
						productImgUploadResponse.setProductImg(addedProduct);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productImgUploadResponse;
	}

	private ProductImgUploadResponse getProductImgUploadResponse(ProductImgUploadRequest req,
			String sessionId, Map<String, Object> paramsMap) throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加商品是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_IMG_UPLOAD.getMethod(), sessionId,
							paramsMap), req.getImage());
		} else {
			rsp = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_IMG_UPLOAD.getMethod(), sessionId,
							paramsMap));
		}
		return new ProductImgUploadResponse(rsp);
	}

	public ProductPropImgUploadResponse productPropimgUpload(ProductPropImgUploadRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareProductPropimgUpload(req);
		return sendProductPropimgUpload(req, sessionId, params);
	}
	//自动映射重命名
	public ProductPropImgUploadResponse productPropImgUpload(ProductPropImgUploadRequest req,
			String sessionId) throws TaobaoApiException {
		return productPropimgUpload(req, sessionId);
	}

	public Map<String, Object> prepareProductPropimgUpload(ProductPropImgUploadRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductPropImgUploadRequest.ID, req.getId());
		params.put(ProductPropImgUploadRequest.PICID, req.getPicId());
		params.put(ProductPropImgUploadRequest.PRODUCTID, req.getProductId());
		params.put(ProductPropImgUploadRequest.PROPS, req.getProps());
		params.put(ProductPropImgUploadRequest.POSITION, req.getPosition());
		return params;
	}

	public ProductPropImgUploadResponse sendProductPropimgUpload(ProductPropImgUploadRequest req,
			String sessionId, Map<String, Object> params) throws TaobaoApiException {
		ProductPropImgUploadResponse productPropImgUploadResponse = getProductPropImgUploadResponse(
				req, sessionId, params);
		// 平台成功访问
		if (productPropImgUploadResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(productPropImgUploadResponse.getBody());
				parseError(productPropImgUploadResponse, json);
				// 访问淘宝成功
				if (productPropImgUploadResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductPropImgUploadRequest.PRODUCTPROPIMG)) {
						JSONArray products = rsp
								.getJSONArray(ProductPropImgUploadRequest.PRODUCTPROPIMG);
						JSONObject product = products.getJSONObject(0);
						ProductPropImg addedProduct = new ProductPropImg();
						try {
							addedProduct.setCreated(DateUtil.strToDate(product
									.getString(ApiConstants.CREATED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						try {
							addedProduct.setModified(DateUtil.strToDate(product
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
						addedProduct.setPicId(product.getString(ApiConstants.PICID));
						addedProduct.setUrl(product.getString(ApiConstants.URL));
						productPropImgUploadResponse.setProductPropImg(addedProduct);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productPropImgUploadResponse;
	}

	private ProductPropImgUploadResponse getProductPropImgUploadResponse(
			ProductPropImgUploadRequest req, String sessionId, Map<String, Object> paramsMap)
			throws TaobaoApiException {
		TaobaoResponse rsp = null;
		// 根据添加商品是否有图片选择不同的请求方法
		if (req.getImage() != null) {
			rsp = this.getFetch().fetchWithFile(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_PROPIMG_UPLOAD.getMethod(),
							sessionId, paramsMap), req.getImage());
		} else {
			rsp = this.getFetch().fetch(
					this.getServiceUrl(),
					getTemplateRequest(TaobaoApiMethod.PRODUCT_PROPIMG_UPLOAD.getMethod(),
							sessionId, paramsMap));
		}
		return new ProductPropImgUploadResponse(rsp);
	}

	public ProductImgDeleteResponse productImgDelete(ProductImgDeleteRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareProductImgDelete(req);
		return sendProductImgDelete(params, sessionId);
	}

	public Map<String, Object> prepareProductImgDelete(ProductImgDeleteRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductImgDeleteRequest.ID, req.getId());
		params.put(ProductImgDeleteRequest.PICID, req.getPicId());
		params.put(ProductImgDeleteRequest.PRODUCTID, req.getProductId());
		return params;
	}

	public ProductImgDeleteResponse sendProductImgDelete(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCT_IMG_DELETE.getMethod(), sessionId,
						params));
		ProductImgDeleteResponse productImgDeleteResponse = new

		ProductImgDeleteResponse(response);
		// 平台访问成功
		if (productImgDeleteResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(productImgDeleteResponse.getBody());
				parseError(productImgDeleteResponse, json);
				// 访问淘宝成功
				if (productImgDeleteResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductImgUploadRequest.PRODUCTIMG)) {
						JSONArray products = rsp.getJSONArray

						(ProductImgUploadRequest.PRODUCTIMG);
						JSONObject product = products.getJSONObject

						(0);
						productImgDeleteResponse.setProductImg

						(TaobaoProductJSONConvert.convertJsonToProductImg(product));
					}

				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productImgDeleteResponse;
	}

	public ProductPropImgDeleteResponse productPropimgDelete(ProductPropImgDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareProductPropimgDelete(req);
		return sendProductPropimgDelete(params, sessionId);
	}
	//自动映射重命名
	public ProductPropImgDeleteResponse productPropImgDelete(ProductPropImgDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		return productPropimgDelete(req, sessionId);
	}

	public Map<String, Object> prepareProductPropimgDelete(ProductPropImgDeleteRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductPropImgDeleteRequest.ID, req.getId());
		params.put(ProductPropImgDeleteRequest.PICID, req.getPicId());
		params.put(ProductPropImgDeleteRequest.PRODUCTID, req.getProductId());
		return params;
	}

	public ProductPropImgDeleteResponse sendProductPropimgDelete(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCT_PROPIMG_DELETE.getMethod(), sessionId,
						params));
		ProductPropImgDeleteResponse productPropImgDeleteResponse = new ProductPropImgDeleteResponse(
				response);
		// 平台访问成功
		if (productPropImgDeleteResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(productPropImgDeleteResponse.getBody());
				parseError(productPropImgDeleteResponse, json);
				// 访问淘宝成功
				if (productPropImgDeleteResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ProductPropImgUploadRequest.PRODUCTPROPIMG)) {
						JSONArray products = rsp
								.getJSONArray(ProductPropImgUploadRequest.PRODUCTPROPIMG);
						JSONObject product = products.getJSONObject(0);
						productPropImgDeleteResponse.setProductPropImg(TaobaoProductJSONConvert
								.convertJsonToProductPropImg(product));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return productPropImgDeleteResponse;
	}

	public VasProductGetResponse vasProductGet(VasProductGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareVasProductGet(req);
		return sendVasProductGet(params, sessionId);
	}

	public Map<String, Object> prepareVasProductGet(VasProductGetRequest vasRequest) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.SUB_PROD_ID, vasRequest.getSubProdId());
		return params;
	}

	protected VasProductGetResponse sendVasProductGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SUB_PROD_GET.getMethod(), sessionId, params));
		VasProductGetResponse vasResponse = new VasProductGetResponse(response);

		if (vasResponse.isSuccess()) {
			try {
				JSONObject jsonBody = new JSONObject(vasResponse.getBody());
				parseError(vasResponse, jsonBody);
				if (vasResponse.isSuccess()) {
					JSONObject rsp = jsonBody.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.VAS_PRODUCT)) {
						JSONObject jsonVas = rsp.getJSONObject(ApiConstants.VAS_PRODUCT);
						VasProduct vasProduct = TaobaoVasJSONConvert
								.convertJsonToVasProduct(jsonVas);
						vasResponse.setVasProduct(vasProduct);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}

		return vasResponse;
	}

	public TimegetResponse timeget() throws TaobaoApiException, ParseException {
		Map<String, Object> params = new HashMap<String, Object>();
		return sendTimeget(params);
	}

	protected TimegetResponse sendTimeget(Map<String, Object> params) throws TaobaoApiException,
			ParseException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TIME_MAIN.getMethod(), params));
		TimegetResponse timegetResponse = new TimegetResponse(response);
		// 平台成功访问
		if (timegetResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(timegetResponse.getBody());
				parseError(timegetResponse, json);
				// 访问淘宝成功
				if (timegetResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.MAIN_TIME)) {
						JSONArray tbtime = rsp.getJSONArray(ApiConstants.MAIN_TIME);
						JSONObject times = tbtime.getJSONObject(0);
						try {
							timegetResponse.setMaintime(DateUtil.strToDate((times
									.getString(ApiConstants.REMAIN_TIME))));
						} catch (JSONException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}

		return timegetResponse;
	}

	public GuarantyFundsGetResponse guarantyfundsGet(GuarantyFundsGetRequest req, String sessionId)
		throws TaobaoApiException {
		return guarantyFundsGet(req, sessionId);
	}
	
	public GuarantyFundsGetResponse guarantyFundsGet(GuarantyFundsGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareGuarantyFundsGet(req);
		return sendGuarantyFundsGet(params, sessionId);
	}
	
	public Map<String, Object> prepareGuarantyfundsGet(GuarantyFundsGetRequest req) {
		return prepareGuarantyFundsGet(req);
	}

	public Map<String, Object> prepareGuarantyFundsGet(GuarantyFundsGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.BEGIN_DATE, req.getBeginDate());
		params.put(ApiConstants.END_DATE, req.getEndDate());
		return params;
	}

	protected GuarantyFundsGetResponse sendGuarantyFundsGet(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = getFetch().fetch(
				this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.GUARANTY_FUNDS_GET.getMethod(), sessionId,
						params));
		GuarantyFundsGetResponse gfsResponse = new GuarantyFundsGetResponse(response);

		if (gfsResponse.isSuccess()) {
			try {
				JSONObject jsonBody = new JSONObject(gfsResponse.getBody());
				parseError(gfsResponse, jsonBody);
				if (gfsResponse.isSuccess()) {
					JSONObject rsp = jsonBody.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.GUARANTY_FUNDS)) {
						JSONArray jsonRoot = rsp.getJSONArray(ApiConstants.GUARANTY_FUNDS);
						List<GuarantyFund> guarantyFunds = TaobaoSellerJSONConvert
								.convertJsonArrayToGuarantyFundList(jsonRoot);
						gfsResponse.setGuarantyFunds(guarantyFunds);
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}

		return gfsResponse;
	}

	// ============================比比价API========================================

	public ProductStatSearchResponse productstatSearch(ProductStatSearchRequest request) throws TaobaoApiException {
		return productStatSearch(request);
	}
	
	public ProductStatSearchResponse productStatSearch(ProductStatSearchRequest request)
			throws TaobaoApiException {
		Map<String, Object> params = prepareProductstatSearch(request);
		return sendProductStatSearch(params);
	}
	
	public ProductStatSearchResponse sendProductStatSearch(Map<String, Object> params) throws TaobaoApiException {
		TaobaoResponse tr = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.PRODUCTSTAT_SEARCH.getMethod(), params));
		ProductStatSearchResponse response = new ProductStatSearchResponse(tr);
		try {
			JSONObject json = new JSONObject(response.getBody());
			response.fillResponse(json);
		} catch (JSONException e) {
			throw new TaobaoApiException(e);
		}
		return response;
	}
	
	public Map<String, Object> prepareProductstatSearch(ProductStatSearchRequest request) throws TaobaoApiException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductStatSearchRequest.QUOTE, request.getQuote());
		params.put(ProductStatSearchRequest.CID, request.getCatId());
		params.put(ProductStatSearchRequest.ORDER_BY, request.getOrderBy());
		params.put(ProductStatSearchRequest.PAGE_NO, request.getPageNumber());
		params.put(ProductStatSearchRequest.PAGE_SIZE, request.getPageSize());
		params.put(ProductStatSearchRequest.PROP_PATH, request.getPropPath());
		return params;
	}
	
	public ProductStatGetResponse productstatGet(ProductStatGetRequest request) throws TaobaoApiException {
		return productStatGet(request);
	}
	
	public ProductStatGetResponse productStatGet(ProductStatGetRequest request) throws TaobaoApiException
	{
		Map<String, Object> params = prepareProductstatGet(request);
		return sendProductStatGet(params);
	}
	
	public Map<String, Object> prepareProductstatGet(ProductStatGetRequest request) throws TaobaoApiException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductStatGetRequest.PRODUCT_ID, request.getProductId());
		return params;
	}
	
	public ProductStatGetResponse sendProductStatGet(Map<String, Object> params) throws TaobaoApiException{
		TaobaoResponse tr = this.getFetch().fetch(this.getServiceUrl(), getTemplateRequest(TaobaoApiMethod.PRODUCTSTAT_GET.getMethod(), params));
		ProductStatGetResponse response = new ProductStatGetResponse(tr);
		try{
		    JSONObject json = new JSONObject(response.getBody());
		    response.fillResponse(json);
		}catch(JSONException e){
			throw new TaobaoApiException(e);
		}
		return response;
	}
	
	public ProductPriceStatGetResponse productpricestatGet(ProductPriceStatGetRequest request) throws TaobaoApiException{
		return productPriceStatGet(request);
	}
	
	public ProductPriceStatGetResponse productPriceStatGet(ProductPriceStatGetRequest request) throws TaobaoApiException
	{
		Map<String, Object> params = prepareProductpricestatGet(request);
		return sendProductPriceStatGet(params);
	}
	
	public Map<String, Object> prepareProductpricestatGet(ProductPriceStatGetRequest request) throws TaobaoApiException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ProductPriceStatGetRequest.PRODUCT_ID, request.getProductId());
		return params;
	}
	
	public ProductPriceStatGetResponse sendProductPriceStatGet(Map<String, Object> params) throws TaobaoApiException{
		TaobaoResponse tr = this.getFetch().fetch(this.getServiceUrl(), getTemplateRequest(TaobaoApiMethod.PRODUCT_PRICE_STAT_GET.getMethod(), params));
		ProductPriceStatGetResponse response = new ProductPriceStatGetResponse(tr);
		try {
			JSONObject json = new JSONObject(response.getBody());
			response.fillResponse(json);
		} catch (JSONException e) {
			throw new TaobaoApiException(e);
		}
		return response;
	}

	public SkusCustomGetResponse skusCustomGet(SkusCustomGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareSkusCustomGet(req);
		return sendSkusCustomGet(params, sessionId);
	}

	public Map<String, Object> prepareSkusCustomGet(SkusCustomGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.OUTER_ID, req.getOuterId());
		params.put(ApiConstants.FIELDS, req.getFields());
		return params;
	}

	public SkusCustomGetResponse sendSkusCustomGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.SKUS_CUSTOM_GET.getMethod(), sessionId, params));
		SkusCustomGetResponse skusCustomGetResponse = new SkusCustomGetResponse(response);
		if (skusCustomGetResponse.isSuccess()) {
			JSONObject json;
			try {
				json = new JSONObject(skusCustomGetResponse.getBody());
				parseError(skusCustomGetResponse, json);
				if (skusCustomGetResponse.isSuccess()) {
					// 处理返回结果
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.SKUS)) {
						JSONArray skus = rsp.getJSONArray(ApiConstants.SKUS);
						skusCustomGetResponse.setSkus(TaobaoSkuJSONConvert
								.convertJsonArrayToSkuList(skus));
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return skusCustomGetResponse;
	}

	public ItemRecommendDeleteResponse itemRecommendDelete(ItemRecommendDeleteRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareItemRecommendDelete(req);
		return sendItemRecommendDelte(params, sessionId);
	}

	public Map<String, Object> prepareItemRecommendDelete(ItemRecommendDeleteRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		return params;
	}

	public ItemRecommendDeleteResponse sendItemRecommendDelte(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_RECOMMEND_DELETE.getMethod(), sessionId, params));
		ItemRecommendDeleteResponse itemRecommendDeleteRequest = new ItemRecommendDeleteResponse(
				response);
		// 平台成功访问
		if (itemRecommendDeleteRequest.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemRecommendDeleteRequest.getBody());
				parseError(itemRecommendDeleteRequest, json);
				// 访问淘宝成功
				if (itemRecommendDeleteRequest.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						itemRecommendDeleteRequest.setIid(item.getString(ApiConstants.IID));
						try {
							itemRecommendDeleteRequest.setModified(DateUtil.strToDate(item
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemRecommendDeleteRequest;
	}

	public ItemRecommendAddResponse itemRecommendAdd(ItemRecommendAddRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareItemRecommendAdd(req);
		return addRecommendItem(params, sessionId);
	}

	public Map<String, Object> prepareItemRecommendAdd(ItemRecommendAddRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.IID, req.getIid());
		return params;
	}

	public ItemRecommendAddResponse addRecommendItem(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse response = this.getFetch().fetch(this.getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.ITEM_RECOMMEND_ADD.getMethod(), sessionId, params));
		ItemRecommendAddResponse itemRecommendAddResponse = new ItemRecommendAddResponse(response);
		// 平台成功访问
		if (itemRecommendAddResponse.isSuccess()) {
			try {
				JSONObject json = new JSONObject(itemRecommendAddResponse.getBody());
				parseError(itemRecommendAddResponse, json);
				// 访问淘宝成功
				if (itemRecommendAddResponse.isSuccess()) {
					// 对返回结果进行处理
					JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
					if (rsp.has(ApiConstants.ITEMS)) {
						JSONArray items = rsp.getJSONArray(ApiConstants.ITEMS);
						JSONObject item = items.getJSONObject(0);
						itemRecommendAddResponse.setIid(item.getString(ApiConstants.IID));
						try {
							itemRecommendAddResponse.setModified(DateUtil.strToDate(item
									.getString(ApiConstants.MODIFIED)));
						} catch (ParseException e) {
							throw new TaobaoApiException(e);
						}
					}
				}
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
		}
		return itemRecommendAddResponse;
	}

	public TradeGetResponse tradeSnapshotGet(TradeGetRequest req, String sessionId)
			throws TaobaoApiException {
		Map<String, Object> params = prepareTradeSnapshotGet(req);
		return sendTradeSnapshotGet(params, sessionId);
	}

	public Map<String, Object> prepareTradeSnapshotGet(TradeGetRequest req) {
		return prepareTradeGet(req);
	}

	protected TradeGetResponse sendTradeSnapshotGet(Map<String, Object> params, String sessionId)
			throws TaobaoApiException {
		TaobaoResponse rsp = getFetch().fetch(getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_SNAPSHOT_GET.getMethod(), sessionId, params));
		TradeGetResponse tradeGetResponse = new TradeGetResponse(rsp);

		// 访问淘宝成功
		if (tradeGetResponse.isSuccess()) {
			try {
				JSONObject jsonRoot = new JSONObject(tradeGetResponse.getBody());
				parseError(tradeGetResponse, jsonRoot); // 容错
				if (tradeGetResponse.isSuccess()) { // 容错之后是否还成功
					jsonRoot = jsonRoot.getJSONObject(ApiConstants.RSP);
					if (jsonRoot.has(ApiConstants.TRADES)) {
						JSONObject trade = jsonRoot.getJSONArray(ApiConstants.TRADES)
								.getJSONObject(0);
						tradeGetResponse.setTrade(TaobaoTradeJSONConvert.convertJsonToTrade(trade));
					}
				}
			} catch (Exception e) {
				throw new TaobaoApiException(e);
			}
		}

		return tradeGetResponse;
	}

	public TradeShippingAddressUpdateResponse tradeShippingaddressUpdate(
			TradeShippingAddressUpdateRequest req, String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTradeShippingaddressUpdate(req);
		return sendTradeShippingaddressUpdate(params, sessionId);
	}

	protected TradeShippingAddressUpdateResponse sendTradeShippingaddressUpdate(
			Map<String, Object> params, String sessionId) throws TaobaoApiException {
		TaobaoResponse tRsp = getFetch().fetch(getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_SHIPPING_ADDRESS_UPDATE.getMethod(), sessionId, params));
		TradeShippingAddressUpdateResponse rsp = new TradeShippingAddressUpdateResponse(tRsp);

		if (rsp.isSuccess()) {
			try {
				JSONObject jsonRoot = new JSONObject(rsp.getBody());
				parseError(rsp, jsonRoot);
				if (rsp.isSuccess()) {
					jsonRoot = jsonRoot.getJSONObject(ApiConstants.RSP);
					if (jsonRoot.has(ApiConstants.TRADES)) {
						JSONObject trade = jsonRoot.getJSONArray(ApiConstants.TRADES).getJSONObject(0);
						rsp.setTrade(TaobaoTradeJSONConvert.convertJsonToTrade(trade));
					}
				}
			} catch (Exception e) {
				throw new TaobaoApiException(e);
			}
		}

		return rsp;
	}

	public Map<String, Object> prepareTradeShippingaddressUpdate(
			TradeShippingAddressUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.TID, req.getTid());
		params.put(ApiConstants.RECEIVER_NAME, req.getReceiverName());
		params.put(ApiConstants.RECEIVER_PHONE, req.getReceiverPhone());
		params.put(ApiConstants.RECEIVER_MOBILE, req.getReceiverMobile());
		params.put(ApiConstants.RECEIVER_STATE, req.getReceiverState());
		params.put(ApiConstants.RECEIVER_CITY, req.getReceiverCity());
		params.put(ApiConstants.RECEIVER_DISTRINCT, req.getReceiverDistrict());
		params.put(ApiConstants.RECEIVER_ADDRESS, req.getReceiverAddress());
		params.put(ApiConstants.RECEIVER_ZIP, req.getReceiverZip());
		return params;
	}

	public TradeOrderSkuUpdateResponse tradeOrderskuUpdate(TradeOrderSkuUpdateRequest req,
			String sessionId) throws TaobaoApiException {
		Map<String, Object> params = prepareTradeOrderskuUpdate(req);
		return sendTradeOrderskuUpdate(params, sessionId);
	}

	protected TradeOrderSkuUpdateResponse sendTradeOrderskuUpdate(Map<String, Object> params,
			String sessionId) throws TaobaoApiException {
		TaobaoResponse tRsp = getFetch().fetch(
				getServiceUrl(),
				getTemplateRequest(TaobaoApiMethod.TRADE_ORDER_SKU_UPDATE.getMethod(), sessionId,
						params));
		TradeOrderSkuUpdateResponse rsp = new TradeOrderSkuUpdateResponse(tRsp);

		if (rsp.isSuccess()) {
			try {
				JSONObject jsonRoot = new JSONObject(rsp.getBody());
				parseError(rsp, jsonRoot);
				if (rsp.isSuccess()) {
					jsonRoot = jsonRoot.getJSONObject(ApiConstants.RSP);
					if (jsonRoot.has(ApiConstants.ORDERS)) {
						JSONObject order = jsonRoot.getJSONArray(ApiConstants.ORDERS)
								.getJSONObject(0);
						rsp.setOrder(TaobaoTradeJSONConvert.convertJsonToOrder(order));
					}
				}
			} catch (Exception e) {
				throw new TaobaoApiException(e);
			}
		}

		return rsp;
	}

	public Map<String, Object> prepareTradeOrderskuUpdate(TradeOrderSkuUpdateRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.OID, req.getOid());
		params.put(ApiConstants.SKU_ID, req.getSkuId());
		params.put(ApiConstants.SKU_PROPS, req.getSkuProps());
		return params;
	}

	public ItemPropsVerticalGetResponse itemPropsVerticalGet(
			ItemPropsVerticalGetRequest request) throws TaobaoApiException {
		return null;
	}
	
	public Map<String, Object> prepareItempropsVerticalGet(ItemPropsVerticalGetRequest req) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(ApiConstants.FIELDS, req.getFields());
		params.put(ApiConstants.CID, req.getCid());
		params.put(ApiConstants.TYPE, req.getType());
		return params;
	}

	/**
	 * For API 2.0 only, so no implementation here.
	 */
	public LogisticsPartnersGetResponse logisticsPartnersGet(
			LogisticsPartnersGetRequest request) throws TaobaoApiException {
		return null;
	}

	/**
	 * For API 2.0 only, so no implementation here.
	 */
	public DeliveryCodSendResponse deliveryCodSend(
			DeliveryCodSendRequest request, String sessionId)
			throws TaobaoApiException {
		return null;
	}
}