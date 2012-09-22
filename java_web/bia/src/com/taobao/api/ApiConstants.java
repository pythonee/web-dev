package com.taobao.api;

/**
 * 工程常量存放
 * 
 * @author gaoweibin.tw
 * 
 */
public class ApiConstants {

	/**
	 * 阿里软件SIP（服务集成平台）入口
	 */
	public static final String SIP_SERVICE_URL = "http://sip.alisoft.com/sip/rest/";
	/**
	 * 阿里软件SIP沙箱环境入口
	 */
	public static final String SIP_SANDBOX_SERVICE_URL = "http://sipdev.alisoft.com/sip/rest/";

	/**
	 * 淘宝API入口
	 */
	public static final String API_SERVICE_URL = "http://gw.api.taobao.com/router/rest";

	/**
	 * 淘宝插件平台入口
	 */
	public static final String CONTAINER_URL = "http://container.api.taobao.com/container";

	/**
	 * 淘宝API沙箱环境入口
	 */
	public static final String API_SANDBOX_SERVICE_URL = "http://gw.sandbox.taobao.com/router/rest";

	/**
	 * 淘宝插件平台沙箱环境入口
	 */
	public static final String CONTAINER_SANDBOX_URL = "http://container.sandbox.taobao.com/container";

	public static final String EMPTY_STR = "";
	// SIP系统级参数
	public static final String SIP_APPKEY = "sip_appkey";
	public static final String SIP_APINAME = "sip_apiname";
	public static final String SIP_SESSIONID = "sip_sessionid";
	public static final String SIP_SIGN = "sip_sign";
	public static final String SIP_TIMESTAMP = "sip_timestamp";
	// 系统级参数
	/**
	 * @see APPKEY
	 */
	@Deprecated
	public static final String APIKEY = "api_key";
	public static final String APPKEY = "app_key";
	
	public static final String METHOD = "method";
	public static final String SESSION = "session";
	public static final String SIGN = "sign";
	public static final String TIMESTAMP = "timestamp";
	// 默认版本号
	public static final String DEFAULT_SERVICE_VERSION = "2.0";
	
