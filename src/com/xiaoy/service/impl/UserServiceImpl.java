package com.xiaoy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.action.form.UserForm;
import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.UserDao;
import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;
import com.xiaoy.util.ValiDateUtil;

@Service("userService")
public class UserServiceImpl extends BasicServiceImpl<User> implements UserService
{
	private UserDao userDao;

	@Override
	@Resource(name = "userDao")
	public void setBasicDao(BasicDao<User> basicDao)
	{
		super.basicDao = basicDao;
		this.userDao = (UserDao) basicDao;
	}
	
	@Override
	public boolean isValidEmailRepeat(String email)
	{
		List<User> list = userDao.isValidEmailRepeat(email);

		if (ValiDateUtil.isValid(list))
		{
			return false;
		}
		return true;
	}

	@Override
	public User validLogin(UserForm model)
	{
		User user = null;
		List<User> list = userDao.validLogin(model);

		if (list != null && list.size() > 0)
		{
			user = new User();
			BeanUtils.copyProperties(list.get(0), user);
		}
		return user;
	}
}
