package com.taobao.api.model;

import java.util.ArrayList;
import java.util.List;

import com.taobao.api.TaobaoApiException;
import com.taobao.api.convert.TaobaoItemJSONConvert;
import com.taobao.api.convert.reader.ApiSubDomainName;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;

/**
 * 代表一个产品统计搜索结果
 * 
 * @author hukui
 * 
 */
public class ProductSearch extends TaobaoModel {

	private static final long serialVersionUID = -6873178647341330361L;

	private static final String PRODUCTS = "products"; // 产品列表对应属性名。

	private static final String CATEGORIES = "categories"; // 产品统计的类目对应属性名。

	private static final String PROPS = "props"; // 产品统计的属性/属性值对应属性名。

	private static final String PROP_PATH = "prop_path"; // 属性导航对应属性名。

	private static final String PROP_PATH_STR = "prop_path_str"; // 属性导航值对应属性名。

	@ApiSubDomainName("product")
	private List<ProductStat> products = new ArrayList<ProductStat>(); // 搜索到的产品列表。

	@ApiSubDomainName("category")
	private List<ItemCategory> categories = new ArrayList<ItemCategory>(); // 产品统计的类目。

	@ApiSubDomainName("prop")
	private List<ProductProp> props = new ArrayList<ProductProp>(); // 产品统计的属性/属性值。

	private String propPath; // 属性导航,当前搜索的属性列表。格式：pid:vid;pid:vid。

	private String propPathStr; // 属性导航值。当前搜索的属性字符串列表。比如:品牌:诺基亚;铃声:32和弦。

	public List<ProductStat> getProducts() {
		return products;
	}

	public void setProducts(List<ProductStat> products) {
		this.products = products;
	}

	public List<ItemCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ItemCategory> categories) {
		this.categories = categories;
	}

	public List<ProductProp> getProps() {
		return props;
	}

	public void setProps(List<ProductProp> props) {
		this.props = props;
	}

	public String getPropPath() {
		return propPath;
	}

	public void setPropPath(String propPath) {
		this.propPath = propPath;
	}

	public String getPropPathStr() {
		return propPathStr;
	}

	public void setPropPathStr(String propPathStr) {
		this.propPathStr = propPathStr;
	}

	/**
	 * 把JSON格式转成ProductSearch对象
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static ProductSearch convertJsonToObject(JSONObject json) throws JSONException, TaobaoApiException {
		ProductSearch search = new ProductSearch();

		if (json == null) {
			return search;
		}

		if (json.has(ProductSearch.CATEGORIES)) {
			JSONArray array = json.getJSONArray(ProductSearch.CATEGORIES);
			if (array != null) {
				for (int i = 0; i < array.length(); i++) {
					search.getCategories().add(TaobaoItemJSONConvert.convertJsonToItemCategory(array.getJSONObject(i)));
				}
			}
		}

		if (json.has(ProductSearch.PRODUCTS)) {
			JSONArray array = json.getJSONArray(ProductSearch.PRODUCTS);
			if (array != null) {
				for (int i = 0; i < array.length(); i++) {
					search.getProducts().add(ProductStat.convertJsonToObject(array.getJSONObject(i)));
				}
			}
		}

		if(json.has(ProductSearch.PROP_PATH)){
			search.setPropPath(json.getString(ProductSearch.PROP_PATH));
		}
		
		if(json.has(ProductSearch.PROP_PATH_STR)){
			search.setPropPathStr(json.getString(ProductSearch.PROP_PATH_STR));
		}
		
		if(json.has(ProductSearch.PROPS)){
			JSONArray array = json.getJSONArray(ProductSearch.PROPS);
			if (array != null) {
				for (int i = 0; i < array.length(); i++) {
					search.getProps().add(ProductProp.convertJsonToObject(array.getJSONObject(i)));
				}
			}
		}
		
		return search;
	}
}
