package com.mashup.service;

import java.util.List;

import com.mashup.dao.ISensitivewordDAO;
import com.mashup.domain.Sensitiveword;

public interface ISensWordService {
		
		public List findAll();
		
		public Sensitiveword getsensWordById(Integer id);

		
		public void updateSensWord(Sensitiveword sensWord);
		
		
		public void insertSensWord(Sensitiveword sensWord);
		
		public List findByWord(String word);
		public Sensitiveword findByWordId(Integer sensId);
		public void removeSensitivewordById(Integer id);
		
		public void batchRemoveSensWord(List ls);

		public ISensitivewordDAO getSensitivewordDAO() ;

		public void setSensitivewordDAO(ISensitivewordDAO sensitivewordDAO);



}