	// Error Response Params
	public static final String ERROR_RSP = "error_rsp";
	public static final String ERROR_CODE = "code";
	public static final String ERROR_MSG = "msg";
	// Base Response Params
	public static final String RSP = "rsp";
	public static final String TOTALRESULTS = "totalResults";
	// 应用参数
	public static final String ITEMS = "items";
	public static final String ITEM_SEARCHS="itemsearchs";
	public static final String TAOBAO_TAOKE_ITEMS = "taobaokeItems";
	public static final String TAOBAO_TAOBAOKE_SHOPS = "taobaokeShops";
	public static final String ITEMEXTRAS = "itemextras";
	public static final String USERS = "users";
	public static final String ITEM_CATS = "item_cats";
	public static final String SHOP_CATS = "shop_cats";
	public static final String SELLER_CATS = "seller_cats";
	public static final String SPUS = "spus";
	public static final String ITEM_PROPS = "item_props";
	public static final String POSTAGES = "postages";
	public static final String SKUS = "skus";
	public static final String SHIPPING_ADDRESS = "shipping_addresses";
	public static final String PRODUCTS = "products";
	// Base Params
	public static final String ANONS = "anons";
	public static final String TYPE = "type";
	public static final String STUFF_STATUS = "stuff_status";
	public static final String APPROVE_STATUS = "approve_status";
	public static final String OPTIONS = "options";
	public static final String CID = "cid";
	public static final String DATETIME = "datetime";
	public static final String PROPS = "props";
	public static final String PARENT_PID = "parent_pid";
	public static final String IS_KEY_PROP = "is_key_prop";
	public static final String IS_SALE_PROP = "is_sale_prop";
	public static final String IS_COLOR_PROP = "is_color_prop";
	public static final String IS_ENUM_PROP = "is_enum_prop";
	public static final String IS_INPUT_PROP = "is_input_prop";
	public static final String IS_ITEM_PROP = "is_item_prop";
	public static final String CHILD_TEMPLATE = "child_template";
	public static final String PVS = "pvs";
	public static final String SORT_ORDER = "sort_order";
	public static final String SORT = "sort";
	public static final String LAST_MODIFIELD = "lastModified";
	public static final String PROP_NAME = "prop_name";
	public static final String MUST = "must";
	public static final String MULTI = "multi";
	public static final String PARENT_VID = "parent_vid";
	public static final String NUM = "num";
	public static final String PRICE = "price";
	public static final String TITLE = "title";
	public static final String DESC = "desc";
	public static final String LOCATION_STATE = "location.state";
	public static final String LOCATION_CITY = "location.city";
	public static final String FREIGHT_PAYER = "freight_payer";
	public static final String VALID_THRU = "valid_thru";
	public static final String HAS_INVOICE = "has_invoice";
	public static final String HAS_WARRANTY = "has_warranty";
	public static final String SELLER_CIDS = "seller_cids";
	public static final String HAS_DISCOUNT = "has_discount";
	public static final String POST_FEE = "post_fee";
	public static final String EXPRESS_FEE = "express_fee";
	public static final String LIST_TIME = "list_time";
	public static final String INCREMENT = "increment";
	public static final String AUTO_REPOST = "auto_repost";
	public static final String HAS_SHOWCASE = "has_showcase";
	public static final String EMS_FEE = "ems_fee";
	public static final String FIELDS = "fields";
	public static final String NICK = "nick";
	public static final String USER_ID = "user_id";
	public static final String IID = "iid";
	public static final String XID = "xid";
	public static final String DETAIL_URL = "detail_url";
	public static final String NUM_IID = "num_iid";
	public static final String IIDS = "iids";
	public static final String PIC_PATH = "pic_path";
	public static final String PICT_URL="pict_url";
	public static final String PROMOTION_INFO="promotion_info";
	public static final String DELIST_TIME = "delist_time";
	public static final String BULK_BASE_NUM = "bulk_base_num";
	public static final String MODIFIED = "modified";
	public static final String Q = "q";
	public static final String PAGE_NO = "page_no";
	public static final String PAGE_SIZE = "page_size";
	public static final String ORDER_BY = "order_by";
	public static final String START_PRICE = "start_price";
	public static final String END_PRICE = "end_price";
	public static final String NICKS = "nicks";
	public static final String SELLER_NICK = "seller_nick";
	public static final String BUYER_NICK = "buyer_nick";
	public static final String CREATED = "created";
	public static final String TID = "tid";
	public static final String ORDER_ID = "order_id";
	public static final String STATUS = "status";
	public static final String SELLER_RATE = "seller_rate";
	public static final String BUYER_RATE = "buyer_rate";
	public static final String START_CREATED = "start_created";
	public static final String END_CREATED = "end_created";
	public static final String SEX = "sex";
	public static final String BUYER_CREDIT = "buyer_credit";
	public static final String LEVEL = "level";
	public static final String SCORE = "score";
	public static final String TOTAL_NUM = "total_num";
	public static final String GOOD_NUM = "good_num";
	public static final String SELLER_CREDIT = "seller_credit";
	public static final String LOCATION = "location";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTRY = "country";
	public static final String ZIP = "zip";
	public static final String ADDRESS = "address";
	public static final String LAST_VISIT = "last_visit";
	public static final String REAL_NAME = "real_name";
	public static final String ID_CARD = "id_card";
	public static final String PHONE = "phone";
	public static final String MOBILE = "mobile";
	public static final String EMAIL = "email";
	public static final String BIRTHDAY = "birthday";
	public static final String PARENT_CID = "parent_cid";
	public static final String CIDS = "cids";
	public static final String IS_PARENT = "is_parent";
	public static final String NAME = "name";
	public static final String NAME_ALIAS = "name_alias";
	public static final String BINDS = "binds";
	public static final String PID = "pid";
	public static final String CHILD_PATH = "child_path";
	public static final String PUB_MUST = "pub_must";
	public static final String PUB_MULTI = "pub_multi";
	public static final String PROP_VALUES = "prop_values";
	public static final String VID = "vid";
	public static final String TRADES = "trades";
	public static final String ALIPAYNO = "alipay_no";
	public static final String ALIPAY_ACCOUNT = "alipay_account";
	public static final String PAYMENT = "payment";
	public static final String SID = "sid";
	public static final String DELIVERY_START = "delivery_start";
	public static final String DELIVERY_END = "delivery_end";
	public static final String OUT_SID = "out_sid";
	public static final String ITEM_TITLE = "item_title";
	public static final String RECEIVER_NAME = "receiver_name";
	public static final String RECEIVER_MOBILE = "receiver_mobile";
	public static final String RECEIVER_PHONE = "receiver_phone";
	public static final String RECEIVER_LOCATION = "receiver_location";
	public static final String COMPANY_NAME = "company_name";
	public static final String BULLETIN = "bulletin";
	public static final String ROLE = "role";
	public static final String RATED_NICK = "rated_nick";
	public static final String RESULT = "result";
	public static final String ITEM_PRICE = "item_price";
	public static final String CONTENT = "content";
	public static final String REPLY = "reply";
	public static final String ANONY = "anony";
	public static final String SHIPPINGS = "ship";
	public static final String SHOPS = "shops";
	public static final String TRADERATES = "rates";
	public static final String SELLER_CONFIRM = "seller_confirm";
	public static final String IS_VIRTUAL_GOODS = "virtual_goods";
	public static final String RATE_TYPE = "rate_type";
	public static final String TIME_PERIOD = "time_period";
	public static final String BANNER = "banner";
	public static final String BULK_PRICE = "bulk_price";
	public static final String REMAIN_COUNT = "remain_count";
	public static final String POSTAGE_ID = "postage_id";
	public static final String MEMO = "memo";
	public static final String PICID = "pic_id";
	public static final String PRODUCT_ID = "product_id";
	public static final String AUCTION_POINT = "auction_point";
	public static final String PROPERTY_ALIAS = "property_alias";
	public static final String ITEMIMGS = "itemImgs";
	public static final String ITEM_IMG = "item_img";
	public static final String PROPIMGS = "propImgs";
	public static final String PROP_IMG = "prop_img";
	public static final String SKU = "sku";
	public static final String SKU_ID = "sku_id";
	public static final String SKU_PROPERTIES_NAME = "sku_properties_name"; // add by jeck 2009-06-26 TAOBAOAPI-756
	public static final String ITEM_MEAL_NAME = "item_meal_name"; // add by jeck 2009-06-26 TAOBAOAPI-756
	public static final String ITEM_MEMO = "item_memo";
	public static final String TRADE_MEMO = "trade_memo";// add by jeck 2009-08-31
	public static final String LOCATION_ID = "location.id";// add by jeck 2009-09-03
	public static final String IS_ALLOW_ALIAS = "is_allow_alias";// add by jeck 2009-09-03
	public static final String IS_3D = "is_3D";	
	
