package com.taobao.api.model;

/**
 * @author gaoweibin.tw
 *
 */
public class PropimgUploadResponse extends TaobaoResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7895133380881624178L;
	
	private PropImg propImg;
	
	public PropimgUploadResponse() {
		super();
	}
	
	public PropimgUploadResponse(TaobaoResponse response){
		super(response);
	}

	public PropImg getPropImg() {
		return propImg;
	}

	public void setPropImg(PropImg propImg) {
		this.propImg = propImg;
	}
}
