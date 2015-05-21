package com.xiaoy.basic.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象方法
 * 
 * @author XiaoY
 * @date: 2015年5月20日 下午6:44:21
 */
public abstract class BasicAction<T> extends ActionSupport implements ModelDriven<T>, Preparable, ServletRequestAware, ServletResponseAware
{
	private static final long serialVersionUID = -8699431871497570065L;

	private T model;

	@SuppressWarnings("unchecked")
	public BasicAction()
	{
		try
		{
			ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
			this.model = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	protected HttpServletRequest request = null;

	protected HttpServletResponse response = null;

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public void prepare() throws Exception
	{

	}

	@Override
	public T getModel()
	{
		return model;
	}
}
