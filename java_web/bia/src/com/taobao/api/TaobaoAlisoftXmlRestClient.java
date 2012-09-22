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
public class TaobaoAlisoftXmlRestClient extends TaobaoAlisoftBaseRestClient {
	private static final String XML = "xml";
	
	public TaobaoAlisoftXmlRestClient(String appkey, String secret)
			throws TaobaoApiException {
		this(appkey, secret, false);
	}

	public TaobaoAlisoftXmlRestClient(String appkey, String secret,
			boolean isSandbox) throws TaobaoApiException {
		this(isSandbox ? ApiConstants.SIP_SANDBOX_SERVICE_URL
				: ApiConstants.SIP_SERVICE_URL, appkey, secret);
	}

	public TaobaoAlisoftXmlRestClient(String serviceUrl, String appkey,
			String secret) throws TaobaoApiException {
		this(serviceUrl, "2.0", appkey, secret);
	}

	public TaobaoAlisoftXmlRestClient(String serviceUrl, String version,
			String appkey, String secret) throws TaobaoApiException {
		super(serviceUrl, version, appkey, secret);
	}


}