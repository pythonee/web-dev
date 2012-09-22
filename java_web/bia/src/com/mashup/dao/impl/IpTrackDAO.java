package com.mashup.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IIpTrackDAO;
import com.mashup.domain.IpTrack;
/**
 * A data access object (DAO) providing persistence and search support for
 * IpTrack entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.IpTrack
 * @author MyEclipse Persistence Tools
 */

public class IpTrackDAO extends HibernateDaoSupport implements IIpTrackDAO
{

	private static final Log log = LogFactory.getLog(IpTrackDAO.class);
	// property constants
	public static final String IP_ADDRESS = "ipAddress";
	public static final String PRODUCT_ID = "productId";
	public static final String TIMES = "times";

	protected void initDao()
	{
		// do nothing
	}

	public void save(IpTrack transientInstance)
	{
		log.debug("saving IpTrack instance");
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

	public void delete(IpTrack persistentInstance)
	{
		log.debug("deleting IpTrack instance");
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

	public IpTrack findById(java.lang.Integer id)
	{
		log.debug("getting IpTrack instance with id: " + id);
		try
		{
			IpTrack instance = (IpTrack) getHibernateTemplate().get(
					"com.mashup.domain.IpTrack", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpTrack> findByExample(IpTrack instance)
	{
		log.debug("finding IpTrack instance by example");
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

	public List<IpTrack> findByProperty(String propertyName, Object value)
	{
		log.debug("finding IpTrack instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from IpTrack as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpTrack> findByIpAddress(Object ipAddress)
	{
		return findByProperty(IP_ADDRESS, ipAddress);
	}

	public List<IpTrack> findByProductId(Object productId)
	{
		return findByProperty(PRODUCT_ID, productId);
	}

	public List<IpTrack> findByTimes(Object times)
	{
		return findByProperty(TIMES, times);
	}

	public List<IpTrack> findAll()
	{
		log.debug("finding all IpTrack instances");
		try
		{
			String queryString = "from IpTrack";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<IpTrack> findLatestAll(String ip)
	{
		
		log.debug("finding latest all IpTrack instances");
		try
		{
			String queryString = "from IpTrack ipTrack where ipTrack.ipAddress=? order by time desc,times desc limit 10";
			return getHibernateTemplate().find(queryString,ip);
		} catch (RuntimeException re)
		{
			log.error("find latest all failed", re);
			throw re;
		}
	}

	@Override
	public boolean isExisted(String ip, int pid)
	{
		log.debug("Have existed a record..");
		try
		{
			String queryString = "from IpTrack ipTrack where ipTrack.ipAddress=? and ipTrack.productId="+pid;
			if(getHibernateTemplate().find(queryString,ip).size() == 0){
				return false;
			}else {
				return true;
			}
			
		} catch (RuntimeException re)
		{
			log.error("Have existed a record failed", re);
			throw re;
		}
	}
	
	public void clear(){
		
		List<IpTrack> list = findAll();
		
		if (list.size() >0)
		{
			getHibernateTemplate().deleteAll(list);
		}
	}

	public IpTrack merge(IpTrack detachedInstance)
	{
		log.debug("merging IpTrack instance");
		try
		{
			IpTrack result = (IpTrack) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpTrack instance)
	{
		log.debug("attaching dirty IpTrack instance");
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

	public void attachClean(IpTrack instance)
	{
		log.debug("attaching clean IpTrack instance");
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

	public static IpTrackDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (IpTrackDAO) ctx.getBean("IpTrackDAO");
	}
}