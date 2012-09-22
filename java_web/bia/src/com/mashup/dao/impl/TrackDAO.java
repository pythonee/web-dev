package com.mashup.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.ITrackDAO;
import com.mashup.domain.Track;

/**
 * A data access object (DAO) providing persistence and search support for Track
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mashup.dao.impl.Track
 * @author MyEclipse Persistence Tools
 */

public class TrackDAO extends HibernateDaoSupport implements ITrackDAO {
	private static final Log log = LogFactory.getLog(TrackDAO.class);
	// property constants
	public static final String IP_ADRESS = "ipAdress";
	public static final String USER_NAME = "userName";
	public static final String PAGE_NAME = "pageName";
	public static final String TIME = "time";

	protected void initDao() {
		// do nothing
	}

	public void save(Track transientInstance) {
		log.debug("saving Track instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Track persistentInstance) {
		log.debug("deleting Track instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Track findById(java.lang.Integer id) {
		log.debug("getting Track instance with id: " + id);
		try {
			Track instance = (Track) getHibernateTemplate().get(
					"com.mashup.domain.Track", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Track instance) {
		log.debug("finding Track instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Track instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Track as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIpAdress(Object ipAdress) {
		return findByProperty(IP_ADRESS, ipAdress);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByPageName(Object pageName) {
		return findByProperty(PAGE_NAME, pageName);
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findAll() {
		log.debug("finding all Track instances");
		try {
			String queryString = "from Track";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Track merge(Track detachedInstance) {
		log.debug("merging Track instance");
		try {
			Track result = (Track) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Track instance) {
		log.debug("attaching dirty Track instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Track instance) {
		log.debug("attaching clean Track instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TrackDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TrackDAO) ctx.getBean("TrackDAO");
	}
}