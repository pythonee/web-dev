/**
 * 
 */
package com.taobao.api.model;

import java.io.File;

/**
 * 添加担保寄售商品
 * 
 * @author jeck218@gmail.com 2009-11-18
 */
public class ItemGameAddRequest extends ItemAddRequest {

	//
	private static final long serialVersionUID = -2687099971777127272L;

	private String gameType;
	private String verticalIds;
	private String verticalValues;
	private String verticalImageId;
	private File verticalImage;

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getVerticalIds() {
		return verticalIds;
	}

	public void setVerticalIds(String verticalIds) {
		this.verticalIds = verticalIds;
	}

	public String getVerticalValues() {
		return verticalValues;
	}

	public void setVerticalValues(String verticalValues) {
		this.verticalValues = verticalValues;
	}

	public String getVerticalImageId() {
		return verticalImageId;
	}

	public void setVerticalImageId(String verticalImageId) {
		this.verticalImageId = verticalImageId;
	}

	public File getVerticalImage() {
		return verticalImage;
	}

	public void setVerticalImage(File verticalImage) {
		this.verticalImage = verticalImage;
	}
	
	@Override
	public void setImage(File image) {
		throw new RuntimeException("此API不支持上传主图");
	}

}
