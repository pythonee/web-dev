package com.mashup.dao.impl;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IResourcesDAO;
import com.mashup.domain.Resources;

/**
 * A data access object (DAO) providing persistence and search support for
 * Resources entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.Resources
 * @author MyEclipse Persistence Tools
 */

public class ResourcesDAO extends HibernateDaoSupport implements IResourcesDAO
{

	private static final Log log = LogFactory.getLog(ResourcesDAO.class);
	// property constants
	public static final String RESOURCE_NAME = "resourceName";
	public static final String RESOURCE_TYPE = "resourceType";
	public static final String RESOURCE_VALUE = "resourceValue";
	public static final String DESC = "resourceDesc";

	protected void initDao()
	{
		// do nothing
	}

	public void save(Resources transientInstance)
	{
		log.debug("saving Resources instance");
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

	public void delete(Resources persistentInstance)
	{
		log.debug("deleting Resources instance");
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
	
	public void batchRemove(List<Integer> ids){
		log.debug("batch deleting Resources instance");
		try
		{
			for (Integer id : ids) {
				delete(findById(id));
			}
			log.debug(" batch delete successful");
		} catch (RuntimeException re)
		{
			log.error("batch delete failed", re);
			throw re;
		}	
	}
	
	public Resources findById(java.lang.Integer id)
	{
		log.debug("getting Resources instance with id: " + id);
		try
		{
			Resources instance = (Resources) getHibernateTemplate().get(
					"com.mashup.domain.Resources", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Resources instance)
	{
		log.debug("finding Resources instance by example");
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
		log.debug("finding Resources instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from Resources as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByResourceName(Object resourceName)
	{
		return findByProperty(RESOURCE_NAME, resourceName);
	}

	public List findByResourceType(Object resourceType)
	{
		return findByProperty(RESOURCE_TYPE, resourceType);
	}

	public List findByResourceValue(Object resourceValue)
	{
		return findByProperty(RESOURCE_VALUE, resourceValue);
	}

	public List findByDesc(Object desc)
	{
		return findByProperty(DESC, desc);
	}

	public List findAll()
	{
		log.debug("finding all Resources instances");
		try
		{
			String queryString = "from Resources";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public Resources merge(Resources detachedInstance)
	{
		log.debug("merging Resources instance");
		try
		{
			Resources result = (Resources) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Resources instance)
	{
		log.debug("attaching dirty Resources instance");
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

	public void attachClean(Resources instance)
	{
		log.debug("attaching clean Resources instance");
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

	public static ResourcesDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (ResourcesDAO) ctx.getBean("ResourcesDAO");
	}
}