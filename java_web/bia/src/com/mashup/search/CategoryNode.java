package com.mashup.search;
import com.mashup.domain.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
public class CategoryNode {
	int categoryId;
	String categoryName;
	Map tagsMap;
	int fatherId;
	public List categoryNodeList;
	
	public List getCategoryNodeList() {
		return categoryNodeList;
	}
	public void setCategoryNodeList(List categoryNodeList) {
		this.categoryNodeList = categoryNodeList;
	}
	public CategoryNode(int categoryId, String categoryName, String tags,
			int fatherId) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.fatherId = fatherId;
		this.tagsMap=new TreeMap();
		this.buildTagsToMap(tags);
		this.categoryNodeList=new ArrayList();
	}
	public CategoryNode(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.fatherId = category.getFatherId();
		this.tagsMap=new TreeMap();
		this.buildTagsToMap(category.getTags());
		this.categoryNodeList=new ArrayList();
	}
	public void buildTagsToMap(String tags){
		String[] tagTerms = tags.split(" ");
		for (int i = 0; i < tagTerms.length; i++) {
			String term = tagTerms[i];			
			if (tagsMap.containsKey(term)) {
				Integer value = (Integer) tagsMap.get(term);
				tagsMap.put(term,
				new Integer(value.intValue() + 1));
			} else {
				tagsMap.put(term, new Integer(1));
			}
		}
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Map getTagsMap() {
		return tagsMap;
	}
	public void setTagsMap(Map tagsMap) {
		this.tagsMap = tagsMap;
	}
	public int getFatherId() {
		return fatherId;
	}
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

}