	public static final String Features = "features";
	public static final String ATTR_KEY = "attr_key";
	public static final String ATTR_KEYS = "attr_keys";
	public static final String ATTR_VALUE = "attr_value";
	
	public static final String EXTRA_ID="extra_id";
	public static final String ID = "id";
	public static final String ITEMIMG_ID = "itemimg_id";
	public static final String PROPIMG_ID = "propimg_id";
	public static final String URL = "url";
	public static final String POSITION = "position";
	public static final String PROPERTIES = "properties";
	public static final String QUANTITY = "quantity";
	public static final String OUTER_ID = "outer_id";
	public static final String WW_STATUS = "ww_status";
	public static final String POST_FREE = "post_free";
	public static final String SKU_IDS = "sku_ids";
	public static final String SKU_EXTRA_IDS="sku_extra_ids";
	public static final String SKU_PROPS = "sku_props";
	public static final String SKU_PROPERTIES = "sku_properties";
	public static final String SKU_QUANTITIES = "sku_quantities";
	public static final String SKU_PRICES = "sku_prices";
	public static final String SKU_OUTER_IDS = "sku_outer_ids";
	public static final String SKU_MEMOS = "sku_memos";
	public static final String START_DATE = "start_date";
	public static final String END_DATE = "end_date";
	public static final String ISMAJOR = "is_major";
	public static final String INPUT_PIDS = "input_pids";
	public static final String INPUT_STR = "input_str";
	public static final String KEYWORD = "keyword";
	public static final String ITEM_ID = "item_id";
	public static final String SEND_TYPE = "send_type";
	public static final String PAY_TYPE = "pay_type";
	public static final String CREDIT = "credit";
	public static final String START_CREDIT = "start_credit";
	public static final String END_CREDIT = "end_credit";
	public static final String GUARANTEE = "guarantee";
	public static final String CHICK_URL = "chick_url";
	public static final String CLICK_URL = "click_url";
	public static final String CATEGORY_ID="category_id";
	public static final String COUNT="count";
	public static final String ITEM_LISTS="item_list";
	public static final String CATEGORY_LISTS="category_list";
	public static final String IS_VIRTURAL="is_virtual";
	
