/**
 * 
 */
package com.taobao.api;

/**
 * 淘宝REST客户端, 实现了公开数据接口和隐私数据接口和REST客户端接口。
 * 
 * @version 2008-8-28
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 */
public interface TaobaoRestClient extends TaobaoPublicApi,
		TaobaoPrivateApi, RestClient {

}
