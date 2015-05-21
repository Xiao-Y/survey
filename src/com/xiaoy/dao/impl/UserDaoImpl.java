package com.xiaoy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoy.action.form.UserForm;
import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.UserDao;
import com.xiaoy.entities.User;
import com.xiaoy.util.DataHelp;

@Repository("userDao")
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao
{

	@Override
	public List<User> isValidEmailRepeat(String email)
	{
		String hql = "from User u where u.email = :email";

		Map<String, Object> param = new HashMap<>();
		param.put("email", email);
		return this.findEntityByHQL(hql, param);
	}

	@Override
	public List<User> validLogin(UserForm model)
	{
		String hql = "from User u where u.email = :email and u.password = :password";
		Map<String, Object> param = new HashMap<>();
		param.put("email", model.getEmail());
		param.put("password", DataHelp.md5(model.getPassword()));

		return this.findEntityByHQL(hql, param);
	}
}