	public static final String REFUND_STATUS = "refund_status";
	public static final String BUYER_MESSAGE = "buyer_message";
	public static final String PAY_TIME = "pay_time";
	public static final String END_TIME = "end_time";
	public static final String BUYER_OBTAIN_POINT_FEE = "buyer_obtain_point_fee";
	public static final String POINT_FEE = "point_fee";
	public static final String REAL_POINT_FEE = "real_point_fee";
	public static final String SELLER_MEMO = "seller_memo";
	public static final String BUYER_MEMO = "buyer_memo";
	public static final String ORDERS = "orders";
	public static final String LANG = "lang";
	public static final String TOTAL_FEE = "total_fee";
	public static final String IS_TIMING = "is_timing";
	public static final String SERVICE_CODE = "service_code";
	public static final String SUITES = "suites";
	public static final String SUITE_NAME = "suite_name";
	public static final String FEATURE="feature";
	public static final String EID="eid";
	public static final String RESERVE_PRICE="reserve_price";
	public static final String IS_TAOBAO = "is_taobao";
	public static final String IS_EX = "is_ex";
	public static final String ITEM_TYPE = "item_type";
	//获得淘宝系统时间
	public static final String MAIN_TIME = "maintime";
	public static final String REMAIN_TIME = "remain_time";
	//收费
	public static final String SUB_PROD_ID = "sub_prod_id";
	//用户相关
	public static final String HAS_MORE_PIC = "has_more_pic";
	public static final String ITEMIMG_NUM = "item_img_num";
	public static final String ITEMIMG_SIZE = "item_img_size";
	public static final String PROP_IMG_NUM = "prop_img_num";
	public static final String PROP_IMG_SIZE = "prop_img_size";
	public static final String USER_PROMOTEDTYPE = "promoted_type";
	public static final String USER_STATUS = "status";
	public static final String USER_ALIPAYBAND = "alipay_bind";
	public static final String CONSUMER_PROTECTION = "consumer_protection";
	public static final String ADDRESS_ID = "address_id";
	public static final String IS_DEFAULT = "is_default";
	public static final String DISTRICT = "district";
	
	public static final String BIZ_ORDER_ID="biz_order_id";
	public static final String REFUND_ID="refund_id";
	public static final String REFUND_FEE="refund_fee";
	public static final String GOOD_STATUS="good_status";
	public static String HAS_GOOD_RETURN = "has_good_return";
	public static String REASON = "reason";
	public static String GOOD_RETURN_TIME = "good_return_time";
	public static String REFUNDS = "refunds";
	public static String REFUND_MESSAGES = "refundMessages";
	public static final String MESSAGE_ID="message_id";
	public static final String MESSAGE_TYPE="message_type";
	public static final String OWNER_ID="owner_id";
	public static final String OWNER_NICK="owner_nick";
	public static final String OWNER_ROLE="owner_role";
	public static final String REFUND_REMIND_TIMEOUT = "refund_remind_timeout";
	public static final String REMIND_TYPE = "remind_type";
	public static final String EXIST_TIMEOUT = "exist_timeout";
	public static final String TIMEOUT = "timeout";
	public static final String ITEM_PICT_URL = "item_pict_url";
	public static final String REFUND_MESSAGE_PICTURE_URL = "url";
	public static final String PICTURE_URLS = "picture_urls";
	public static final String OID = "oid";	
	public static final String ORDER_STATUS = "order_status";
	
	//邮费模板相关
	public static final String POSTAGE_MODE = "postage_mode_list";
	public static final String POST_PRICE = "post_price";
	public static final String POST_INCREASE = "post_increase";
	public static final String EXPRESS_PRICE = "express_price";
	public static final String EXPRESS_INCREASE = "express_increase";
	public static final String EMS_PRICE = "ems_price";
	public static final String EMS_INCREASE = "ems_increase";
	public static final String DEST = "dest";
	public static final String INCREASE = "increase";
	public static final String POSTAGE_MODE_TYPE = "postage_mode.type";
	public static final String POSTAGE_MODE_TYPES = "postage_mode_types";
	public static final String POSTAGE_MODE_DEST = "postage_mode.dest";
	public static final String POSTAGE_MODE_DESTS = "postage_mode_dests";
	public static final String POSTAGE_MODE_PRICE = "postage_mode.price";
	public static final String POSTAGE_MODE_PRICES = "postage_mode_prices";
	public static final String POSTAGE_MODE_INCREASE = "postage_mode.increase";
	public static final String POSTAGE_MODE_INCREASES = "postage_mode_increases";
	public static final String POSTAGE_MODE_ID = "postage_mode.id";
	public static final String POSTAGE_MODE_IDS = "postage_mode_ids";
	public static final String POSTAGE_MODE_OPTTYPE = "postage_mode_optTypes";
	public static final String POSTAGE_MODE_ID_OLD = "postage_mode_id";
	public static final String POSTAGE_MODE_TYPE_OLD = "postage_mode_type";
//	public static final String MODE_POSTAGE_ID = "mode_postage_id";
	
