package com.mashup.dao.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IAdvertiseDAO;
import com.mashup.domain.Advertise;

/**
 * A data access object (DAO) providing persistence and search support for
 * Advertise entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.Advertise
 * @author MyEclipse Persistence Tools
 */

public class AdvertiseDAO extends HibernateDaoSupport implements IAdvertiseDAO
{

	private static final Log log = LogFactory.getLog(AdvertiseDAO.class);
	// property constants
	public static final String AD_DESC = "adDesc";
	public static final String AD_POSTION = "adPostion";
	public static final String TARGET = "target";
	public static final String AD_NAME = "adName";
	public static final String AD_PATH = "adPath";

	protected void initDao()
	{
		// do nothing
	}

	public void save(Advertise transientInstance)
	{
		log.debug("saving Advertise instance");
		try
		{
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re)
		{
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Advertise persistentInstance)
	{
		log.debug("deleting Advertise instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	public Advertise findById(java.lang.Integer id)
	{
		log.debug("getting Advertise instance with id: " + id);
		try
		{
			Advertise instance = (Advertise) getHibernateTemplate().get(
					"com.mashup.domain.Advertise", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Advertise instance)
	{
		log.debug("finding Advertise instance by example");
		try
		{
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding Advertise instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from Advertise as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdDesc(Object adDesc)
	{
		return findByProperty(AD_DESC, adDesc);
	}

	public List findByAdPostion(Object adPostion)
	{
		return findByProperty(AD_POSTION, adPostion);
	}

	public List findByTarget(Object target)
	{
		return findByProperty(TARGET, target);
	}

	public List findByAdName(Object adName)
	{
		return findByProperty(AD_NAME, adName);
	}

	public List findByAdPath(Object adPath)
	{
		return findByProperty(AD_PATH, adPath);
	}

	public List findAll()
	{
		log.debug("finding all Advertise instances");
		try
		{
			String queryString = "from Advertise";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public Advertise merge(Advertise detachedInstance)
	{
		log.debug("merging Advertise instance");
		try
		{
			Advertise result = (Advertise) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Advertise instance)
	{
		log.debug("attaching dirty Advertise instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Advertise instance)
	{
		log.debug("attaching clean Advertise instance");
		try
		{
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteOutOfDate(){
		Date rightNow=new Date();
		log.debug("delete Advertise instances by out of date: " + rightNow);
		try
		{
			String queryString = "from Advertise as model where model.endTime"+"< ?";
			List list=getHibernateTemplate().find(queryString,rightNow);
			if(list!=null&&list.size()!=0){
				getHibernateTemplate().deleteAll(list);
				log.debug("delete Advertise instances successful by out of date: " + rightNow);
			}
		} catch (RuntimeException re)
		{
			log.error("delete Advertise instances by out of date failed", re);
			throw re;
		}
	}
	public static AdvertiseDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (AdvertiseDAO) ctx.getBean("AdvertiseDAO");
	}
}