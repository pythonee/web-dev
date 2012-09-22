package com.mashup.dao;

import java.util.List;

import com.mashup.domain.Advertise;
import com.mashup.domain.Category;

public interface ICategoryDAO {
	public void save(Category transientInstance);
    
	public void delete(Category persistentInstance);
    
    public Category findById( java.lang.Integer id);
    
    public List findByExample(Category instance);
    
    public List findByProperty(String propertyName, Object value);

	public List findByCategoryName(Object categoryName);
	
	public List findByTags(Object tags);
	
	public List findByFatherId(Object fatherId);

	public List findAll();
	
    public Category merge(Category detachedInstance);

    public void attachDirty(Category instance);
}