	//物流
	public static final String AREA_ID = "area_id";
	public static final String AREA_TYPE = "area_type";
	public static final String AREA_NAME = "area_name";
	public static final String PARENT_ID = "parent_id";
	public static final String COMPANY_ID = "company_id";
	public static final String COMPANY_CODE = "company_code";
	public static final String SEND_AREA = "send_area";
	public static final String NOT_SEND_AREA = "not_send_area";
	public static final String AREAS = "areas";
	public static final String AREA = "area";
	public static final String LOGISTICS_COMPANY = "logistic_companies";
	public static final String IS_RECOMMENTED = "is_recommended";
	public static final String ORDER_MODE = "order_mode";
	public static final String APP_IP = "app_ip";
	public static final String SELLER_AREA_ID = "seller_area_id";
	public static final String SELLER_ADDRESS = "seller_address";
	public static final String SELLER_ZIP = "seller_zip";
	public static final String IS_SUCCESS = "is_success";
	public static final String SERVICE_TYPE = "service_type";
	public static final String SOURCE_ID = "source_id";
	public static final String TARGET_ID = "target_id";
	public static final String GOODS_VALUE = "goods_value";
	
	// 物流 Fetcher
	
	public static final String FETCHER_NAME = "fetcher_name";
	public static final String FETCHER_AREA_ID = "fetcher_area_id";
	public static final String FETCHER_ADDRESS = "fetcher_address";
	public static final String FETCHER_ZIP = "fetcher_zip";
	public static final String FETCHER_PHONE = "fetcher_phone";
	public static final String FETCHER_MOBILE = "fetcher_mobile";
	
	public static final String CONSIGNTIME = "consign_time";
	public static final String SNAPSHOT = "snapshot";
	public static final String OUTER_SKUID_IDS = "outer_skuid_ids";
	public static final String PRICES = "prices";
	public static final String NUMS = "nums";
	public static final String GOODS_URLS = "good_urls";
	public static final String TITLES = "titles";
	public static final String LOCATION_ZIP = "location.zip";
	public static final String LOCATION_ADDRESS = "location.address";
	public static final String LOCATION_DISTRICT = "location.district";
	public static final String DIVISION_CODE = "division_code";
	public static final String START_MODIFIED = "start_modified";
	public static final String END_MODIFIED = "end_modified";
	public static final String LOGISTIC_TYPE = "logistic.type";
	public static final String SHOP_PROMOTION = "shop_promotion"; // 促销活动
	public static final String SHOP_TITLE = "shop_title";
	public static final String SHIPPING_TYPE = "shipping_type";
	public static final String COD_FEE = "cod_fee";
	public static final String COD_STATUS = "cod_status";
	public static final String PAY_TITLES = "pay_title";
	public static final String DISCOUNT_FEES = "discount_fees";
	public static final String SAVE_DELIVER_ADDR = "save_deliver_addr";
	public static final String ITEM_MEMOS = "item_memos";
	
	/*平威2009-03-03新增字段*/
	public static final String BUYER_ALIPAY_NO = "buyer_alipay_no";
	public static final String RECEIVER_STATE = "receiver_state";
	public static final String RECEIVER_CITY = "receiver_city";
	public static final String RECEIVER_DISTRINCT = "receiver_district";
	public static final String RECEIVER_ADDRESS = "receiver_address";
	public static final String RECEIVER_ZIP = "receiver_zip";
	public static final String CONSIGN_TIME = "consign_time";
	public static final String BUYER_EAMIL = "buyer_email";
	public static final String COMMISSION_FEE = "commission_fee";
	public static final String SELLER_ALIPAY_NO = "seller_alipay_no";
	public static final String SELLER_MOBILE = "seller_mobile";
	public static final String SELLER_PHONE = "seller_phone";
	public static final String SELLER_NAME = "seller_name";
	public static final String SELLER_EAMIL = "seller_email";
	public static final String OUTERSKUIDID = "outer_sku_id";
	public static final String OUTERIID = "outer_iid";
	public static final String CLOSE_REASON = "close_reason";
	
