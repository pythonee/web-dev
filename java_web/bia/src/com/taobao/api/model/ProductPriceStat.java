package com.taobao.api.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.util.DateUtil;

/**
 * 代表一个商品价格统计数据结果
 * @author hukui
 *
 */
public class ProductPriceStat extends TaobaoModel {

	private static final long serialVersionUID = 2383452686445703046L;

	private static final String PRODUCT_ID = "product_id";    //产品id对应属性名。
	
	private static final String STARTED = "started"; 	//历史价格起始时间对应属性名。
	
	private static final String ENDED = "ended"; 	//历史价格终止时间对因属性名。
	
	private static final String INTERVAL = "interval"; 	//价格时间间隔对应属性名。
	
	private static final String SHOP_PRICES = "shop_prices"; 	//店铺保修历史价格对应属性名。
	
	private static final String STANDARD_PRICES = "standard_prices"; 	//全国联保历史价格对应属性名。 

	private String productId;    //产品id。
	
	private Date started; 	//历史价格起始时间。
	
	private Date ended; 	//历史价格终止时间。
	
	private int interval; 	//价格时间间隔(以小时为单位）。例如一个月四周的历史价格，时间间隔是7*24=168。
	
	private List<String> shopPrices = new ArrayList<String>(); 	//店铺保修历史价格。例如对于一个月四周的历史价格，将包含四周的每周店铺保修平均价格。
	
	private List<String >standardPrices = new ArrayList<String>(); 	//全国联保历史价格。例如对于一个月四周的历史价格，将包含四周的每周全国联保平均价格。 

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnded() {
		return ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public List<String> getShopPrices() {
		return shopPrices;
	}

	public void setShopPrices(List<String> shopPrices) {
		this.shopPrices = shopPrices;
	}

	public List<String> getStandardPrices() {
		return standardPrices;
	}

	public void setStandardPrices(List<String> standardPrices) {
		this.standardPrices = standardPrices;
	}

	/**
	 * 把JSON格式转成PriceHistory对象
	 * 
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static ProductPriceStat convertJsonToObject(JSONObject json) throws JSONException{
		ProductPriceStat history  = new ProductPriceStat();
		
		if(json == null){
			return history;
		}
		
		if(json.has(ProductPriceStat.ENDED)){
			String ended = json.getString(ProductPriceStat.ENDED);
			if (ended != null && ended.length() != 0) {
				try {
					history.setEnded(DateUtil.strToDate(ended));
				} catch (ParseException e) {
					e.printStackTrace();
					throw new JSONException(e);
				}
			}
		}
		
		if(json.has(ProductPriceStat.INTERVAL)){
			history.setInterval((json.getInt(ProductPriceStat.INTERVAL)));
		}
		
		if(json.has(ProductPriceStat.PRODUCT_ID)){
			history.setProductId(json.getString(ProductPriceStat.PRODUCT_ID));
		}
		
		if(json.has(ProductPriceStat.SHOP_PRICES)){
		    JSONArray array = json.getJSONArray(ProductPriceStat.SHOP_PRICES);
		    if(array != null){
		    	for(int i =0; i<array.length(); i++){
		    		history.getShopPrices().add(array.getString(i));
		    	}
		    }
		}
		
		if(json.has(ProductPriceStat.STANDARD_PRICES)){
			JSONArray array = json.getJSONArray(ProductPriceStat.STANDARD_PRICES);
		    if(array != null){
		    	for(int i =0; i<array.length(); i++){
		    		history.getStandardPrices().add(array.getString(i));
		    	}
		    }
		}
		
		if(json.has(ProductPriceStat.STARTED)){
			String enstarted = json.getString(ProductPriceStat.STARTED);
			if (enstarted != null && enstarted.length() != 0) {
				try {
					history.setStarted(DateUtil.strToDate(enstarted));
				} catch (ParseException e) {
					e.printStackTrace();
					throw new JSONException(e);
				}
			}
		}
		
		return history;
	}
}

