package com.xiaoy.dao;

import java.util.List;

import com.xiaoy.action.form.UserForm;
import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.entities.User;

public interface UserDao extends BasicDao<User>
{

	/**
	 * 验证邮箱地址是否被占用
	 * 
	 * @param email
	 * @return
	 */
	List<User> isValidEmailRepeat(String email);

	/**
	 * 登陆信息验证
	 * 
	 * @param model
	 * @return
	 */
	List<User> validLogin(UserForm model);

}