	/*2009.03.26 交易发布新增*/
	public static final String DISCOUNT_FEE = "discount_fee";
	public static final String ADJUST_FEE = "adjust_fee";
	public static final String SNAPSHOT_URL = "snapshot_url";
	
	public static final String RATE_STATUS = "rate_status";
	
	public static final String CONFIRM_FEES = "confirmFees";
	public static final String IS_DETAIL = "is_detail";
	public static final String CONFIRM_FEE = "confirm_fee";
	public static final String CONFIRM_POST_FEE = "confirm_post_fee";
	public static final String IS_LAST_DETAIL_ORDER = "is_last_detail_order";
	
	
	/*2009.4.9交易发布新增*/
	public static final String AVAILABLE_CONFIRM_FEE = "available_confirm_fee";
	public static final String HAS_POST_FEE = "has_post_fee";
	public static final String TIMEOUT_ACTION_TIME = "timeout_action_time";
	public static final String RECEIVED_PAYMENT = "received_payment";
	
	public static final String SELLER_AUTHORIZE = "authorizes"; // @see SellerAuthorize
	public static final String BRANDS = "brands"; // @see Brand
	
	public static final String COMMISSION = "commission";
	public static final String COMMISSION_RATE = "commission_rate";
	public static final String SHOP_COMMISSION_RATE = "shop_commission_rate";
	public static final String COMMISSION_NUM = "commission_num";
	public static final String COMMISSION_VOLUME = "commission_volume";
	public static final String OUTER_CODE = "outer_code";
	public static final String START_COMMISSION = "start_commission";
	public static final String END_COMMISSION = "end_commission";
	public static final String START_COMMISSION_RATE = "start_commissionRate";
	public static final String END_COMMISSION_RATE = "end_commissionRate";
	public static final String START_COMMISSION_NUM = "start_commissionNum";
	public static final String END_COMMISSION_NUM = "end_commissionNum";
	public static final String SIDS = "sids";
	
	public static final String DELIVERY_SEND_TYPE = "order_type";

	/* 2009.7.9 保证金相关新增 */
	public static final String BEGIN_DATE = "begin_date";
	public static final String AMOUNT = "amount";
	public static final String BALANCE = "balance";
	public static final String SELLER_ID = "seller_id";
	public static final String BUYER_ID = "buyer_id";
	public static final String ACCUSE_ID = "accuse_id";
	public static final String OPERATION_TYPE = "operation_type";
	public static final String OPERATION_ACTION = "operation_action";
	public static final String CREATOR = "creator";
	public static final String OPERATOR = "operator";
	public static final String GUARANTY_FUNDS = "guarantyFunds";

	/* 2009.7.14产品增值服务新增 */
	public static final String VAS_PRODUCT = "vas_product";
	public static final String PROD_ID = "prod_id";
	public static final String PROD_NAME = "prod_name";
	public static final String SERV_NAME = "serv_name";
	public static final String SERV_CODE = "serv_code";
	public static final String PLAN_ID = "plan_id";
	public static final String PLAN_NAME = "plan_name";
	public static final String TIME = "time";

	/* 2009.7.24申请应用新增 */
	public static final String PARENT_APP_KEY = "parent_app_key";
	public static final String CALLBACK_URL = "callback_url";
	public static final String NOTIFY_URL = "notify_url";
	public static final String TADGET = "tadget";
	public static final String APP_KEY = "app_key";
	public static final String APP_SECRET = "app_secret";
	
	//视频相关字段
	public static final String VIDEO_ID = "video_id";
	public static final String VIDEO = "video";
	public static final String VIDEOS = "videos";
	
	//商品搜索2009-11-26日常新增
	public static final String START_SCORE = "start_score";
	public static final String END_SCORE = "end_score";
	public static final String START_VOLUME = "start_volume";
	public static final String END_VOLUME = "end_volume";
	public static final String ONE_STATION = "one_station";
	public static final String IS_COD = "is_cod";
	public static final String IS_MALL = "is_mall";
	public static final String IS_PREPAY = "is_prepay";
	public static final String GENUINE_SECURITY = "genuine_security";
	public static final String PROMOTED_SERVICE = "promoted_service";
	public static final String VOLUME = "volume";
	
	public static final String GAME_TYPE  = "game_type";
	public static final String VERTICAL_IDS = "vertical_ids";
	public static final String VERTICAL_VALUES = "vertical_values";
	public static final String VERTICAL_IMAGE_ID = "vertical_image_id";
}
