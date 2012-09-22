package com.taobao.api;

import com.taobao.api.TaobaoApiException;

/**
 * 淘宝对阿里软件SIP提供的API客户端
 * 
 * 
 * <code>
 * this.useTaobaoProtocol()
 * </code>
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 */
public class LegacyTaobaoAlisoftJsonRestClient extends LegacyTaobaoJsonRestClient {

	public LegacyTaobaoAlisoftJsonRestClient(String appkey, String secret)
			throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public LegacyTaobaoAlisoftJsonRestClient(String appkey, String secret,
			boolean isSandbox) throws TaobaoApiException {
		this(isSandbox ? ApiConstants.SIP_SANDBOX_SERVICE_URL
				: ApiConstants.SIP_SERVICE_URL, appkey, secret);
	}

	public LegacyTaobaoAlisoftJsonRestClient(String serviceUrl, String appkey,
			String secret) throws TaobaoApiException {
		super(serviceUrl, appkey, secret);
	}

	public LegacyTaobaoAlisoftJsonRestClient(String serviceUrl, String version,
			String appkey, String secret) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
	}

	/**
	 * @see getAppKeyName
	 */
	@Deprecated
	protected String getApiKeyName() {
		return ApiConstants.SIP_APPKEY;
	}
	
	protected String getAppKeyName() {
		return ApiConstants.SIP_APPKEY;
	}

	protected String getMethodName() {
		return ApiConstants.SIP_APINAME;
	}

	protected String getSignName() {
		return ApiConstants.SIP_SIGN;
	}

	protected String getSessionIdName() {
		return ApiConstants.SIP_SESSIONID;
	}

	protected String getTimestampName() {
		return ApiConstants.SIP_TIMESTAMP;
	}

}