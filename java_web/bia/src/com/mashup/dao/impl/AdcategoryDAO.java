package com.mashup.dao.impl;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IAdcategoryDAO;
import com.mashup.domain.Adcategory;

/**
 	* A data access object (DAO) providing persistence and search support for Adcategory entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.mashup.dao.impl.Adcategory
  * @author MyEclipse Persistence Tools 
 */

public class AdcategoryDAO extends HibernateDaoSupport implements IAdcategoryDAO {
    private static final Log log = LogFactory.getLog(AdcategoryDAO.class);
	//property constants
	public static final String AD_CATEGORY_NAME = "adCategoryName";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Adcategory transientInstance) {
        log.debug("saving Adcategory instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Adcategory persistentInstance) {
        log.debug("deleting Adcategory instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Adcategory findById( java.lang.Integer id) {
        log.debug("getting Adcategory instance with id: " + id);
        try {
            Adcategory instance = (Adcategory) getHibernateTemplate()
                    .get("com.mashup.domain.Adcategory", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Adcategory instance) {
        log.debug("finding Adcategory instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Adcategory instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Adcategory as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAdCategoryName(Object adCategoryName
	) {
		return findByProperty(AD_CATEGORY_NAME, adCategoryName
		);
	}
	

	public List findAll() {
		log.debug("finding all Adcategory instances");
		try {
			String queryString = "from Adcategory";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Adcategory merge(Adcategory detachedInstance) {
        log.debug("merging Adcategory instance");
        try {
            Adcategory result = (Adcategory) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Adcategory instance) {
        log.debug("attaching dirty Adcategory instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Adcategory instance) {
        log.debug("attaching clean Adcategory instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static AdcategoryDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (AdcategoryDAO) ctx.getBean("AdcategoryDAO");
	}
}