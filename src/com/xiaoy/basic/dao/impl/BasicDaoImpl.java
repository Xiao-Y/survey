package com.xiaoy.basic.dao.impl;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.xiaoy.basic.dao.BasicDao;

/**
 * 基础类的实现类
 * <p>
 * 所有的Dao都要继承
 * 
 * @author XiaoY
 * @date: 2015年5月17日 上午12:21:45
 */
public class BasicDaoImpl<T> implements BasicDao<T>
{
	private static final Logger logger = Logger.getLogger(BasicDaoImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public Session getSession()
	{
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BasicDaoImpl()
	{
		// 得到泛型的超类
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 得到真实的类型
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		logger.info("类型转换---> " + clazz);
	}

	@Override
	public void saveEntity(T t)
	{
		this.getSession().save(t);
	}

	@Override
	public void updateEntity(T t)
	{
		this.getSession().update(t);
	}

	@Override
	public void saveOrUpdateEntity(T t)
	{
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void delecteEntity(T t)
	{
		this.getSession().delete(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Map<String, Object> param)
	{
		if (!StringUtils.isEmpty(hql))
		{
			Query query = this.getSession().createQuery(hql);

			if (param != null && param.size() > 0)
			{
				for (Map.Entry<String, Object> entry : param.entrySet())
				{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			query.executeUpdate();
		}
	}

	@Override
	public void batchEntityByHQL(String hql, Object... param)
	{
		if (!StringUtils.isEmpty(hql))
		{
			Query query = this.getSession().createQuery(hql);

			if (param != null && param.length > 0)
			{
				for (int i = 0; i < param.length; i++)
				{
					query.setParameter(i, param[i]);
				}
			}
			query.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T loadEntity(Serializable id)
	{
		return (T) this.getSession().load(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getEntity(Serializable id)
	{
		return (T) this.getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEntityByHQL(String hql, Map<String, Object> param)
	{
		if (!StringUtils.isEmpty(hql))
		{
			Query query = this.getSession().createQuery(hql);

			if (param != null && param.size() > 0)
			{
				for (Map.Entry<String, Object> entry : param.entrySet())
				{
					query.setParameter(entry.getKey(), entry.getValue());
				}
			}
			return query.list();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findEntityByHQL(String hql, Object... param)
	{
		if (!StringUtils.isEmpty(hql))
		{
			Query query = this.getSession().createQuery(hql);

			if (param != null && param.length > 0)
			{
				for (int i = 0; i < param.length; i++)
				{
					query.setParameter(i, param[i]);
				}
			}
			return query.list();
		}
		return null;
	}
}
