package com.taobao.api.model;

/**
 * 淘宝应用/子应用。
 * 
 * @author fengsheng
 * @since 1.0, Jul 24, 2009
 */
public class Tadget extends TaobaoModel {

	private static final long serialVersionUID = 529005926114776105L;

	public static final String TABLE_NAME = "tadgets";
	public static final String DOMAIN_NAME = "tadget";
	public static final String APP_KEY = "app_key";
	public static final String APP_SECRET = "app_secret";

	private String appKey;
	private String appSecret;

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
