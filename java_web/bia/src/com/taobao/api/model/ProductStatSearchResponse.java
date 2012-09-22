package com.taobao.api.model;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.convert.reader.ApiName;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;

/**
 * 代表一个产品统计搜索的http返回结果
 * 
 * @author hukui
 * 
 */
public class ProductStatSearchResponse extends TaobaoResponse {

	private static final long serialVersionUID = -1016694393552382796L;

	private static final String PRODUCT_SEARCH = "product_search";

	private static final String TOTAL_RESULTS = "totalResults";

	private ProductSearch productSearch; // 搜索结果

	/**
	 * 为了测试回归，暂且加此注释  add by jeck 2009-10-17
	 */
	@ApiName("totalResults")
	private int totalResults; // 搜索到的产品总数。

	public ProductStatSearchResponse() {
		super();
	}

	public ProductStatSearchResponse(TaobaoResponse ur) {
		super(ur);
	}

	protected void parseError(JSONObject json) throws JSONException {

		if (json.has(ApiConstants.ERROR_RSP)) {
			JSONObject errorRsp = json.getJSONObject(ApiConstants.ERROR_RSP);

			setErrorCode(errorRsp.getString(ApiConstants.ERROR_CODE));

			setMsg(errorRsp.getString(ApiConstants.ERROR_MSG));
		}
	}

	public boolean fillResponse(JSONObject json) {
		try {
			parseError(json);
			// 对返回结果进行处理
			if (isSuccess()) {
				JSONObject rsp = json.getJSONObject(ApiConstants.RSP);
				if (rsp.has(ProductStatSearchResponse.PRODUCT_SEARCH)) {
					JSONObject search = rsp.getJSONObject(ProductStatSearchResponse.PRODUCT_SEARCH);
					productSearch = ProductSearch.convertJsonToObject(search);
				}
				if (rsp.has(ProductStatSearchResponse.TOTAL_RESULTS)) {
					totalResults = rsp.getInt(ProductStatSearchResponse.TOTAL_RESULTS);
				}
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (TaobaoApiException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ProductSearch getProductSearch() {
		return productSearch;
	}

	public void setProductSearch(ProductSearch productSearch) {
		this.productSearch = productSearch;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

}
