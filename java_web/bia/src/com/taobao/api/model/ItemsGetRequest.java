/**
 * 
 */
package com.taobao.api.model;

/**
 * 调用taobao.items.get搜索商品是 需要传入的参数
 * 
 * @author sulinchong.pt
 * 
 */
public class ItemsGetRequest extends TaobaoRequest{
	//
	private static final long serialVersionUID = 245886862355972349L;
	private String q;
	private String startPrice;
	private String endPrice;
	private String cid;
	private Integer pageNo;
	private Integer pageSize;
	private String orderBy;
	private String fields;
	private String nicks;
	private String props;
	private String productId;
	private Boolean wwStatus;
	private Boolean postFree;
	private String state;
	private String city;
    private String itemType;
    /**
	 * 是否是3D淘宝的商品
	 */
    private Boolean is3D;
    
    //商品所属卖家的最小信用数
    private Long startScore;
    //商品所属卖家的最大信用数
    private Long endScore;
    //商品30天内最小销售数
    private Long startVolume;
    //商品30天内最大销售数
    private Long endVolume;
    //是否淘1站代购商品
    private Boolean oneStation;
    //是否支持货到付款
    private Boolean isCod;
    //是否商城的商品
    private Boolean isMall;
    //是否如实描述(即:先行赔付)商品
    private Boolean isPrepay;
    //是否正品保障商品
    private Boolean genuineSecurity ;
    //是否提供保障服务的商品。可选入参有：2、4。
    //设置为2表示该商品是“假一赔三”的商品，设置为4表示该商品是“7天无理由退换货”的商品 
    private Integer promotedService;
    //商品的新旧状态。可选入参有：new、second、unused 。
    private String stuffStatus;
    
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getNicks() {
		return nicks;
	}

	public void setNicks(String nicks) {
		this.nicks = nicks;
	}

	public ItemsGetRequest withQ(String q) {
		setQ(q);
		return this;
	}

	public ItemsGetRequest withStartPrice(String startPrice) {
		setStartPrice(startPrice);
		return this;
	}

	public ItemsGetRequest withEndPrice(String endPrice) {
		setEndPrice(endPrice);
		return this;
	}

	public ItemsGetRequest withCid(String cid) {
		setCid(cid);
		return this;
	}

	public ItemsGetRequest withPageNo(Integer pageNo) {
		setPageNo(pageNo);
		return this;
	}

	public ItemsGetRequest withPageSize(Integer pageSize) {
		setPageSize(pageSize);
		return this;
	}

	public ItemsGetRequest withOrderBy(String orderBy) {
		setOrderBy(orderBy);
		return this;
	}

	public ItemsGetRequest withFields(String fields) {
		setFields(fields);
		return this;
	}

	public ItemsGetRequest withNicks(String nicks) {
		setNicks(nicks);
		return this;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Boolean getWwStatus() {
		return wwStatus;
	}

	public void setWwStatus(Boolean wwStatus) {
		this.wwStatus = wwStatus;
	}

	public Boolean getPostFree() {
		return postFree;
	}

	public void setPostFree(Boolean postFree) {
		this.postFree = postFree;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public ItemsGetRequest withProps(String props) {
		setProps(props);
		return this;
	}
	
	public ItemsGetRequest withProductId(String productId) {
		setProductId(productId);
		return this;
	}
	
	public ItemsGetRequest withWwStatus(Boolean wwStatus) {
		setWwStatus(wwStatus);
		return this;
	}
	
	public ItemsGetRequest withPostFree(Boolean postFree) {
		setPostFree(postFree);
		return this;
	}
	
	public ItemsGetRequest withState(String state) {
		setState(state);
		return this;
	}
	
	public ItemsGetRequest withCity(String city) {
		setCity(city);
		return this;
	}
	public ItemsGetRequest withItemType(String itemType) {
		setItemType(itemType);
		return this;
	}

	public Boolean getIs3D() {
		return this.is3D;
	}

	public void setIs3D(Boolean is3D) {
		this.is3D = is3D;
	}
	public ItemsGetRequest withIs3S(Boolean is3D) {
		setIs3D(is3D);
		return this;
	}

	public Long getStartScore() {
		return this.startScore;
	}

	public void setStartScore(Long startScore) {
		this.startScore = startScore;
	}

	public Long getEndScore() {
		return this.endScore;
	}

	public void setEndScore(Long endScore) {
		this.endScore = endScore;
	}

	public Long getStartVolume() {
		return this.startVolume;
	}

	public void setStartVolume(Long startVolume) {
		this.startVolume = startVolume;
	}

	public Long getEndVolume() {
		return this.endVolume;
	}

	public void setEndVolume(Long endVolume) {
		this.endVolume = endVolume;
	}

	public Boolean getOneStation() {
		return this.oneStation;
	}

	public void setOneStation(Boolean oneStation) {
		this.oneStation = oneStation;
	}

	public Boolean getIsCod() {
		return this.isCod;
	}

	public void setIsCod(Boolean isCod) {
		this.isCod = isCod;
	}

	public Boolean getIsMall() {
		return this.isMall;
	}

	public void setIsMall(Boolean isMall) {
		this.isMall = isMall;
	}

	public Boolean getIsPrepay() {
		return this.isPrepay;
	}

	public void setIsPrepay(Boolean isPrepay) {
		this.isPrepay = isPrepay;
	}

	public Boolean getGenuineSecurity() {
		return this.genuineSecurity;
	}

	public void setGenuineSecurity(Boolean genuineSecurity) {
		this.genuineSecurity = genuineSecurity;
	}

	public Integer getPromotedService() {
		return this.promotedService;
	}

	public void setPromotedService(Integer promotedService) {
		this.promotedService = promotedService;
	}

	public String getStuffStatus() {
		return this.stuffStatus;
	}

	public void setStuffStatus(String stuffStatus) {
		this.stuffStatus = stuffStatus;
	}
	
}
