package com.mashup.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.mashup.domain.Sensitiveword;
import com.mashup.dao.ISensitivewordDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sensitiveword entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.Sensitiveword
 * @author MyEclipse Persistence Tools
 */

public class SensitivewordDAO extends HibernateDaoSupport implements ISensitivewordDAO
{

	private static final Log log = LogFactory.getLog(SensitivewordDAO.class);
	// property constants
	public static final String SENSITIVE_WORD = "sensitiveWord";

	protected void initDao()
	{
		// do nothing
	}

	public void save(Sensitiveword transientInstance)
	{
		log.debug("saving Sensitiveword instance");
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

	public void delete(Sensitiveword persistentInstance)
	{
		log.debug("deleting Sensitiveword instance");
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

	public Sensitiveword findById(java.lang.Integer id)
	{
		log.debug("getting Sensitiveword instance with id: " + id);
		try
		{
			Sensitiveword instance = (Sensitiveword) getHibernateTemplate()
					.get("com.mashup.domain.Sensitiveword", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Sensitiveword instance)
	{
		log.debug("finding Sensitiveword instance by example");
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
		log.debug("finding Sensitiveword instance with property: "
				+ propertyName + ", value: " + value);
		try
		{
			String queryString = "from Sensitiveword as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySensitiveWord(Object sensitiveWord)
	{
		return findByProperty(SENSITIVE_WORD, sensitiveWord);
	}

	public List findAll()
	{
		log.debug("finding all Sensitiveword instances");
		try
		{
			String queryString = "from Sensitiveword";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sensitiveword merge(Sensitiveword detachedInstance)
	{
		log.debug("merging Sensitiveword instance");
		try
		{
			Sensitiveword result = (Sensitiveword) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sensitiveword instance)
	{
		log.debug("attaching dirty Sensitiveword instance");
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

	public void attachClean(Sensitiveword instance)
	{
		log.debug("attaching clean Sensitiveword instance");
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

	public static SensitivewordDAO getFromApplicationContext(
			ApplicationContext ctx)
	{
		return (SensitivewordDAO) ctx.getBean("SensitivewordDAO");
	}
}