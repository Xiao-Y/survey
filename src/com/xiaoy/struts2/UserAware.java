package com.xiaoy.struts2;

import com.xiaoy.entities.User;

/**
 * 用户关注
 * 
 * @author XiaoY
 * @date: 2015年5月22日 下午11:03:36
 */
public interface UserAware
{
	/**
	 * 用于注入User
	 * 
	 * @param user
	 */
	public void setUser(User user);
}
