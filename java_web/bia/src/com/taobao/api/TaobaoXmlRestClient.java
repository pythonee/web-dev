package com.taobao.api;


/**
 * 淘宝对外部提供的REST API客户端
 * 
 * 注意，默认是SIP入口。使用淘宝API入口需要设置： 4
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 */
public class TaobaoXmlRestClient extends TaobaoBaseRestClient {
	private static final String XML = "xml";

	public TaobaoXmlRestClient(String appkey, String secret) throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public TaobaoXmlRestClient(String appkey, String secret, boolean isSandbox)
			throws TaobaoApiException {
		this(isSandbox ? ApiConstants.API_SANDBOX_SERVICE_URL : ApiConstants.API_SERVICE_URL,
				appkey, secret);
		
	}

	public TaobaoXmlRestClient(String serviceUrl, String appkey, String secret)
			throws TaobaoApiException {
		this(serviceUrl, ApiConstants.DEFAULT_SERVICE_VERSION, appkey, secret);
	}

	public TaobaoXmlRestClient(String serviceUrl, String version, String appkey, String secret)
			throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
		this.setFormat(XML);
	}

}
