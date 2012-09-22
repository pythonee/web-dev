package com.mashup.service.impl;

import java.util.List;

import com.mashup.dao.ISensitivewordDAO;
import com.mashup.domain.Sensitiveword;
import com.mashup.service.ISensWordService;

public class SensWordService implements ISensWordService{

	ISensitivewordDAO sensitivewordDAO;
	
	
	public List findAll()
	{
		List sensWordList = sensitivewordDAO.findAll();
		
		return sensWordList;
	}
	
	public Sensitiveword getsensWordById(Integer id){
		return sensitivewordDAO.findById(id);
	}

	
	public void updateSensWord(Sensitiveword sensWord){
		sensitivewordDAO.attachDirty(sensWord);
	}
	
	
	public void insertSensWord(Sensitiveword sensWord){
		sensitivewordDAO.save(sensWord);
	}
	
	public List findByWord(String word){
		return sensitivewordDAO.findByProperty("sensitiveWord", word);
	}
	public Sensitiveword findByWordId(Integer sensId){
		return sensitivewordDAO.findById(sensId);
	}
	public void removeSensitivewordById(Integer id){
		sensitivewordDAO.delete(sensitivewordDAO.findById(id));
	}
	
	
	public void batchRemoveSensWord(List ls){
		
	}

	public ISensitivewordDAO getSensitivewordDAO() {
		return sensitivewordDAO;
	}

	public void setSensitivewordDAO(ISensitivewordDAO sensitivewordDAO) {
		this.sensitivewordDAO = sensitivewordDAO;
	}


}
