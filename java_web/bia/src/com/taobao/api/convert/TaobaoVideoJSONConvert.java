package com.taobao.api.convert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.taobao.api.ApiConstants;
import com.taobao.api.TaobaoApiException;
import com.taobao.api.json.JSONArray;
import com.taobao.api.json.JSONException;
import com.taobao.api.json.JSONObject;
import com.taobao.api.model.Sku;
import com.taobao.api.model.Video;
import com.taobao.api.util.DateUtil;

/**
 * 
 * @author moling
 * @since 1.0, 2009-8-4
 */
public class TaobaoVideoJSONConvert {
	/**
	 * 将单个json object转换为video
	 * 
	 * @param json
	 * @return Video
	 * @throws JSONException
	 * @throws TaobaoApiException
	 */
	public static Video convertJsonToVideo(JSONObject json) throws JSONException,TaobaoApiException {
		Video video = new Video();

		if (json.has(ApiConstants.ID)) {
			video.setId(json.getString(ApiConstants.ID));
		}
		if (json.has(ApiConstants.IID)) {
			video.setIid(json.getString(ApiConstants.IID));
		}
		if (json.has(ApiConstants.VIDEO_ID)) {
			video.setVideoId(json.getString(ApiConstants.VIDEO_ID));
		}
		if(json.has(ApiConstants.URL)){
			video.setUrl(json.getString(ApiConstants.URL));
		}
		if (json.has(ApiConstants.CREATED)) {
			try {
				video.setCreated(DateUtil.strToDate(json.getString(ApiConstants.CREATED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		if (json.has(ApiConstants.MODIFIED)) {
			try {
				video.setModified(DateUtil.strToDate(json.getString(ApiConstants.MODIFIED)));
			} catch (ParseException e) {
				throw new TaobaoApiException(e);
			}
		}
		
		return video;
	}
	
	/**
	 * 将返回的JsonArray转换为List<Video>
	 * 
	 * @param jsonArray
	 * @return List<Video>
	 * @throws TaobaoApiException
	 */
	public static List<Video> convertJsonArrayToVideoList(JSONArray jsonArray) throws TaobaoApiException {
		List<Video> videos = new ArrayList<Video>();
		Video video = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				video = convertJsonToVideo(jsonArray.getJSONObject(i));
			} catch (JSONException e) {
				throw new TaobaoApiException(e);
			}
			videos.add(video);
		}
		return videos;
	}
}
