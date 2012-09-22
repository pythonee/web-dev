package com.mashup.service;
import java.util.List;
import com.mashup.domain.Adcategory;;
public interface IAdcategoryService {
	public List findAll();
	
	public Adcategory getAdcategoryById(Integer id);
	
	public void updateAdcategory(Adcategory adcategory);
	
	
	public void insertAdcategory(Adcategory adcategory);
	public void removeAdcategoryById(Integer id);
	
	public void batchRemoveAdcategory(List ls);
}
