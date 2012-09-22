package com.mashup.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IResourcesRoleDAO;
import com.mashup.domain.ResourcesRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * ResourcesRole entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.ResourcesRole
 * @author MyEclipse Persistence Tools
 */

public class ResourcesRoleDAO extends HibernateDaoSupport implements IResourcesRoleDAO
{

	private static final Log log = LogFactory.getLog(ResourcesRoleDAO.class);

	// property constants

	protected void initDao()
	{
		// do nothing
	}

	public void save(ResourcesRole transientInstance)
	{
		log.debug("saving ResourcesRole instance");
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

	public void delete(ResourcesRole persistentInstance)
	{
		log.debug("deleting ResourcesRole instance");
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

	public ResourcesRole findById(java.lang.Integer id)
	{
		log.debug("getting ResourcesRole instance with id: " + id);
		try
		{
			ResourcesRole instance = (ResourcesRole) getHibernateTemplate()
					.get("com.mashup.domain.ResourcesRole", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ResourcesRole instance)
	{
		log.debug("finding ResourcesRole instance by example");
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
		log.debug("finding ResourcesRole instance with property: "
				+ propertyName + ", value: " + value);
		try
		{
			String queryString = "from ResourcesRole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll()
	{
		log.debug("finding all ResourcesRole instances");
		try
		{
			String queryString = "from ResourcesRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public ResourcesRole merge(ResourcesRole detachedInstance)
	{
		log.debug("merging ResourcesRole instance");
		try
		{
			ResourcesRole result = (ResourcesRole) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ResourcesRole instance)
	{
		log.debug("attaching dirty ResourcesRole instance");
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

	public void attachClean(ResourcesRole instance)
	{
		log.debug("attaching clean ResourcesRole instance");
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

	public static ResourcesRoleDAO getFromApplicationContext(
			ApplicationContext ctx)
	{
		return (ResourcesRoleDAO) ctx.getBean("ResourcesRoleDAO");
	}
}