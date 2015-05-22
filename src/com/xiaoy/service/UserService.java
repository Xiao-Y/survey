package com.xiaoy.service;

import com.xiaoy.basic.service.BasicService;
import com.xiaoy.entities.User;

public interface UserService extends BasicService<User>
{

	/**
	 * 邮箱重复验证
	 * 
	 * @param email
	 *            原字符串
	 * @return true 有效<br/>
	 *         false 无效<br/>
	 */
	boolean isValidEmailRepeat(String email);

	/**
	 * 验证用户登陆信息
	 * 
	 * @param model
	 * @return
	 */
	User validLogin(User model);

}
