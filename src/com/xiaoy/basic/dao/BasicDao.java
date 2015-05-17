package com.xiaoy.basic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础类
 * <p>
 * 所有的Dao层接口都要继承
 * 
 * @author XiaoY
 * @date: 2015年5月16日 下午11:59:17
 */
public interface BasicDao<T>
{
	/**
	 * 保存一个对象
	 * 
	 * @param t
	 */
	public void saveEntity(T t);

	/**
	 * 更新一个对象
	 * 
	 * @param t
	 */
	public void updateEntity(T t);

	/**
	 * 保存或更新一个对象
	 * 
	 * @param t
	 */
	public void saveOrUpdateEntity(T t);

	/**
	 * 删除一个对象
	 * 
	 * @param t
	 */
	public void delecteEntity(T t);

	/**
	 * 批量操作
	 * 
	 * @param hql
	 *            语句
	 * @param param
	 *            参数
	 */
	public void batchEntityByHQL(String hql, Map<String, Object> param);

	/**
	 * 通过主键查询出一个对象
	 * 
	 * @param id
	 * @return T
	 */
	public T loadEntity(Serializable id);

	/**
	 * 通过主键查询出一个对象
	 * 
	 * @param id
	 * @return T
	 */
	public T getEntity(Serializable id);

	/**
	 * 通过一个HQL语句查询出一个集合
	 * 
	 * @param hql
	 *            语句
	 * @param param
	 *            参数
	 * @return List&ltT&gt
	 */
	public List<T> findEntityByHQL(String hql, Map<String, Object> param);
}
