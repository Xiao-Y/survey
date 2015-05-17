package com.xiaoy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.entities.User;
import com.xiaoy.service.UserService;

@Service("userService")
public class UserServiceImpl extends BasicServiceImpl<User> implements UserService
{

	@Override
	@Resource(name="userDao")
	public void setBasicDao(BasicDao<User> basicDao)
	{
		super.setBasicDao(basicDao);
	}
	
}
