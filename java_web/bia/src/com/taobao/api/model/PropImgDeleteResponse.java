package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class PropImgDeleteResponse extends TaobaoResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5075628510215281832L;
	
	private PropImg propImg;
	
	public PropImgDeleteResponse() {
		super();
	}
	
	public PropImgDeleteResponse(TaobaoResponse response){
		super(response);
	}

	public PropImg getPropImg() {
		return propImg;
	}

	public void setPropImg(PropImg propImg) {
		this.propImg = propImg;
	}
}
