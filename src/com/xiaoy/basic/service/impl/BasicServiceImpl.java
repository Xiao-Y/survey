package com.xiaoy.basic.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.BasicService;

/**
 * 所有的*ServiceImpl都要继承
 * <p/>
 * 重写BasicDao方法,注入自己的实体类
 * 
 * @author XiaoY
 * @date: 2015年5月17日 下午3:44:52
 */
public abstract class BasicServiceImpl<T> implements BasicService<T>
{

	protected BasicDao<T> basicDao;

	public abstract void setBasicDao(BasicDao<T> basicDao);
	
	@Override
	public void saveEntity(T t)
	{
		basicDao.saveEntity(t);
	}

	@Override
	public void updateEntity(T t)
	{
		basicDao.updateEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t)
	{
		basicDao.saveOrUpdateEntity(t);
	}

	@Override
	public void delecteEntity(T t)
	{
		basicDao.delecteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Map<String, Object> param)
	{
		basicDao.batchEntityByHQL(hql, param);
	}

	@Override
	public T loadEntity(Serializable id)
	{
		return basicDao.loadEntity(id);
	}

	@Override
	public T getEntity(Serializable id)
	{
		return basicDao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Map<String, Object> param)
	{
		return basicDao.findEntityByHQL(hql, param);
	}
}
