package com.mashup.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mashup.dao.IProductSimilarityDAO;
import com.mashup.domain.ProductSimilarity;
/**
 * A data access object (DAO) providing persistence and search support for
 * ProductSimilarity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.mashup.dao.impl.ProductSimilarity
 * @author MyEclipse Persistence Tools
 */

public class ProductSimilarityDAO extends HibernateDaoSupport implements IProductSimilarityDAO
{

	private static final Log log = LogFactory
			.getLog(ProductSimilarityDAO.class);
	// property constants
	public static final String SIMILARITY = "similarity";

	protected void initDao()
	{
		// do nothing
	}

	public void save(ProductSimilarity transientInstance)
	{
		log.debug("saving ProductSimilarity instance");
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

	public void delete(ProductSimilarity persistentInstance)
	{
		log.debug("deleting ProductSimilarity instance");
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

	public ProductSimilarity findById(java.lang.Integer id)
	{
		log.debug("getting ProductSimilarity instance with id: " + id);
		try
		{
			ProductSimilarity instance = (ProductSimilarity) getHibernateTemplate()
					.get("com.mashup.domain.ProductSimilarity", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProductSimilarity instance)
	{
		log.debug("finding ProductSimilarity instance by example");
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
		log.debug("finding ProductSimilarity instance with property: "
				+ propertyName + ", value: " + value);
		try
		{
			String queryString = "from ProductSimilarity as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySimilarity(Object similarity)
	{
		return findByProperty(SIMILARITY, similarity);
	}

	public List findAll()
	{
		log.debug("finding all ProductSimilarity instances");
		try
		{
			String queryString = "from ProductSimilarity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProductSimilarity merge(ProductSimilarity detachedInstance)
	{
		log.debug("merging ProductSimilarity instance");
		try
		{
			ProductSimilarity result = (ProductSimilarity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductSimilarity instance)
	{
		log.debug("attaching dirty ProductSimilarity instance");
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

	public void attachClean(ProductSimilarity instance)
	{
		log.debug("attaching clean ProductSimilarity instance");
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

	public static ProductSimilarityDAO getFromApplicationContext(
			ApplicationContext ctx)
	{
		return (ProductSimilarityDAO) ctx.getBean("ProductSimilarityDAO");
	}
}