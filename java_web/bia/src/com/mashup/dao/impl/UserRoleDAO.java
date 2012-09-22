package com.mashup.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IUserRoleDAO;
import com.mashup.domain.UserRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.UserRole
 * @author MyEclipse Persistence Tools
 */

public class UserRoleDAO extends HibernateDaoSupport implements IUserRoleDAO
{

	private static final Log log = LogFactory.getLog(UserRoleDAO.class);

	// property constants

	protected void initDao()
	{
		// do nothing
	}

	public void save(UserRole transientInstance)
	{
		log.debug("saving UserRole instance");
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

	public void delete(UserRole persistentInstance)
	{
		log.debug("deleting UserRole instance");
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

	public UserRole findById(java.lang.Integer id)
	{
		log.debug("getting UserRole instance with id: " + id);
		try
		{
			UserRole instance = (UserRole) getHibernateTemplate().get(
					"com.mashup.domain.UserRole", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserRole instance)
	{
		log.debug("finding UserRole instance by example");
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
		log.debug("finding UserRole instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from UserRole as model where model."
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
		log.debug("finding all UserRole instances");
		try
		{
			String queryString = "from UserRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserRole merge(UserRole detachedInstance)
	{
		log.debug("merging UserRole instance");
		try
		{
			UserRole result = (UserRole) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserRole instance)
	{
		log.debug("attaching dirty UserRole instance");
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

	public void attachClean(UserRole instance)
	{
		log.debug("attaching clean UserRole instance");
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

	public static UserRoleDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (UserRoleDAO) ctx.getBean("UserRoleDAO");
	}
}